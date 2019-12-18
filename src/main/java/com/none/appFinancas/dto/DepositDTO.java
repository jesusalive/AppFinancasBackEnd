package com.none.appFinancas.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class DepositDTO {

    private String reason;
    private Double value;
    private LocalDate date;

    public DepositDTO(String reason, Double value, String date) {
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
        return "DepositDTO{" +
                "reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                '}';
    }
}
