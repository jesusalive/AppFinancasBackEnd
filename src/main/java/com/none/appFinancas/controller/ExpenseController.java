package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.DateFormDTO;
import com.none.appFinancas.dto.ExpenseFormDTO;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.entity.Expense;
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

    @GetMapping("/outs/fixed/{userId}")
    public List<ExpenseDTO> getAllFixedOuts(@PathVariable Long userId){
        VerifyLoggedUser.verifyLoggedUser(userId);
        return outsService.findAllFixedExpenses(userId);
    }

    @GetMapping("/outs/month")
    public List<ExpenseDTO> getAllExpenseOfMonthAndYear(@RequestBody DateFormDTO monthAndYear){
        VerifyLoggedUser.verifyLoggedUser(monthAndYear.getUserId());
        String[] date = monthAndYear.getMonthAndYear().split("-");
        return outsService.findAllExpenseOfMonthAndYear(monthAndYear.getUserId(), date[1], date[0]);
    }

    @PostMapping("/outs")
    public Expense createOut(@RequestBody ExpenseFormDTO out){
        VerifyLoggedUser.verifyLoggedUser(out.getUserId());
        return outsService.createExpense(out.getUserId(), out.getReason(),
                out.getValue(), out.getDate(), out.getFixed());
    }

    @PutMapping("/outs/{expenseId}/status")
    public ExpenseDTO alterStatusOfExpense(@PathVariable Long expenseId, @RequestBody ExpenseFormDTO alterations){
        VerifyLoggedUser.verifyLoggedUser(alterations.getUserId());
        return ExpenseAdapter.expenseAdapter(outsService.alterExpenseStatus(expenseId, alterations));
    }

    @PutMapping("/outs/{expenseId}/fixed")
    public ExpenseDTO alterFixedExpense(@PathVariable Long expenseId, @RequestBody ExpenseFormDTO alterations){
        VerifyLoggedUser.verifyLoggedUser(alterations.getUserId());
        return ExpenseAdapter.expenseAdapter(outsService.alterFixedExpense(expenseId, alterations));
    }

    @DeleteMapping("/outs/{outId}")
    public void deleteOut(@PathVariable Long outId){
        outsService.deleteOut(outId);
    }

}
