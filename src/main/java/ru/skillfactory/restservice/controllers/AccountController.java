package ru.skillfactory.restservice.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import ru.skillfactory.restservice.models.Account;
import ru.skillfactory.restservice.models.Operations;
import ru.skillfactory.restservice.models.OperationsResponse;
import ru.skillfactory.restservice.services.AccountService;
import ru.skillfactory.restservice.services.OperationsService;
import ru.skillfactory.restservice.util.AccountErrorResponse;
import ru.skillfactory.restservice.util.BalanceNotFoundException;
import ru.skillfactory.restservice.util.NegativeAmountException;
import ru.skillfactory.restservice.util.NotEnoughFundsException;

import javax.validation.Valid;
import java.time.LocalDate;

@RestController // @Controller + @ResponseBody на каждом методе
@RequestMapping("/accounts")
public class AccountController {
    private final AccountService accountService;
    private final OperationsService operationsService;


    @Autowired
    public AccountController(AccountService accountService, OperationsService operationsService) {
        this.accountService = accountService;
        this.operationsService = operationsService;
    }

    // check balance
    @GetMapping("/{id}")
    public Integer getBalance(@PathVariable int id) {
        return accountService.checkBalance(id);
    }

    // putMoney
    @PostMapping("/put")
    public Integer addMoney(@RequestBody @Valid Account account, Operations operations) {

        return accountService.putMoney(account.getId(), account.getAmount(), account, operations);
    }

    // takeMoney
    @PostMapping("/take")
    public Integer takeMoney(@RequestBody @Valid Account account, Operations operations) {

        return accountService.takeMoney(account.getId(), account.getAmount(), account, operations);
    }

    // getOperationList
    @GetMapping("/{id}/operations")
    public OperationsResponse getOperationsById(@PathVariable int id,
                                                @RequestParam(value = "dateFrom", required = false)  LocalDate dateFrom,
                                                @RequestParam(value = "dateTo", required = false)  LocalDate dateTo) {
        return new OperationsResponse(operationsService.findOperationsById(id, dateFrom, dateTo));
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