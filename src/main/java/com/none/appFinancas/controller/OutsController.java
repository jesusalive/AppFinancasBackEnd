package com.none.appFinancas.controller;

import com.none.appFinancas.dto.OutFormDTO;
import com.none.appFinancas.dto.OutsDTO;
import com.none.appFinancas.entity.Outs;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.service.OutsService;
import com.none.appFinancas.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class OutsController {

    @Autowired
    private OutsService outsService;

    @Autowired
    private UserService userService;

    @GetMapping("/outs/{userId}")
    public List<OutsDTO> getAllUserOuts(@PathVariable Long userId){
        return outsService.allUserOuts(userId);
    }

    @PostMapping("/outs")
    public Outs createOut(@RequestBody OutFormDTO out){
        return outsService.createOut(out.getUserId(), out.getReason(), out.getValue(), out.getDate());
    }

    @DeleteMapping("/outs/{outId}")
    public void deleteOut(@PathVariable Long outId){
        outsService.deleteOut(outId);
    }

}
