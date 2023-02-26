package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Savings Accounts with a name
public class ListOfSavingsAccount {
    private String name; // name of owner
    private List<SavingsAccount> savingAccounts; // list of saving accounts

    // REQUIRES: name is not empty
    // EFFECTS: constructs an empty list of saving accounts with a name
    public ListOfSavingsAccount(String name) {
        // stub
    }

    public String getName() {
        // stub
    }

    // REQUIRES: at least one account in list
    // EFFECTS: returns the account with appropriate index. first item is index 0
    public SavingsAccount getAccount(int index) {
        // stub
    }

    // REQUIRES: savings account with same owner name
    // MODIFIES: this
    // EFFECTS: adds an account to the end of list of saving accounts and returns updated list
    public ArrayList<SavingsAccount> addAccount(SavingsAccount account) {
        // stub
    }

    // REQUIRES: at least one account in list is suspended
    // MODIFIES: this
    // EFFECTS: removes every account that is suspended in the list then returns the updated list
    public ArrayList<SavingsAccount> removeAccount() {
        // stub
    }

    // EFFECTS: add the balances of each account in the list and return the total
    public double addAll() {
        // stub
    }

    // REQUIRES: months > 0, has at least one account in list
    // EFFECTS: use gainInterest(int months) with same amount as this.months on each account in list, then add all the
    //          values and return the total
    public double addAllWithMonths(int months) {
        // stub
    }

    // EFFECTS: returns the number of accounts in the list
    public int length() {
        // stub
    }

    // EFFECTS: return true if no accounts in list, false otherwise
    public boolean isEmpty() {
        // stub
    }


}
