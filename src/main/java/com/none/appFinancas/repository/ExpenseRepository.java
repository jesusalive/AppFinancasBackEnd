package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Expense;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ExpenseRepository extends JpaRepository<Expense, Long> {
    public List<Expense> findByUser(User user);
}
