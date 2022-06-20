package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Account;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.repositories.AccountRepository;
import ru.skillfactory.restservice.repositories.OperationsRepository;
import ru.skillfactory.restservice.util.BalanceNotFoundException;
import ru.skillfactory.restservice.util.NegativeAmountException;
import ru.skillfactory.restservice.util.NotEnoughFundsException;

import java.time.LocalDateTime;
import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class AccountService {
    private final AccountRepository accountRepository;
    private final OperationsRepository operationsRepository;

    @Autowired
    public AccountService(AccountRepository accountRepository, OperationsRepository operationsRepository) {
        this.accountRepository = accountRepository;
        this.operationsRepository = operationsRepository;
    }

    public List<Account> findAll() {
        return accountRepository.findAll();
    }

    public Integer checkBalance(int id) {

        Integer checkedBalance = accountRepository.getBalanceById(id);
        if (checkedBalance == null) {
            throw new BalanceNotFoundException();
        }
        return checkedBalance;

    }

    @Transactional
    public int putMoney(int id, Integer amount, Operations operations) {

        Integer currentBalance = accountRepository.getBalanceById(id);
        if (currentBalance == null) {
            throw new BalanceNotFoundException();
        } else if (amount < 0 || currentBalance < 0) {
            throw new NegativeAmountException();
        } else {
            int updatedBalance = currentBalance + amount;
            accountRepository.saveUpdatedBalance(id, updatedBalance);

            // addOperations
            operations.setAmount(amount);
            operations.setAccountId(new Account());
            operations.setOperationType(1); //put=1
            operations.setOperationTime(LocalDateTime.now());
            operationsRepository.save(operations);
            return updatedBalance;
        }
    }

    @Transactional
    public Integer takeMoney(int id, Integer amount) {
        Integer currentBalance = accountRepository.getBalanceById(id);
        if (currentBalance == null) {
            throw new BalanceNotFoundException();
        } else if (amount < 0 || currentBalance < 0) {
            throw new NegativeAmountException();
        } else if (currentBalance < amount) {
            throw new NotEnoughFundsException();
        } else {
            int updatedBalance = currentBalance - amount;

            accountRepository.saveUpdatedBalance(id, updatedBalance);

            return updatedBalance;
        }
    }

//    public Operations operationsUpdateByPutMoney(Operations operations) {
//        Operations o = new Operations();
//        o.setOperationType(1); //put=1
//        o.setOperationTime(LocalDateTime.now());
//        return o;
//
//    }
}
