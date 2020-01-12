package com.none.appFinancas.service;

import com.none.appFinancas.entity.FixedExpense;
import com.none.appFinancas.repository.FixedExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class Jobs {

    @Autowired
    private FixedExpenseRepository expenseRepository;

    public void turnExpenseStatusToPending(){
        List<FixedExpense> expenses = expenseRepository.findAll();

        expenses.forEach(expense -> {
            expense.setStatus("pending");
            expenseRepository.save(expense);
        });
    }
}
