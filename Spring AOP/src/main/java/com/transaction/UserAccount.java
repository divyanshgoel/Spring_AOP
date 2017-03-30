package com.transaction;


/*
        Question 1 :- Create User Account table with name,balance
 */

public class UserAccount {
    private String userName;
    private int balance;

    public UserAccount(String userName, int mobileNumber, int balance) {
        this.userName = userName;
        this.balance = balance;
    }

    public UserAccount() {
    }

    @Override
    public String toString() {
        return "UserAccount{" +
                "name='" + userName + '\'' +
                ", balance=" + balance +
                '}';
    }

    public String getName() {
        return userName;
    }

    public void setName(String userName) {
        this.userName = userName;
    }

    public int getBalance() {
        return balance;
    }

    public void setBalance(int balance) {
        this.balance = balance;
    }
}
