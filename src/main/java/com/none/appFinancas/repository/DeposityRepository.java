package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Deposity;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface DeposityRepository extends JpaRepository<Deposity, User> {
}
