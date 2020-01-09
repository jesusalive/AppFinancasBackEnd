package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.*;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.FixedExpense;
import com.none.appFinancas.security.VerifyLoggedUser;
import com.none.appFinancas.service.ExpenseService;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class ExpenseController {
    @Autowired
    private ExpenseService outsService;

    @Autowired
    private UserService userService;

    @GetMapping("/outs/{userId}")
    public List<ExpenseDTO> getAllUserOuts(@PathVariable Long userId){
        VerifyLoggedUser.verifyLoggedUser(userId);
        return outsService.allUserOuts(userId);
    }

    @GetMapping("/fixedouts/{userId}")
    public List<FixedExpenseDTO> getAllFixedOuts(@PathVariable Long userId){
        VerifyLoggedUser.verifyLoggedUser(userId);
        return outsService.findAllFixedExpenses(userId);
    }

    @GetMapping("/outs/{userId}/{month}/{year}")
    public List<ExpenseDTO> getAllExpensesOfMonthAndYear(@PathVariable Long userId,
                                                         @PathVariable String month,
                                                         @PathVariable String year ){
        VerifyLoggedUser.verifyLoggedUser(userId);
        return outsService.findAllExpenseOfMonthAndYear(userId, month, year);
    }

    @PostMapping("/outs")
    public Expense createOut(@RequestBody ExpenseFormDTO out){
        VerifyLoggedUser.verifyLoggedUser(out.getUserId());
        return outsService.createExpense(out.getUserId(), out.getReason(),
                out.getValue(), out.getDate());
    }

    @PostMapping("/fixedouts")
    public FixedExpense createFixedOut(@RequestBody ExpenseFormDTO out){
        VerifyLoggedUser.verifyLoggedUser(out.getUserId());
        return outsService.createFixedExpense(out.getUserId(), out.getReason(),
                out.getValue(), out.getDate());
    }

    @PutMapping("/fixedouts/{expenseId}")
    public FixedExpense alterFixedExpense(@PathVariable Long expenseId,
                                          @RequestBody AlterFixedExpenseFormDTO alterations){
        return outsService.alterFixedExpense(expenseId, alterations);
    }

    @DeleteMapping("/fixedouts/{userId}/{expenseId}")
    public void removeFixedExpense(@PathVariable Long expenseId, @PathVariable Long userId){
        VerifyLoggedUser.verifyLoggedUser(userId);
        outsService.removeFixedExpense(userId, expenseId);
    }

}
