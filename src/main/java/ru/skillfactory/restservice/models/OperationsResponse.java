package ru.skillfactory.restservice.models;


import java.util.List;

public class OperationsResponse {
    private List<Operations> operationsList;

    public OperationsResponse(List<Operations> operationsList) {
        this.operationsList = operationsList;
    }

    public List<Operations> getOperationsList() {
        return operationsList;
    }

    public void setOperationsList(List<Operations> operationsList) {
        this.operationsList = operationsList;
    }
}
