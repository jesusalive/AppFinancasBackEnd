package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.FixedExpenseDTO;
import com.none.appFinancas.entity.FixedExpense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class FixedExpenseAdapter {

    public static List<FixedExpenseDTO> outListAdapter(List<FixedExpense> outList){
        List<FixedExpenseDTO> dtoList = new ArrayList<>();

        outList.forEach(out -> {
            LocalDate date = out.getDate().plusDays(1);
            LocalDate lastPayment = out.getLastPayment().plusDays(1);

            FixedExpenseDTO outDTO = new FixedExpenseDTO(out.getId(), out.getReason(),
                    out.getValue(), date.toString(), lastPayment.toString(), out.getStatus());

            dtoList.add(outDTO);
        });

        return dtoList;
    }

    public static FixedExpenseDTO expenseAdapter(FixedExpense oldExpense){
        return new FixedExpenseDTO(oldExpense.getId(), oldExpense.getReason(), oldExpense.getValue(),
                oldExpense.getDate().toString(), oldExpense.getLastPayment().toString(), oldExpense.getStatus());
    }
}
