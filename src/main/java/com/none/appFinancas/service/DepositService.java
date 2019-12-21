package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DepositAdapter;
import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.dto.DepositFormDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.errors.AuthError;
import com.none.appFinancas.errors.IncorrectId;
import com.none.appFinancas.repository.DepositRepository;
import com.none.appFinancas.security.UserSS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
    @Lazy
    private UserService userService;

    public Deposit createDeposit(Long userId, String reason, Double value, String date, Boolean fixed){
        try {
            User user = userService.findOne(userId);

            return depositRepository.save(new Deposit(user, reason, value,
                    date.trim().isEmpty() ? null : LocalDate.parse(date), fixed));
        }catch(RuntimeException e){
            throw new AtributeNullException(e.getMessage());
        }
    }

    public List<DepositDTO> findAllDeposits(Long userId){
        User user = userService.findOne(userId);
        List<Deposit> oldList = depositRepository.findByUser(user);

        return DepositAdapter.depositListAdapter(oldList);
    }

    public void deleteDeposit(Long id) {
        Deposit deposit = depositRepository.findById(id).orElseThrow(() ->
                new IncorrectId("Entrada não encontrada"));
        verifyUserDeposit(deposit);
        depositRepository.deleteById(id);
    }

    private void verifyUserDeposit(Deposit deposit) {
        UserSS loggedUser = UserService.loggedUser();
        if(loggedUser != null){
            if(!deposit.getUser().getId().equals(loggedUser.getId())){
                throw new AuthError("Acesso negado!");
            }
        }
    }

    public List<DepositDTO> findAllFixedDeposits(Long userId) {
        User user = userService.findOne(userId);
        return DepositAdapter.depositListAdapter(
                depositRepository.findByUserAndFixed(user, true));
    }

    public Deposit alterFixedDeposit(Long depositId, DepositFormDTO alterations){
        User user = userService.findOne(alterations.getUserId());
        Deposit deposit = depositRepository.findByIdAndUser(depositId, user).orElseThrow(() ->
                new IncorrectId("Entrada de valor não encontrada!"));

        deposit.setFixed(alterations.getFixed());

        return depositRepository.save(deposit);
    }

    public List<DepositDTO> findAllDepositsOfMonthAndYear(Long userId, String month, String year){
        User user = userService.findOne(userId);
        LocalDate startOfMonth = LocalDate.parse(year + "-" + month + "-" + "01");
        LocalDate endOfMonth = LocalDate.parse(year + "-" +
                month + '-' + startOfMonth.lengthOfMonth());

        return DepositAdapter.depositListAdapter(
                depositRepository.findByUserAndDateBetween(user, startOfMonth, endOfMonth));
    }
}
