package com.bank.service;

import com.bank.exception.InvalidOperationException;
import com.bank.model.Data;
import com.bank.model.OperationStatus;
import com.bank.model.Transaction;
import com.bank.model.dto.RequestTransaction;
import com.bank.model.dto.ResponseTransaction;

import java.util.List;
import java.util.stream.Collectors;

public class TransactionServiceImpl implements TransactionService {

    private final AccountService accountService = new AccountServiceImpl();

    @Override
    public ResponseTransaction makeOperation(RequestTransaction requestTransaction) {
        var account = accountService.findAccountByRibAndUser(requestTransaction.getUserId(), requestTransaction.getRib());
        Transaction transaction = new Transaction();
        transaction.setAccount(account);
        transaction.setId(Data.transactions.size() + 1L);
        transaction.setAmount(requestTransaction.getAmount());
        transaction.setOperationType(requestTransaction.getOperationType());
        try {
            account.setAmount(requestTransaction.getAmount());
            transaction.setStatus(OperationStatus.ACCEPTED);
        } catch (InvalidOperationException e) {
            transaction.setStatus(OperationStatus.REJECTED);
        }
        Data.transactions.add(transaction);
        return new ResponseTransaction(
                transaction.getStatus(),
                requestTransaction.getAmount(),
                account.getRib(),
                transaction.getDate(),
                transaction.getOperationType()
        );
    }

    @Override
    public List<ResponseTransaction> getHistory(Long userId, String rib) {
        var account = accountService.findAccountByRibAndUser(userId, rib);

        return Data.transactions.stream().filter(transaction -> transaction.getAccount().equals(account))
                .map(transaction -> new ResponseTransaction(
                        transaction.getStatus(),
                        transaction.getAmount(),
                        account.getRib(),
                        transaction.getDate(),
                        transaction.getOperationType()
                        )

                )
                .collect(Collectors.toList());
    }


}
