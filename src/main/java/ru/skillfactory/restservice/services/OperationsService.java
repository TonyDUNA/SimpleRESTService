package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.repositories.OperationsRepository;
import ru.skillfactory.restservice.util.BalanceNotFoundException;

import java.time.LocalDate;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional(readOnly = true)
public class OperationsService {
    private final OperationsRepository operationsRepository;
    private final AccountService accountService;

    @Autowired
    public OperationsService(OperationsRepository operationsRepository, AccountService accountService) {
        this.operationsRepository = operationsRepository;
        this.accountService = accountService;
    }

    public List<Operations> findOperationsById(Integer id, LocalDate dateFrom, LocalDate dateTo) {

        if (dateFrom == null && dateTo == null) {
            List<Operations> operations = operationsRepository.findOperationsById(id);
            return operations;
        } else if (dateTo == null) {
            List<Operations> operations = operationsRepository.findOperationsByIdAndDateFrom(id, dateFrom);
            return operations;
        } else if (dateFrom == null) {
            List<Operations> operations = operationsRepository.findOperationsByIdAndDateTo(id, dateTo);
            return operations;

        } else {
            List<Operations> operations = operationsRepository.findOperationsByIdAndDates(id, dateFrom, dateTo);
            return operations;
        }
    }
}
