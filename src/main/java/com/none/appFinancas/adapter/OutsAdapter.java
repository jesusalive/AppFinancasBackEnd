package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.dto.OutsDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.Outs;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class OutsAdapter {
    public static List<OutsDTO> outListAdapter(List<Outs> outList){
        List<OutsDTO> dtoList = new ArrayList<>();

        outList.forEach(out -> {
            LocalDate date = out.getDate().plusDays(1);

            OutsDTO outDTO = new OutsDTO(out.getReason(),
                    out.getValue(), date.toString());

            dtoList.add(outDTO);
        });

        return dtoList;
    }
}
