package ru.skillfactory.restservice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDateTime;

@Entity
@Table(name = "Operations")
public class Operations {
    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account accountId;


    @Column(name = "operation_type")
    private Integer operationType;

    @Column(name = "amount")
    @Min(value = 0, message = "amount не может быть меньше нуля")
    private Integer amount;

    @Column(name = "operation_time")
    private LocalDateTime operationTime;

    public Operations() {
    }

    public Operations(int operationId, Integer userId, Integer operationType, Integer amount, LocalDateTime operationTime) {
        this.operationId = operationId;
        this.operationType = operationType;
        this.amount = amount;
        this.operationTime = operationTime;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }


    public Integer getOperationType() {
        return operationType;
    }

    public void setOperationType(Integer operationType) {
        this.operationType = operationType;
    }

    public Integer getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

    public Account getAccountId() {
        return accountId;
    }

    public void setAccountId(Account accountId) {
        this.accountId = accountId;
    }

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

}
