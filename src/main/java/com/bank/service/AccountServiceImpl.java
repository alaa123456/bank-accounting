package com.bank.service;

import com.bank.exception.EntityNotFoundException;
import com.bank.model.Account;
import com.bank.model.Data;

import java.math.BigDecimal;


public class AccountServiceImpl implements AccountService {
    @Override
    public Account findAccountByRibAndUser(Long userId, String rib) {
        return Data.accounts.stream()
                .filter(ele -> (ele.getUser().getId().equals(userId) && ele.getRib().equals(rib)))
                .findFirst()
                .orElseThrow(() -> new EntityNotFoundException("Account not found !"));
    }

    @Override
    public BigDecimal getBalance(Long userId, String rib) {
        return findAccountByRibAndUser(userId,rib).getAmount();
    }


}
