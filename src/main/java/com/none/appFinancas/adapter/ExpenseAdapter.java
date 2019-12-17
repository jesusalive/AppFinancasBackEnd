package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.entity.Expense;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class ExpenseAdapter {
    public static List<ExpenseDTO> outListAdapter(List<Expense> outList){
        List<ExpenseDTO> dtoList = new ArrayList<>();

        outList.forEach(out -> {
            LocalDate date = out.getDate().plusDays(1);

            ExpenseDTO outDTO = new ExpenseDTO(out.getReason(),
                    out.getValue(), date.toString(), out.getStatus());

            dtoList.add(outDTO);
        });

        return dtoList;
    }

    public static ExpenseDTO expenseAdapter(Expense oldExpense){
        return new ExpenseDTO(oldExpense.getReason(), oldExpense.getValue(),
                oldExpense.getDate().toString(), oldExpense.getStatus());
    }
}
