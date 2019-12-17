package com.none.appFinancas.service;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String nome, String username, String password){
        try {
            return userRepository.save(new User(nome, username, password));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
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

}
