package ru.skillfactory.restservice.models;

import javax.persistence.*;
import javax.validation.constraints.Min;


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

    public Account(){

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
}
