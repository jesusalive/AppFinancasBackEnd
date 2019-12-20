package com.none.appFinancas.service;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    @Lazy
    private BCryptPasswordEncoder passwordEncoder;

    public User createUser(String nome, String username, String password){
        try {
            this.usernameVerify(username);
            this.passVerify(password);
            return userRepository.save(new User(nome, username, passwordEncoder.encode(password)));
        }catch(RuntimeException e){
            throw new AuthError(e.getMessage());
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

    public void passVerify(String pass){
        if (pass.length() < 6){
            throw new AuthError("A senha deve conter no minimo 6 caracteres");
        }
    }

}
