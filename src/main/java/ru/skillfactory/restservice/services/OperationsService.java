package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.repositories.OperationsRepository;

import java.time.LocalDate;
import java.util.List;

@Service
@Transactional(readOnly = true)
public class OperationsService {
    private final OperationsRepository operationsRepository;


    @Autowired
    public OperationsService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public List<Operations> findOperationsById(Integer id, LocalDate dateFrom, LocalDate dateTo) {

        if (dateFrom == null && dateTo == null) {
            return operationsRepository.findOperationsById(id);

        } else if (dateTo == null) {
            return operationsRepository.findOperationsByIdAndDateFrom(id, dateFrom);

        } else if (dateFrom == null) {
            return operationsRepository.findOperationsByIdAndDateTo(id, dateTo);

        } else {
            return operationsRepository.findOperationsByIdAndDates(id, dateFrom, dateTo);
        }
    }
}
