package com.bank;

import com.bank.exception.EntityNotFoundException;
import com.bank.model.*;
import com.bank.model.dto.RequestTransaction;
import com.bank.service.AccountService;
import com.bank.service.AccountServiceImpl;
import com.bank.service.TransactionService;
import com.bank.service.TransactionServiceImpl;
import org.junit.jupiter.api.*;

import java.math.BigDecimal;
import java.util.List;

@TestMethodOrder(MethodOrderer.OrderAnnotation.class)
class TransactionServiceTest {
    // static data
    private static final String RIB_TEST = "123456rib";
    private static final Long USER_ID_TEST = 1L;
    private static final String USER_NAME_TEST = "Alaa";
    private static final BigDecimal AMOUNT_DEPO = new BigDecimal("1000");
    private static final BigDecimal AMOUNT_RETRIEVE = new BigDecimal("-500");

    Account accountTest = new Account(USER_ID_TEST, RIB_TEST, new User(USER_ID_TEST, USER_NAME_TEST));
    AccountService accountService = new AccountServiceImpl();
    TransactionService transactionService = new TransactionServiceImpl();
    RequestTransaction requestDeposit = new RequestTransaction(
            AMOUNT_DEPO,
            OperationType.DEPOSIT,
            USER_ID_TEST,
            RIB_TEST
    );
    RequestTransaction requestRetrieve = new RequestTransaction(
            AMOUNT_RETRIEVE,
            OperationType.WITHDRAWAL,
            USER_ID_TEST,
            RIB_TEST
    );
  @BeforeEach
  void setup() {
      Data.accounts = List.of(accountTest);
  }


    @Test
    @Order(1)
    void should_return_existing_account() {
        // EXECUTE
        var account = accountService.findAccountByRibAndUser(USER_ID_TEST, RIB_TEST);
        // VERIFY
        Assertions.assertEquals(RIB_TEST, account.getRib());
        Assertions.assertEquals(USER_NAME_TEST, account.getUser().getName());
    }

    @Test
    @Order(2)
    void should_return_throw_exception_account_not_found() {
        // PREPARE
        var rib = "000";
        // EXECUTE && VERIFY
        Assertions.assertThrows(EntityNotFoundException.class, () -> accountService.findAccountByRibAndUser(USER_ID_TEST, rib));
    }

    @Test
    @Order(3)
    void should_add_amount_to_account() {
        // EXECUTE
        var transactionResp = transactionService.makeOperation(requestDeposit);
        // VERIFY
        Assertions.assertEquals(AMOUNT_DEPO, transactionResp.getAmount());
        Assertions.assertEquals(OperationStatus.ACCEPTED, transactionResp.getOperationStatus());
        Assertions.assertEquals(AMOUNT_DEPO, accountTest.getAmount());
    }

    @Test
    @Order(4)
    void should_throw_exception_for_rib_user_not_found() {
        // PREPARE
        var rib = "000";
        requestDeposit.setRib(rib);
        // EXECUTE
        // VERIFY
        Assertions.assertThrows(EntityNotFoundException.class, () -> transactionService.makeOperation(requestDeposit));
    }

    @Test
    @Order(5)
    void should_retrieve_amount_from_account() {
        // PREPARE
        transactionService.makeOperation(requestDeposit);
        // EXECUTE
        var transactionResp = transactionService.makeOperation(requestRetrieve);
        // VERIFY
        Assertions.assertEquals(AMOUNT_RETRIEVE, transactionResp.getAmount());
        Assertions.assertEquals(OperationStatus.ACCEPTED, transactionResp.getOperationStatus());
        Assertions.assertEquals(OperationType.WITHDRAWAL, transactionResp.getOperationType());
    }

    @Test
    @Order(6)
    void should_rejected_transaction_retrieve_amount_greater() {

        // PREPARE
        requestRetrieve.setAmount(new BigDecimal("-2000"));
        // EXECUTE
        var transactionResp = transactionService.makeOperation(requestRetrieve);
        // VERIFY
        Assertions.assertEquals(OperationStatus.REJECTED, transactionResp.getOperationStatus());
        Assertions.assertEquals(OperationType.WITHDRAWAL, transactionResp.getOperationType());
    }


    @Test
    @Order(7)
    void should_return_history_of_transactions(){
        var histories = transactionService.getHistory(USER_ID_TEST, RIB_TEST);
        // VERIFY
        Assertions.assertNotNull(histories);

    }

    @Test
    @Order(8)
    void should_return_balance_amount_account() {
        // PREPARE
        var balanceAmount = accountService.getBalance(USER_ID_TEST, RIB_TEST);
        var transactionResp = transactionService.makeOperation(requestDeposit);
        var balanceAfterDepot = accountService.getBalance(USER_ID_TEST, RIB_TEST);


        // VERIFY
        Assertions.assertEquals(new BigDecimal(0), balanceAmount);
        Assertions.assertEquals(AMOUNT_DEPO, balanceAfterDepot);
    }
}
