package com.bank.service;

import com.bank.model.Account;

import java.math.BigDecimal;

public interface AccountService {
    Account findAccountByRibAndUser(Long userId, String rib);
    BigDecimal getBalance(Long userId, String rib);
}
