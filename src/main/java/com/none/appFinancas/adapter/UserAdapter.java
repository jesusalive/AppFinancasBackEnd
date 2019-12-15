package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter {

    public static List<UserDTO> userListAdapter(List<User> list){
        List<UserDTO> dtoList = new ArrayList<>();

        list.forEach(user -> {
            UserDTO userDTO = new UserDTO(user.getId(), user.getName(), user.getUsername());

            dtoList.add(userDTO);
        });

        return dtoList;
    }

    public static UserDTO userAdapter(User oldUser){
       return new UserDTO(oldUser.getId(), oldUser.getName(), oldUser.getUsername());
    }
}
