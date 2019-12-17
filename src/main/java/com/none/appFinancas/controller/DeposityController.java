package com.none.appFinancas.controller;

import com.none.appFinancas.dto.DateFormDTO;
import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.dto.DeposityFormDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.DeposityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;

@RestController
public class DeposityController {

    @Autowired
    private DeposityService deposityService;

    @GetMapping("/deposities/{userId}")
    public List<DeposityDTO> getAllDeposities(@PathVariable Long userId){
        return deposityService.findAllDeposities(userId);
    }

    @GetMapping("/deposities/{userId}/fixed")
    public List<DeposityDTO> getAllFixedDeposities(@PathVariable Long userId){
        return deposityService.findAllFixedDeposities(userId);
    }

    @GetMapping("/deposities/month")
    public List<DeposityDTO> getAllDepositiesOfMonthAndYear(@RequestBody DateFormDTO monthAndYear){
        String[] date = monthAndYear.getMonthAndYear().split("-");
        return deposityService.findAllDepositiesOfMonthAndYear(monthAndYear.getUserId(), date[1], date[0]);
    }

    @PostMapping("/deposities")
    public Deposity createDeposity(@RequestBody DeposityFormDTO deposity){
        return deposityService.createDeposity(deposity.getUserId(), deposity.getReason(),
                deposity.getValue(), deposity.getDate(), deposity.getFixed());
    }

    @DeleteMapping("/deposities/{deposityId}")
    public void deleteDeposity(@PathVariable Long deposityId){
        deposityService.deleteDeposity(deposityId);
    }
}
