package com.none.appFinancas.adapter;

import com.none.appFinancas.dto.DeposityDTO;
import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class DeposityAdapterTest {

    @Test
    public void deveCriarUmaListaDeEntradasDTO(){
        User user = new User("Matheus", "jesusalive", "210900");
        Deposity deposity1 = new Deposity(user, "Lanche", 20d, LocalDate.now());
        Deposity deposity2 = new Deposity(user, "Nada", 20d, LocalDate.now());

        List<Deposity> oldList = new ArrayList<>(
                Arrays.asList(deposity1, deposity2)
        );

        List<DeposityDTO> adaptedList = DeposityAdapter.deposityListAdapter(oldList);

        System.out.println(adaptedList);

        assertNotNull(adaptedList);
    }

}