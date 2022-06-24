package ru.skillfactory.restservice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

@Entity
@Table(name = "Operations")
public class Operations {
    @Id
    @Column(name = "operation_id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int operationId;

    @ManyToOne
    @JoinColumn(name = "account_id", referencedColumnName = "id")
    private Account account;


    @Column(name = "operation_type")
    private Integer operationType;

    @Column(name = "amount")
    @Min(value = 0, message = "amount не может быть меньше нуля")
    private Integer amount;

    @Column(name = "operation_time")
    private LocalDateTime operationTime;

    @Column(name = "operation_date")
    private LocalDate operationDate;

    public Operations() {
    }


    public Operations(int operationId, Account account, Integer operationType,
                      Integer amount, LocalDateTime operationTime, LocalDate operationDate) {
        this.operationId = operationId;
        this.account = account;
        this.operationType = operationType;
        this.amount = amount;
        this.operationTime = operationTime;
        this.operationDate = operationDate;
    }

    public int getOperationId() {
        return operationId;
    }

    public void setOperationId(int operationId) {
        this.operationId = operationId;
    }

    public Account getAccount() {
        return account;
    }

    public void setAccount(Account account) {
        this.account = account;
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

    public LocalDateTime getOperationTime() {
        return operationTime;
    }

    public void setOperationTime(LocalDateTime operationTime) {
        this.operationTime = operationTime;
    }

    public LocalDate getOperationDate() {
        return operationDate;
    }

    public void setOperationDate(LocalDate operationDate) {
        this.operationDate = operationDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Operations)) return false;
        Operations that = (Operations) o;
        return operationId == that.operationId && Objects.equals(account, that.account) && Objects.equals(operationType, that.operationType) && Objects.equals(amount, that.amount) && Objects.equals(operationTime, that.operationTime);
    }

    @Override
    public int hashCode() {
        return Objects.hash(operationId, account, operationType, amount, operationTime);
    }
}
