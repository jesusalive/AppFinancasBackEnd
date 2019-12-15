package com.none.appFinancas.service;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User createUser(String nome, String username, String password){
        return userRepository.save(new User(nome, username, password));
    }

    public List<UserDTO> allUsers(){
        List<User> oldList = userRepository.findAll();
        List<UserDTO> adaptedList = UserAdapter.userListAdapter(oldList);

        return adaptedList;
    }

    public User findOne(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
