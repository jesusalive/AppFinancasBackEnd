package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DeposityAdapter;
import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.dto.DeposityFormDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.DeposityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeposityService {

    @Autowired
    private DeposityRepository deposityRepository;

    @Autowired
    private UserService userService;

    public Deposity createDeposity(Long userId, String reason, Double value, String date){
        User user = userService.findOne(userId);

        return deposityRepository.save(new Deposity(user, reason, value, LocalDate.parse(date)));
    }

    public List<DeposityDTO> findAllDeposities(Long userId){
        User user = userService.findOne(userId);
        List<Deposity> oldList = deposityRepository.findByUser(user);

        return DeposityAdapter.deposityListAdapter(oldList);
    }
}
