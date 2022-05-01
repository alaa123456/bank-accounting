package com.bank.model;

import com.bank.exception.InvalidOperationException;

import java.math.BigDecimal;

public class Account {

    private Long id;
    private BigDecimal amount = new BigDecimal("0");
    private String rib;
    private User user;

    public Account() {
    }

    public Account(Long id, String rib, User user) {
        this.id = id;
        this.rib = rib;
        this.user = user;
    }

    public BigDecimal getAmount() {
        return amount;
    }

    public void setAmount(BigDecimal amount) {
        if (amount.compareTo(BigDecimal.ZERO) < 0 && this.amount.compareTo(amount.abs()) < 0)
            throw new InvalidOperationException("Invalid amount to retrieve");
        this.amount = this.amount.add(amount);
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getRib() {
        return rib;
    }

    public void setRib(String rib) {
        this.rib = rib;
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", amount=" + amount +
                ", rib='" + rib + '\'' +
                ", user=" + user +
                '}';
    }
}
