package com.none.appFinancas.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "fixedExpenses")
public class FixedExpense {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(nullable = false, name = "owner")
    private User user;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private LocalDate lastPayment;

    @Column(nullable = false)
    private String status;

    public FixedExpense() {
    }

    public FixedExpense(User user, String reason, Double value, LocalDate date, LocalDate lastPayment) {
        this.user = user;
        this.reason = reason;
        this.value = value;
        this.date = date;
        this.lastPayment = lastPayment;
        this.status = "pending";
    }

    public Long getId() {
        return id;
    }

    public User getUser() {
        return user;
    }

    public String getReason() {
        return reason;
    }

    public Double getValue() {
        return value;
    }

    public LocalDate getDate() {
        return date;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setLastPayment(LocalDate lastPayment) {
        this.lastPayment = lastPayment;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
