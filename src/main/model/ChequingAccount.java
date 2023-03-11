package model;

import org.json.JSONObject;
import persistence.Writable;

// Represents a Chequing Account with a name and balance and status
public class ChequingAccount implements Writable {
    private String name; // name of owner
    private double balance; // balance of account
    private boolean isSuspended; // status of account

    // REQUIRES: name must not be empty, balance >= 0
    // EFFECTS: constructs a chequing account with name and balance and is not suspended
    public ChequingAccount(String name, Double balance) {
        this.name = name;
        this.balance = balance;
        this.isSuspended = false;
    }

    public String getName() {
        return name;
    }

    public Double getBalance() {
        return balance;
    }

    // EFFECTS: returns true if account is suspended, otherwise false
    public Boolean isSuspended() {
        return isSuspended;
    }

    // REQUIRES: given account is not already suspended and balance is 0
    // MODIFIES: this
    // EFFECTS: suspends the given account
    public void suspend() {
        isSuspended = true;
    }

    // REQUIRES: amount > 0
    // MODIFIES: this
    // EFFECTS: adds amount to the balance of the account and returns new balance
    public Double deposit(double amount) {
        balance += amount;
        return balance;
    }

    // REQUIRES: account balance >= amount >= 0
    // MODIFIES: this
    // EFFECTS: takes amount away from balance and returns new balance
    public Double withdraw(double amount) {
        balance -= amount;
        return balance;
    }

    @Override
    public JSONObject toJson() { // referred to jsondemo
        JSONObject json = new JSONObject();
        json.put("chequing name", name);
        json.put("chequing balance", balance);
        return json;
    }

}
