package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Account;
import ru.skillfactory.restservice.repositories.Repository;
import ru.skillfactory.restservice.util.BalanceNotFoundException;
import ru.skillfactory.restservice.util.NegativeAmountException;
import ru.skillfactory.restservice.util.NotEnoughFundsException;

import java.util.List;

@org.springframework.stereotype.Service
@Transactional(readOnly = true)
public class Service {
    private final Repository repository;

    @Autowired
    public Service(Repository repository) {
        this.repository = repository;
    }

    public List<Account> findAll() {
        return repository.findAll();
    }

    public Integer checkBalance(int id) {

        Integer checkedBalance = repository.getBalanceById(id);
        if (checkedBalance == null) {
            throw new BalanceNotFoundException();
        }
        return checkedBalance;

    }

    @Transactional
    public int putMoney(int id, Integer amount) {

        Integer currentBalance = repository.getBalanceById(id);
        if (currentBalance == null) {
            throw new BalanceNotFoundException();
        } else if (amount < 0 || currentBalance < 0) {
            throw new NegativeAmountException();
        } else {
            int updatedBalance = currentBalance + amount;
            repository.saveUpdatedBalance(id, updatedBalance);
            return updatedBalance;
        }
    }

    @Transactional
    public Integer takeMoney(int id, Integer amount) {
        Integer currentBalance = repository.getBalanceById(id);
        if (currentBalance == null) {
            throw new BalanceNotFoundException();
        } else if (amount < 0 || currentBalance < 0) {
            throw new NegativeAmountException();
        } else if (currentBalance < amount) {
            throw new NotEnoughFundsException();
        } else {
            int updatedBalance = currentBalance - amount;
            repository.saveUpdatedBalance(id, updatedBalance);
            return updatedBalance;
        }
    }
}
