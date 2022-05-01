package com.bank.model.dto;

import com.bank.model.OperationStatus;
import com.bank.model.OperationType;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class ResponseTransaction {
    private OperationStatus operationStatus;
    private BigDecimal amount;
    private String rib;
    private LocalDateTime date;
    private OperationType operationType;

    public ResponseTransaction() {
    }

    public ResponseTransaction(OperationStatus operationStatus, BigDecimal amount, String rib,
                               LocalDateTime date, OperationType operationType) {
        this.operationStatus = operationStatus;
        this.amount = amount;
        this.rib = rib;
        this.date = date;
        this.operationType = operationType;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public OperationStatus getOperationStatus() {
        return operationStatus;
    }

    public void setOperationStatus(OperationStatus operationStatus) {
        this.operationStatus = operationStatus;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    @Override
    public String toString() {
        return "{" +
                "operationType=" + operationType +
                ", operationStatus=" + operationStatus +
                ", amount=" + amount +
                ", rib='" + rib + '\'' +
                ", date=" + date +
                '}'+"\n";
    }
}
