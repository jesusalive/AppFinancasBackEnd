package com.none.appFinancas.dto;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

public class ExpenseDTO {
    private String reason;
    private Double value;
    private LocalDate date;
    private String status;

    public ExpenseDTO(String reason, Double value, String date, String status) {
        this.reason = reason;
        this.value = value;
        this.date = LocalDate.parse(date);
        this.status = status;
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

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "ExpenseDTO{" +
                "reason='" + reason + '\'' +
                ", value=" + value +
                ", date=" + date +
                ", status='" + status + '\'' +
                '}';
    }
}
