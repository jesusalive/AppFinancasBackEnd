package com.none.appFinancas.dto;

import java.time.LocalDate;

public class ExpenseDTO {
    private String reason;
    private Double value;
    private LocalDate date;

    public ExpenseDTO(String reason, Double value, String date) {
        this.reason = reason;
        this.value = value;
        this.date = LocalDate.parse(date);
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

    @Override
    public String toString() {
        return "OutsDTO{" +
                "reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
