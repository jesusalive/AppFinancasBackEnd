package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DeposityAdapter;
import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.erros.ErrorModel;
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

    public Object createDeposity(Long userId, String reason, Double value, String date){
        try{
            User user = userService.findOne(userId);

            return deposityRepository.save(new Deposity(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date)));
        }catch(RuntimeException e){
            return new ErrorModel(e.getMessage());
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
