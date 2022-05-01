package com.bank.service;

import com.bank.model.dto.RequestTransaction;
import com.bank.model.dto.ResponseTransaction;

import java.util.List;


public interface TransactionService {

    ResponseTransaction makeOperation(RequestTransaction requestTransaction);
    List<ResponseTransaction> getHistory(Long userId, String rib);

}
