package com.none.appFinancas.controller;

import com.none.appFinancas.adapter.ExpenseAdapter;
import com.none.appFinancas.dto.DateFormDTO;
import com.none.appFinancas.dto.ExpenseFormDTO;
import com.none.appFinancas.dto.ExpenseDTO;
import com.none.appFinancas.entity.Expense;
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
        return outsService.allUserOuts(userId);
    }

    @GetMapping("/outs/{userId}/fixed")
    public List<ExpenseDTO> getAllFixedOuts(@PathVariable Long userId){
        return outsService.findAllFixedExpenses(userId);
    }

    @GetMapping("/outs/month")
    public List<ExpenseDTO> getAllExpenseOfMonthAndYear(@RequestBody DateFormDTO monthAndYear){
        String[] date = monthAndYear.getMonthAndYear().split("-");
        return outsService.findAllExpenseOfMonthAndYear(monthAndYear.getUserId(), date[1], date[0]);
    }

    @PostMapping("/outs")
    public Expense createOut(@RequestBody ExpenseFormDTO out){
        return outsService.createExpense(out.getUserId(), out.getReason(),
                out.getValue(), out.getDate(), out.getFixed());
    }

    @PutMapping("/outs/status/{expenseId}")
    public ExpenseDTO alterStatusOfExpense(@PathVariable Long expenseId, @RequestBody ExpenseFormDTO alterations){
        return ExpenseAdapter.expenseAdapter(outsService.alterExpenseStatus(expenseId, alterations));
    }

    @PutMapping("/outs/fixed/{expenseId}")
    public ExpenseDTO alterFixedExpense(@PathVariable Long expenseId, @RequestBody ExpenseFormDTO alterations){
        return ExpenseAdapter.expenseAdapter(outsService.alterFixedExpense(expenseId, alterations));
    }

    @DeleteMapping("/outs/{outId}")
    public void deleteOut(@PathVariable Long outId){
        outsService.deleteOut(outId);
    }

}
