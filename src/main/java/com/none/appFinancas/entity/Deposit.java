package com.none.appFinancas.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deposities")
public class Deposit {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "user", nullable = false)
    private User user;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private LocalDate date;

    @Column(nullable = false)
    private Boolean fixed;

    public Deposit() {
    }

    public Deposit(User user, String reason, Double value, LocalDate date, Boolean fixed) {
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
        this.fixed = fixed;
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

    public Long getId() {
        return id;
    }

    public LocalDate getDate() {
        return date;
    }

    public Boolean getFixed(){ return fixed; }

    @Override
    public String toString() {
        return "Deposit{" +
                "id=" + id +
                ", user=" + user +
                ", reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", fixed=" + fixed +
                '}';
    }
}
