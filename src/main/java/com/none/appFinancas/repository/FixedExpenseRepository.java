package com.none.appFinancas.repository;

import com.none.appFinancas.entity.FixedExpense;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface FixedExpenseRepository extends JpaRepository<FixedExpense, Long> {
    List<FixedExpense> findByUser(User user);
    Optional<FixedExpense> findByIdAndUser(Long id, User user);
}