package model;

// Represents a Savings Account with a name, balance, interest rate, and status
public class SavingsAccount {
    private String name; // name of owner
    private double balance; // balance of account
    private double interest; // monthly interest rate of account in percentage
    private boolean isSuspended; // status of account

    // REQUIRES: name is not empty, balance >= 0, interest > 0
    // EFFECTS: constructs a savings account w/ name, balance, interest, and is not suspended
    public SavingsAccount(String name, Double balance, Double interest) {
        // stub
    }

    public String getName() {
        // stub
    }

    public Double getBalance() {
        // stub
    }

    public Double getInterest() {
        // stub
    }

    // EFFECTS: returns true if account is suspended, otherwise false
    public Boolean isSuspended() {
        // stub
    }

    // REQUIRES: given account is not already suspended and balance is 0
    // MODIFIES: this
    // EFFECTS: suspends the given account
    public void suspend() {
        // stub
    }

    // REQUIRES: amount >= 0
    // MODIFIES: this
    // EFFECTS: adds amount to the balance of the account and returns new balance
    public Double deposit(double amount) {
        // stub
    }

    // REQUIRES: account balance >= amount >= 0
    // MODIFIES: this
    // EFFECTS: takes amount away from balance and returns new balance
    public void withdraw(double amount) {
        // stub
    }

    // REQUIRES: months > 0
    // MODIFIES: this
    // EFFECTS: multiply current balance by interest rate then update balance and do this by the amount of months
    public Double gainInterest(int months) {
        // stub

        // balance= 100, interest = 2%, months = 3
        // month 1: 102 (100 x 1.02)
        // month 2: 104.04 (102 x 1.02)
        // month 3: 106.1208 (104.04 x 1.02)
    }

}
