package com.example.BlogApp.model;

public class LoginResponse {

    private String message;
    private int status;
    public LoginResponse(String message, int status) {
        this.message = message;
        this.status = status;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }




}
