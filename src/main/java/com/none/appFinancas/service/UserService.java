package com.none.appFinancas.service;

import com.none.appFinancas.adapter.UserAdapter;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.erros.ErrorModel;
import com.none.appFinancas.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.SQLIntegrityConstraintViolationException;
import java.util.List;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public Object createUser(String nome, String username, String password){
            try {
                return userRepository.save(new User(nome, username, password));
            }catch(RuntimeException e){
                return new ErrorModel(e.getMessage());
            }
    }

    public List<UserDTO> allUsers(){
        List<User> oldList = userRepository.findAll();
        return UserAdapter.userListAdapter(oldList);
    }

    public User findOne(Long id){
        return userRepository.findById(id).orElseThrow(() -> new RuntimeException("Usuário não encontrado"));
    }

}
