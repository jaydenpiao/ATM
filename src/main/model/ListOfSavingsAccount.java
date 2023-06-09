package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of Savings Accounts with a name
public class ListOfSavingsAccount implements Writable {
    private String name; // name of owner
    private List<SavingsAccount> savingAccounts; // list of saving accounts

    // REQUIRES: name is not empty
    // EFFECTS: constructs an empty list of saving accounts with a name
    public ListOfSavingsAccount(String name) {
        this.name = name;
        this.savingAccounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // REQUIRES: at least one account in list
    // EFFECTS: returns the account with appropriate index. first item is index 0
    public SavingsAccount getAccount(int index) {
        return savingAccounts.get(index);
    }

    // REQUIRES: savings account with same owner name
    // MODIFIES: this
    // EFFECTS: adds an account to the end of list of saving accounts and returns updated list
    public List<SavingsAccount> addAccount(SavingsAccount account) {
        savingAccounts.add(account);
        return savingAccounts;
    }

    // REQUIRES: at least one account in list is suspended
    // MODIFIES: this
    // EFFECTS: removes every account that is suspended in the list then returns the updated list
    public List<SavingsAccount> removeAccount() {
        savingAccounts.removeIf(account -> account.isSuspended());
        return savingAccounts;
    }

    // EFFECTS: add the balances of each account in the list and return the total
    public double addAll() {
        double total = 0.0;
        for (SavingsAccount account : savingAccounts) {
            total += account.getBalance();
        }
        return total;
    }

    // REQUIRES: months > 0, has at least one account in list
    // EFFECTS: use gainInterest(int months) with same amount as this.months on each account in list, then add all the
    //          values and return the total
    public double addAllWithMonths(int months) {
        double total = 0.0;
        for (SavingsAccount account : savingAccounts) {
            account.gainInterest(months);
            total += account.getBalance();
        }
        return total;
    }

    // EFFECTS: returns the number of accounts in the list
    public int length() {
        return savingAccounts.size();
    }

    // EFFECTS: return true if no accounts in list, false otherwise
    public boolean isEmpty() {
        if (savingAccounts.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if list contains the account, otherwise false
    public boolean hasAccount(SavingsAccount sa) {
        if (savingAccounts.contains(sa)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if list contains the account with index, otherwise false
    public boolean hasAccount(int index) {
        if (savingAccounts.size() > index) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns an unmodifiable list of accounts in this list // make tests
    public List<SavingsAccount> getSavingAccounts() {
        return Collections.unmodifiableList(savingAccounts);
    }

    @Override
    public JSONObject toJson() { // referred to jsondemo
        JSONObject json = new JSONObject();
        json.put("name of saving accounts", name);
        json.put("saving accounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns accounts in this list as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (SavingsAccount sa : savingAccounts) {
            jsonArray.put(sa.toJson());
        }

        return jsonArray;
    }
}
