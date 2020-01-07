package com.none.appFinancas.service;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.dto.ExpenseFormDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.errors.IncorrectId;
import com.none.appFinancas.errors.IncorrectURL;
import com.none.appFinancas.repository.ExpenseRepository;
import com.none.appFinancas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.BadCredentialsException;
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
                .findById(expenseId).orElseThrow(() -> new IncorrectId("Despesa não encontrada"));

        expense.setStatus(alterations.getStatus());

        return expenseRepository.save(expense);
    }

    public Expense alterFixedExpense(Long expenseId, ExpenseFormDTO alterations){
        User user = userService.findOne(alterations.getUserId());
        Expense expense = expenseRepository.findByIdAndUser(expenseId, user).orElseThrow(() ->
               new IncorrectId("Despesa não encontrada!"));

        expense.setFixed(alterations.getFixed());

        return expenseRepository.save(expense);
    }

    public List<ExpenseDTO> findAllExpenseOfMonthAndYear(Long userId, String month, String year){
        User user = userService.findOne(userId);
        if(month.length() != 2 || year.length() != 4) {
            throw new IncorrectURL("Parametros incorretos");
        }
        LocalDate startOfMonth = LocalDate.parse(year + "-" + month + "-" + "01");
        LocalDate endOfMonth = LocalDate.parse(year + "-" +
                month + '-' + startOfMonth.lengthOfMonth());

        return ExpenseAdapter.outListAdapter(
                expenseRepository.findByUserAndFixedAndDateBetween(user, false, startOfMonth, endOfMonth));
    }

    public void deleteOut(Long outId) {
        Expense expense = expenseRepository.findById(outId).orElseThrow(
                () -> new IncorrectId("Despesa não encontrada")
        );
        verifyUserExpense(expense);
        expenseRepository.deleteById(outId);
    }

    private void verifyUserExpense(Expense expense) {
        UserSS loggedUser = UserService.loggedUser();
        if(loggedUser != null){
            if(!expense.getUser().getId().equals(loggedUser.getId())){
                throw new AuthError("Acesso negado!");
            }
        }
    }
}
