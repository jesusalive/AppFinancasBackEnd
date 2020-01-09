package com.none.appFinancas.service;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.adapter.FixedExpenseAdapter;
import com.none.appFinancas.dto.AlterFixedExpenseFormDTO;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.dto.ExpenseFormDTO;
import com.none.appFinancas.dto.FixedExpenseDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.FixedExpense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.*;
import com.none.appFinancas.repository.ExpenseRepository;
import com.none.appFinancas.repository.FixedExpenseRepository;
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
    private FixedExpenseRepository fixedExpenseRepository;

    @Autowired
    private UserService userService;

    public Expense createExpense(Long userId, String reason, Double value, String date){
        try {
            User user = userService.findOne(userId);

            return expenseRepository.save(new Expense(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date)));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
        }
    }

    public FixedExpense createFixedExpense(Long userId, String reason, Double value, String date){
        try {
            User user = userService.findOne(userId);

            return fixedExpenseRepository.save(new FixedExpense(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date), LocalDate.now()));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
        }
    }

    public List<FixedExpenseDTO> findAllFixedExpenses(Long userId){
        User user =  userService.findOne(userId);
        return FixedExpenseAdapter.outListAdapter(fixedExpenseRepository.findByUser(user));
    }

    public List<ExpenseDTO> allUserOuts(Long userId){
        User user = userService.findOne(userId);
        List<Expense> oldList = expenseRepository.findByUser(user);

        return ExpenseAdapter.outListAdapter(oldList);
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
                expenseRepository.findByUserAndDateBetween(user, startOfMonth, endOfMonth));
    }

    public FixedExpense alterFixedExpense(Long expenseId, AlterFixedExpenseFormDTO alterations) {
        User user = userService.findOne(alterations.getUserId());
        FixedExpense expense = fixedExpenseRepository.findById(expenseId).orElseThrow(() ->
                new IncorrectId("Despesa não encontrada!"));

        if (!alterations.getLastPayment().trim().isEmpty() && alterations.getLastPayment().length() == 10) {
            expense.setLastPayment(LocalDate.parse(alterations.getLastPayment()));
        }

        if (!alterations.getStatus().trim().isEmpty()) {
            expense.setStatus(alterations.getStatus());
        }


        try {
            return fixedExpenseRepository.save(expense);
        }catch (RuntimeException e){
            throw new BadCredentials("Houve um erro inesperado, verifique os dados da requisição!");
        }
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

    public void removeFixedExpense(Long userId, Long expenseId) {
        User user = userService.findOne(userId);
        FixedExpense expense = fixedExpenseRepository.findByIdAndUser(expenseId, user).orElseThrow(() ->
                new BadCredentials("Despesa não encontrada"));

        fixedExpenseRepository.deleteById(expenseId);
    }
}
