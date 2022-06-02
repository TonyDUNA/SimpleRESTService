package ru.skillfactory.restservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.restservice.models.Account;
import ru.skillfactory.restservice.services.Service;
import ru.skillfactory.restservice.util.AccountErrorResponse;
import ru.skillfactory.restservice.util.BalanceNotFoundException;
import ru.skillfactory.restservice.util.NegativeAmountException;
import ru.skillfactory.restservice.util.NotEnoughFundsException;

import javax.validation.Valid;
import java.util.List;

@RestController // @Controller + @ResponseBody на каждом методе
@RequestMapping("/accounts")
public class AccountController {
    private final Service service;

    @Autowired
    public AccountController(Service service) {
        this.service = service;
    }

    @GetMapping()
    public List<Account> getAccount() {
        return service.findAll();
    }

    // check balance
    @GetMapping("/{id}")
    public Integer getBalance(@PathVariable int id) {
        return service.checkBalance(id);
    }

    // putMoney
    @PostMapping("/put")
    public Integer addMoney(@RequestBody @Valid Account account) {

        return service.putMoney(account.getId(), account.getAmount());
    }

    // takeMoney
    @PostMapping("/take")
    public Integer takeMoney(@RequestBody @Valid Account account) {
        return service.takeMoney(account.getId(), account.getAmount());
    }

    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handle(BalanceNotFoundException e) {
        AccountErrorResponse accountErrorResponse = new AccountErrorResponse("Отсутствует баланс аккаунта",
                -1);

// в ответе тело response и статус в заголовке
        return new ResponseEntity<>(accountErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handle(NegativeAmountException e) {
        AccountErrorResponse accountErrorResponse = new AccountErrorResponse("Сумма не может быть отрицательной",
                -1);

        return new ResponseEntity<>(accountErrorResponse, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler
    private ResponseEntity<AccountErrorResponse> handle(NotEnoughFundsException e) {
        AccountErrorResponse accountErrorResponse = new AccountErrorResponse("Недостаточно средств",
                0);

        return new ResponseEntity<>(accountErrorResponse, HttpStatus.FORBIDDEN);
    }
}