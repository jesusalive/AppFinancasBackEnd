package com.none.appFinancas.controller;

import com.none.appFinancas.dto.DateFormDTO;
import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.dto.DepositFormDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.security.UserSS;
import com.none.appFinancas.service.DepositService;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class DepositController {

    @Autowired
    @Lazy
    private DepositService depositService;

    @GetMapping("/deposits/{userId}")
    public List<DepositDTO> getAllDeposits(@PathVariable Long userId){
        verifyLoggedUser(userId);
        return depositService.findAllDeposits(userId);
    }

    @GetMapping("/deposits/fixed/{userId}")
    public List<DepositDTO> getAllFixedDeposits(@PathVariable Long userId){
        verifyLoggedUser(userId);
        return depositService.findAllFixedDeposits(userId);
    }

    @GetMapping("/deposits/month")
    public List<DepositDTO> getAllDepositsOfMonthAndYear(@RequestBody DateFormDTO monthAndYear){
        verifyLoggedUser(monthAndYear.getUserId());
        String[] date = monthAndYear.getMonthAndYear().split("-");
        return depositService.findAllDepositsOfMonthAndYear(monthAndYear.getUserId(), date[1], date[0]);
    }

    @PostMapping("/deposits")
    public Deposit createDeposit(@RequestBody DepositFormDTO deposit){
        verifyLoggedUser(deposit.getUserId());
        return depositService.createDeposit(deposit.getUserId(), deposit.getReason(),
                deposit.getValue(), deposit.getDate(), deposit.getFixed());
    }

    @PutMapping("/deposits/{depositId}/fixed")
    public Deposit alterFixedStatusOfDeposit(@PathVariable Long depositId, @RequestBody DepositFormDTO alterations){
        verifyLoggedUser(alterations.getUserId());
        return depositService.alterFixedDeposit(depositId, alterations);
    }

    @DeleteMapping("/deposits/{depositId}")
    public void deleteDeposit(@PathVariable Long depositId){
        depositService.deleteDeposit(depositId);
    }

    public void verifyLoggedUser(Long id){
        UserSS loggedUser = UserService.loggedUser();

        if(loggedUser == null || !id.equals(loggedUser.getId())){
            throw new AuthError("Acesso negado!");
        }

    }

}
