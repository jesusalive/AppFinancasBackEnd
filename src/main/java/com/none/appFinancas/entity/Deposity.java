package com.none.appFinancas.entity;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "deposities")
public class Deposity {

    @Id
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @Column(nullable = false)
    private String reason;

    @Column(nullable = false)
    private Double value;

    @Column(nullable = false)
    private String date;

    public Deposity() {
    }

    public Deposity(User user, String reason, Double value, String date) {
        this.user = user;
        this.reason = reason;
        this.value = value;
        this.date = date;
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

    public String getDate() {
        return date;
    }

    @Override
    public String toString() {
        return "Deposity{" +
                "user=" + user +
                ", reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
