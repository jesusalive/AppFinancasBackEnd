package com.none.appFinancas.service;

import com.none.appFinancas.adapter.DepositAdapter;
import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.dto.DepositFormDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import com.none.appFinancas.errors.AtributeNullException;
import com.none.appFinancas.repository.DepositRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.List;

@Service
public class DepositService {

    @Autowired
    private DepositRepository depositRepository;

    @Autowired
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

        return DepositAdapter.deposityListAdapter(oldList);
    }

    public void deleteDeposit(Long id) {
        depositRepository.deleteById(id);
    }

    public List<DepositDTO> findAllFixedDeposits(Long userId) {
        User user = userService.findOne(userId);
        return DepositAdapter.deposityListAdapter(
                depositRepository.findByUserAndFixed(user, true));
    }

    public Deposit alterFixedDeposit(Long depositId, DepositFormDTO alterations){
        User user = userService.findOne(alterations.getUserId());
        Deposit deposit = depositRepository.findByIdAndUser(depositId, user).orElseThrow(() ->
                new RuntimeException("Entrada de valor não encontrada!"));

        deposit.setFixed(alterations.getFixed());

        return depositRepository.save(deposit);
    }

    public List<DepositDTO> findAllDepositsOfMonthAndYear(Long userId, String month, String year){
        User user = userService.findOne(userId);
        LocalDate startOfMonth = LocalDate.parse(year + "-" + month + "-" + "01");
        LocalDate endOfMonth = LocalDate.parse(year + "-" +
                month + '-' + startOfMonth.lengthOfMonth());

        return DepositAdapter.deposityListAdapter(
                depositRepository.findByUserAndDateBetween(user, startOfMonth, endOfMonth));
    }
}
