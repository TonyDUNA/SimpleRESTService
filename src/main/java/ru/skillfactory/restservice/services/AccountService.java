package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Account;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.repositories.AccountRepository;
import ru.skillfactory.restservice.repositories.OperationsRepository;
import ru.skillfactory.restservice.exception.BalanceNotFoundException;
import ru.skillfactory.restservice.exception.NegativeAmountException;
import ru.skillfactory.restservice.exception.NotEnoughFundsException;

import java.time.LocalDate;
import java.time.LocalDateTime;

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

    public Integer checkBalance(int id) {

        Integer checkedBalance = accountRepository.getBalanceById(id);
        if (checkedBalance == null) {
            throw new BalanceNotFoundException();
        }
        return checkedBalance;

    }

    @Transactional
    public int putMoney(int id, Integer amount, Account account, Operations operations) {

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
            operations.setAccount(account);
            operations.setOperationType(1); //put=1
            operations.setOperationTime(LocalDateTime.now());
            operations.setOperationDate(LocalDate.now());
            operationsRepository.save(operations);

            return updatedBalance;
        }
    }

    @Transactional
    public Integer takeMoney(int id, Integer amount, Account account, Operations operations) {
        Integer currentBalance = accountRepository.getBalanceById(id);
        if (currentBalance == null) {
            throw new BalanceNotFoundException();
        } else if (amount < 0 || currentBalance < 0) {
            throw new NegativeAmountException();
        } else if (currentBalance < amount) {
            throw new NotEnoughFundsException();
        } else {
            int updatedBalance = currentBalance - amount;

            // takeOperations
            operations.setAmount(amount);
            operations.setAccount(account);
            operations.setOperationType(2); //take=2
            operations.setOperationTime(LocalDateTime.now());
            operations.setOperationDate(LocalDate.now());
            operationsRepository.save(operations);

            accountRepository.saveUpdatedBalance(id, updatedBalance);

            return updatedBalance;
        }
    }


}
