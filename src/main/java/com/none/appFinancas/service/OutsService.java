package com.none.appFinancas.service;

import com.none.appFinancas.entity.Outs;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.OutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class OutsService {

    @Autowired
    private OutsRepository outsRepository;

    @Autowired
    private UserService userService;

    public Outs createOut(Long userId, String reason, Double value, String date){
        User user = userService.findOne(userId);

        return outsRepository.save(new Outs(user, reason, value, LocalDate.parse(date)));
    }
}
