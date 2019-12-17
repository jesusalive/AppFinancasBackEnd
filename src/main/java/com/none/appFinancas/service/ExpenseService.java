package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DeposityAdapter;
import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.dto.ExpenseFormDTO;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.repository.ExpenseRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

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

    public List<ExpenseDTO> findAllFixedExpenses(Long userId){
        User user =  userService.findOne(userId);
        return ExpenseAdapter.outListAdapter(expenseRepository.findByUserAndFixed(user, true));
    }

    public List<ExpenseDTO> allUserOuts(Long userId){
        User user = userService.findOne(userId);
        List<Expense> oldList = expenseRepository.findByUser(user);

        return ExpenseAdapter.outListAdapter(oldList);
    }

    public Expense alterExpenseStatus(Long expenseId, ExpenseFormDTO alterations){
        Expense expense = expenseRepository
                .findById(expenseId).orElseThrow(() -> new RuntimeException("Despesa não encontrada"));

        expense.setStatus(alterations.getStatus());

        return expenseRepository.save(expense);
    }

    public Expense alterFixedExpense(Long expenseId, ExpenseFormDTO alterations){
        User user = userService.findOne(alterations.getUserId());
        Expense expense = expenseRepository.findByIdAndUser(expenseId, user).orElseThrow(() ->
               new RuntimeException("Despesa não encontrada!"));

        expense.setFixed(alterations.getFixed());

        return expenseRepository.save(expense);
    }

    public List<ExpenseDTO> findAllExpenseOfMonthAndYear(Long userId, String month, String year){
        User user = userService.findOne(userId);
        LocalDate startOfMonth = LocalDate.parse(year + "-" + month + "-" + "01");
        LocalDate endOfMonth = LocalDate.parse(year + "-" +
                month + '-' + startOfMonth.lengthOfMonth());

        return ExpenseAdapter.outListAdapter(
                expenseRepository.findByUserAndDateBetween(user, startOfMonth, endOfMonth));
    }

    public void deleteOut(Long outId) {
        expenseRepository.deleteById(outId);
    }
}
