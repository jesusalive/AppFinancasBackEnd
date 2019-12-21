package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.entity.Deposit;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class DepositAdapter {

    public static List<DepositDTO> depositListAdapter(List<Deposit> depositList){
        List<DepositDTO> dtoList = new ArrayList<>();

        depositList.forEach(deposity -> {
            LocalDate date = deposity.getDate().plusDays(1);

            DepositDTO depositDTO = new DepositDTO(deposity.getId(), deposity.getReason(),
                    deposity.getValue(), date.toString());

            dtoList.add(depositDTO);
        });

        return dtoList;
    }

}
