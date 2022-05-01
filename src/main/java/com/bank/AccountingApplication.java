package com.bank;

import com.bank.model.Account;
import com.bank.model.Data;
import com.bank.model.OperationType;
import com.bank.model.User;
import com.bank.model.dto.RequestTransaction;
import com.bank.model.dto.ResponseTransaction;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.service.TransactionService;
import com.bank.service.TransactionServiceImpl;

import java.math.BigDecimal;

public class AccountingApplication {
    private static final Long USER_ID = 1L;
    private static final String USER_NAME = "Alaa";
    private static final String ACCOUNT_RIB = "12345678";

    private static void generateAccounts() {
        Data.accounts.add(new Account(
                1L,
                ACCOUNT_RIB,
                new User(USER_ID, USER_NAME)
        ));
    }
    public static void main(String[] args) {
        System.out.println("Prepare Data");
        generateAccounts();

        TransactionService transactionService = new TransactionServiceImpl();
        AccountService accountService = new AccountServiceImpl();
        System.out.println("///////////////");
        System.out.println("User request to add money");
        System.out.println("Prepare request to save money");
        RequestTransaction requestTransaction = new RequestTransaction(
                new BigDecimal("1000"),
                OperationType.DEPOSIT,
                USER_ID,
                ACCOUNT_RIB

        );
        ResponseTransaction transaction = transactionService.makeOperation(requestTransaction);
        System.out.println(transaction);


        System.out.println("///////////");
        System.out.println("User request to retrieve money");
        requestTransaction.setOperationType(OperationType.WITHDRAWAL);
        requestTransaction.setAmount(new BigDecimal("-500"));
        System.out.println(transactionService.makeOperation(requestTransaction));


        System.out.println("//////////");
        System.out.println("User request to retrieve money greater than the balance account");
        requestTransaction.setOperationType(OperationType.WITHDRAWAL);
        requestTransaction.setAmount(new BigDecimal("-2000"));
        System.out.println(transactionService.makeOperation(requestTransaction));

        System.out.println("//////////");
        System.out.println("User request get History of transactions");

        var history = transactionService.getHistory(USER_ID, ACCOUNT_RIB);
        System.out.println("history of transactions for user "+ USER_NAME + ":");
        System.out.println(history);

        System.out.println("total balance: " + accountService.getBalance(USER_ID, ACCOUNT_RIB));


	// write your code here
    }
}
