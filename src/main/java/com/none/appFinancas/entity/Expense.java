package com.none.appFinancas.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "expenses")
public class Expense {

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

    public Expense() {
    }

    public Expense(User user, String reason, Double value, LocalDate date) {
        if(reason.trim().isEmpty()){
            throw new RuntimeException("Motivo não pode ser nulo");
        }

        if(value <= 0.0){
            throw new RuntimeException("Valor não pode ser nulo(a), igual, ou menor que zero!");
        }

        this.user = user;
        this.reason = reason;
        this.value = value;
        this.date = date;
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

    @Override
    public String toString() {
        return "Expense{" +
                "id=" + id +
                ", user=" + user +
                ", reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
