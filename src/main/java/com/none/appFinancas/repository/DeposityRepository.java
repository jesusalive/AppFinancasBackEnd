package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DeposityRepository extends JpaRepository<Deposity, Long> {

    List<Deposity> findByUser(User user);
    List<Deposity> findByUserAndFixed(User user, Boolean fixed);
    List<Deposity> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
