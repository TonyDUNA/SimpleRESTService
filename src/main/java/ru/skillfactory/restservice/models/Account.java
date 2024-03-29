package ru.skillfactory.restservice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;
import java.util.List;


@Entity
@Table(name = "Account")
public class Account {
    @Id
    @Column(name = "id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @Column(name = "balance")
    @Min(value = 0, message = "Баланс не может быть меньше нуля")
    private Integer balance;

    @Column(name = "amount")
    @Min(value = 0, message = "amount не может быть меньше нуля")
    private Integer amount;


//    @OneToMany(mappedBy = "account", cascade = CascadeType.ALL, orphanRemoval = true)
//    private List<Operations> operationsList;

    public Account() {
    }

    public Account(int id, Integer balance, Integer amount) {
        this.id = id;
        this.balance = balance;
        this.amount = amount;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public Integer getBalance() {
        return balance;
    }

    public void setBalance(Integer balance) {
        this.balance = balance;
    }

    public int getAmount() {
        return amount;
    }

    public void setAmount(Integer amount) {
        this.amount = amount;
    }

//    public List<Operations> getOperationsList() {
//        return operationsList;
//    }
//
//    public void setOperationsList(List<Operations> operationsList) {
//        this.operationsList = operationsList;
//    }
}
