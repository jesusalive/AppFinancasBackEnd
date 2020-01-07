package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    List<Expense> findByUser(User user);
    Optional<Expense> findByIdAndUser(Long expenseId, User user);
    List<Expense> findByUserAndFixed(User user, Boolean fixed);
    List<Expense> findByUserAndFixedAndDateBetween(User user, Boolean fixed, LocalDate startDate, LocalDate endDate);
}
