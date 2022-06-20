package ru.skillfactory.restservice.services;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.repositories.OperationsRepository;

import java.util.List;

@Service
@Transactional(readOnly = true)
public class OperationsService {
    private final OperationsRepository operationsRepository;

    @Autowired
    public OperationsService(OperationsRepository operationsRepository) {
        this.operationsRepository = operationsRepository;
    }

    public List<Operations> getOperationList(int id) {
        //  TO DO метод получения из репы
        return null;
    }

}

