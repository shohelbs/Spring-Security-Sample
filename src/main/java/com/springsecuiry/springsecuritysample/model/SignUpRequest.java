package com.springsecuiry.springsecuritysample.model;

public class SignUpRequest {

    private String customerName;
    private String username;
    private String password;
    private String email;

    public SignUpRequest() {
    }

    public SignUpRequest(String customerName, String username, String password, String email) {
        this.customerName = customerName;
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public String getCustomerName() {
        return customerName;
    }

    public void setCustomerName(String customerName) {
        this.customerName = customerName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
