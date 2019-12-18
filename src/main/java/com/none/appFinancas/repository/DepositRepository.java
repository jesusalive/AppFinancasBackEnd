package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Deposit;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface DepositRepository extends JpaRepository<Deposit, Long> {

    List<Deposit> findByUser(User user);
    List<Deposit> findByUserAndFixed(User user, Boolean fixed);
    Optional<Deposit> findByIdAndUser(Long id, User user);
    List<Deposit> findByUserAndDateBetween(User user, LocalDate startDate, LocalDate endDate);
}
