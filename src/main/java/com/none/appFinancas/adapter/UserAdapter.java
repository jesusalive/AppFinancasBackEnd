package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.enums.ProfileUser;

import java.util.ArrayList;
import java.util.List;


public class UserAdapter {

    public static List<UserDTO> userListAdapter(List<User> list){
        List<UserDTO> dtoList = new ArrayList<>();

        list.forEach(user -> {
            UserDTO userDTO = null;
            try {
                userDTO = new UserDTO(user.getId(), user.getName(), user.getUsername(), user.getProfile());
            } catch (IllegalAccessException e) {
                e.getMessage();
            }

            dtoList.add(userDTO);
        });

        return dtoList;
    }

    public static UserDTO userAdapter(User oldUser) throws IllegalAccessException {
       return new UserDTO(oldUser.getId(), oldUser.getName(), oldUser.getUsername(), oldUser.getProfile());
    }
}
