package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DeposityAdapter;
import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.repository.DeposityRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DeposityService {

    @Autowired
    private DeposityRepository deposityRepository;

    @Autowired
    private UserService userService;

    public Deposity createDeposity(Long userId, String reason, Double value, String date, Boolean fixed){
        try {
            User user = userService.findOne(userId);

            return deposityRepository.save(new Deposity(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date), fixed));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
        }
    }

    public List<DeposityDTO> findAllDeposities(Long userId){
        User user = userService.findOne(userId);
        List<Deposity> oldList = deposityRepository.findByUser(user);

        return DeposityAdapter.deposityListAdapter(oldList);
    }

    public void deleteDeposity(Long id) {
        deposityRepository.deleteById(id);
    }

}
