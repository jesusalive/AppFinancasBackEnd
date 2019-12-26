package com.none.appFinancas.service;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.dto.UserFormDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.repository.UserRepository;
import com.none.appFinancas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import javax.mail.internet.AddressException;
import javax.mail.internet.InternetAddress;
import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(String nome, String username, String email, String password){
        try {
            this.usernameVerify(username);
            this.emailVerify(email);
            this.passVerify(password);
            return userRepository.save(new User(nome, username, email,
                    passwordEncoder.encode(password)));
        }catch(RuntimeException e){
            throw new AuthError(e.getMessage());
        }
    }

    public Boolean userEmailExists(String email){
        try{
            this.emailVerify(email);
            return false;
        }catch (RuntimeException e){
            throw new AuthError(e.getMessage());
        }
    }

    private void emailVerify(String email) {
        userRepository.findByEmail(email).ifPresent( item -> {
            if(item.getName() != null){
                throw new AuthError("Email ja cadastrado, tente outro!");
            }
        });

        try {
            InternetAddress emailInTest = new InternetAddress(email);
            emailInTest.validate();
        } catch (AddressException e) {
            throw new AuthError("Preencha o campo email corretamente");
        }

    }

    public User findUserByUsername(String username){
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new AuthError("Usuario não encontrado"));
    }

    public List<UserDTO> allUsers(){
        List<User> oldList = userRepository.findAll();
        return UserAdapter.userListAdapter(oldList);
    }

    public User findOne(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

    public void usernameVerify(String username){
        userRepository.findByUsername(username).ifPresent( item -> {
            if(item.getName() != null){
                throw new AuthError("Usuario em uso!");
            }
        });
    }

    public User refreshUser(Long userId, UserFormDTO alterations){
        User user = userRepository.findById(userId).orElseThrow(
                () -> new AuthError("Usuario não encontrado")
        );

        if(!alterations.getNome().trim().isEmpty()){
            user.setName(alterations.getNome());
        }

        if(!alterations.getUsername().trim().isEmpty()){
            user.setUsername(alterations.getUsername());
        }

        if(!alterations.getEmail().trim().isEmpty()){
            user.setEmail(alterations.getEmail());
        }
        if(!alterations.getPassword().trim().isEmpty()){
            user.setPassword(passwordEncoder.encode(alterations.getPassword()));
        }

        return userRepository.save(user);
    }

    public void passVerify(String pass){
        if (pass.length() < 6){
            throw new AuthError("A senha deve conter no minimo 6 caracteres");
        }
    }

    public static UserSS loggedUser(){
        try{
            return (UserSS) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        }catch (Exception e){
            return null;
        }
    }

    public Boolean usernameExists(String username) {
        try{
            this.usernameVerify(username);
            return false;
        }catch (RuntimeException e){
            throw new AuthError(e.getMessage());
        }
    }
}
