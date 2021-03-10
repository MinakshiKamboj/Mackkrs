package com.sukrit.mckkrs.Models;

public class Login {
    public Login(String message, String marn, String name, String email, String validated) {
        this.message = message;
        this.marn = marn;
        this.name = name;
        this.email = email;
        this.validated = validated;
    }

    public Login() {
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getMarn() {
        return marn;
    }

    public void setMarn(String marn) {
        this.marn = marn;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getValidated() {
        return validated;
    }

    public void setValidated(String validated) {
        this.validated = validated;
    }

    String message;
    String marn;
    String name;
    String email;
    String validated;

}
