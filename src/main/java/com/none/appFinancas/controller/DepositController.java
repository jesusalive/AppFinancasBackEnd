package com.none.appFinancas.controller;

import com.none.appFinancas.dto.DateFormDTO;
import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.dto.DepositFormDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.service.DepositService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {

    @Autowired
    private DepositService depositService;

    @GetMapping("/deposits/{userId}")
    public List<DepositDTO> getAllDeposits(@PathVariable Long userId){
        return depositService.findAllDeposits(userId);
    }

    @GetMapping("/deposits/fixed/{userId}")
    public List<DepositDTO> getAllFixedDeposits(@PathVariable Long userId){
        return depositService.findAllFixedDeposits(userId);
    }

    @GetMapping("/deposits/month")
    public List<DepositDTO> getAllDepositsOfMonthAndYear(@RequestBody DateFormDTO monthAndYear){
        String[] date = monthAndYear.getMonthAndYear().split("-");
        return depositService.findAllDepositsOfMonthAndYear(monthAndYear.getUserId(), date[1], date[0]);
    }

    @PostMapping("/deposits")
    public Deposit createDeposit(@RequestBody DepositFormDTO deposit){
        return depositService.createDeposit(deposit.getUserId(), deposit.getReason(),
                deposit.getValue(), deposit.getDate(), deposit.getFixed());
    }

    @DeleteMapping("/deposits/{depositId}")
    public void deleteDeposit(@PathVariable Long depositId){
        depositService.deleteDeposit(depositId);
    }
}
