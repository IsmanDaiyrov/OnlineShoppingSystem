package com.company;

public abstract class Account {
    private String accountNumber;
    private String pin;
    private String ssn;
    private double balance;
    private double lastDepositAmt;

    public Account(String pin, String ssn, String accountNumber, double balance, double lastDepositAmt) throws pinException, ssnException {
        this.pin = pin;
        this.ssn = ssn;
        this.accountNumber = accountNumber;
        this.balance = balance;
        this.lastDepositAmt = lastDepositAmt;

        if (pin.length() != 4) {
            throw new pinException("Invalid PIN");
        }
        if (ssn.length() != 9) {
            throw new ssnException("Invalid SSN");
        }
    }

    public boolean checkPin(String pinEntered) {
        return pinEntered.equals(pin);
    }

    public void close() {
        balance = 0.0;
    }

    public double getBalance() {
        return balance;
    }

    public String toString() {
        return accountNumber + " " + pin + " " + ssn + " " + balance + " " + lastDepositAmt;
    }

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public void withdraw(double amt) {
        if (balance >= amt) {  // Account has sufficient balance
            balance = (balance - amt);
        }
    }

    public void deposit(double amt) {
        balance += amt;
        lastDepositAmt = amt;
    }

    public double getLastDepositAmount() {
        return lastDepositAmt;
    }

    public void transfer(Account account, double amt) {
        if (balance >= amt) {
            account.deposit(amt);
            balance -= amt;
        }
    }
}
