package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.DepositDTO;
import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DepositAdapterTest {

    @Test
    public void deveCriarUmaListaDeEntradasDTO(){
        User user = new User("Matheus", "jesusalive", "210900");
        Deposit deposit1 = new Deposit(user, "Lanche", 20d, LocalDate.now(),true);
        Deposit deposit2 = new Deposit(user, "Nada", 20d, LocalDate.now(), true);

        List<Deposit> oldList = new ArrayList<>(
                Arrays.asList(deposit1, deposit2)
        );

        List<DepositDTO> adaptedList = DepositAdapter.depositListAdapter(oldList);

        System.out.println(adaptedList);

        assertNotNull(adaptedList);
    }

}