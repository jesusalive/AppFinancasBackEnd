package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.dto.UserDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DeposityAdapter {

    public static List<DeposityDTO> deposityListAdapter(List<Deposity> deposityList){
        List<DeposityDTO> dtoList = new ArrayList<>();

        deposityList.forEach(deposity -> {
            LocalDate date = deposity.getDate().plusDays(1);

            DeposityDTO deposityDTO = new DeposityDTO(deposity.getReason(),
                    deposity.getValue(), date.toString());

            dtoList.add(deposityDTO);
        });

        return dtoList;
    }

}
