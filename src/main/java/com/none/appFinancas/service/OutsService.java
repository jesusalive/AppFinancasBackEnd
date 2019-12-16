package com.none.appFinancas.service;

import com.none.appFinancas.adapter.OutsAdapter;
import com.none.appFinancas.dto.OutsDTO;
import com.none.appFinancas.entity.Outs;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.repository.OutsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

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

    public List<OutsDTO> allUserOuts(Long userId){
        User user = userService.findOne(userId);
        List<Outs> oldList = outsRepository.findByUser(user);

        return OutsAdapter.outListAdapter(oldList);
    }

    public void deleteOut(Long outId) {
        outsRepository.deleteById(outId);
    }
}
