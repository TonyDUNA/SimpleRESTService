package ru.skillfactory.restservice.util;

public class AccountErrorResponse {
    private String message;
    int CodeOfMistake;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public int getCodeOfMistake() {
        return CodeOfMistake;
    }

    public void setCodeOfMistake(int codeOfMistake) {
        CodeOfMistake = codeOfMistake;
    }

    public AccountErrorResponse(String message, int codeOfMistake) {
        this.message = message;
        CodeOfMistake = codeOfMistake;
    }
}
