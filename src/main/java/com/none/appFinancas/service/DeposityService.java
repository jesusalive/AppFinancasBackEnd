package com.none.appFinancas.service;

import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.repository.DeposityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Service
public class DeposityService {

    @Autowired
    private DeposityRepository deposityRepository;

    @PostMapping("/deposities")
    public Deposity createDeposity(@RequestBody Long userId, @RequestBody String reason, @RequestBody Double value){

    }
}
