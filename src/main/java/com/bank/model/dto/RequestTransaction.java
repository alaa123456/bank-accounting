package com.bank.model.dto;

import com.bank.model.OperationType;

import java.math.BigDecimal;

public class RequestTransaction {
    private BigDecimal amount;
    private OperationType operationType;
    private Long userId;
    private String rib;

    public RequestTransaction() {
    }

    public RequestTransaction(BigDecimal amount, OperationType operationType, Long userId, String rib) {
        this.amount = amount;
        this.operationType = operationType;
        this.userId = userId;
        this.rib = rib;
    }

    public OperationType getOperationType() {
        return operationType;
    }

    public void setOperationType(OperationType operationType) {
        this.operationType = operationType;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        this.amount = amount;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }
}
