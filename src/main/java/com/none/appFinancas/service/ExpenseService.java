package com.none.appFinancas.service;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class ExpenseService {

    @Autowired
    private ExpenseRepository expenseRepository;

    @Autowired
    private UserService userService;

    public Expense createExpense(Long userId, String reason, Double value, String date, Boolean fixed){
        try {
            User user = userService.findOne(userId);

            return expenseRepository.save(new Expense(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date), fixed));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
        }
    }

    public List<ExpenseDTO> allUserOuts(Long userId){
        User user = userService.findOne(userId);
        List<Expense> oldList = expenseRepository.findByUser(user);

        return ExpenseAdapter.outListAdapter(oldList);
    }

    public void deleteOut(Long outId) {
        expenseRepository.deleteById(outId);
    }
}
