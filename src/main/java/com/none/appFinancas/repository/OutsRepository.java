package com.none.appFinancas.repository;

import com.none.appFinancas.entity.Outs;
import com.none.appFinancas.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface OutsRepository extends JpaRepository<Outs, Long> {
    public List<Outs> findByUser(User user);
}
