package com.none.appFinancas.dto;

import java.time.LocalDate;

public class FixedExpenseDTO {

    private Long id;
    private String reason;
    private Double value;
    private LocalDate date;
    private LocalDate lastPayment;
    private String status;

    public FixedExpenseDTO(Long id, String reason, Double value, String date, String lastPayment, String status) {
        this.id = id;
        this.reason = reason;
        this.value = value;
        this.date = LocalDate.parse(date);
        this.lastPayment = LocalDate.parse(lastPayment);
        this.status = status;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public Double getValue() {
        return value;
    }

    public void setValue(Double value) {
        this.value = value;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public LocalDate getLastPayment() {
        return lastPayment;
    }

    public void setLastPayment(LocalDate lastPayment) {
        this.lastPayment = lastPayment;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
