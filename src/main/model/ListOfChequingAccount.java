package model;

import java.util.ArrayList;
import java.util.List;

// Represents a list of Chequing Accounts with a name
public class ListOfChequingAccount {
    private String name; // name of owner
    private List<ChequingAccount> chequingAccounts; // list of chequing accounts

    // REQUIRES: name is not empty
    // EFFECTS: constructs an empty list of chequing accounts with a name
    public ListOfChequingAccount(String name) {
        // stub
    }

    public String getName() {
        // stub
    }

    // REQUIRES: at least one account in list
    // EFFECTS: returns the account with appropriate index. first item is index 0
    public ChequingAccount getAccount(int index) {
        // stub
    }

    // REQUIRES: chequing account with same owner name
    // MODIFIES: this
    // EFFECTS: adds an account to the end of list of chequing accounts and returns updated list
    public ArrayList<ChequingAccount> addAccount(ChequingAccount account) {
        // stub
    }

    // REQUIRES: at least one account in list is suspended
    // MODIFIES: this
    // EFFECTS: removes every account that is suspended in the list then returns the updated list
    public ArrayList<ChequingAccount> removeAccount() {
        // stub
    }

    // EFFECTS: add the balances of each account in the list and return the total
    public double addAll() {
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
