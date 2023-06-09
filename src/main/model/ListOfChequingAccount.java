package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a list of Chequing Accounts with a name
public class ListOfChequingAccount implements Writable {
    private String name; // name of owner
    private List<ChequingAccount> chequingAccounts; // list of chequing accounts

    // REQUIRES: name is not empty
    // EFFECTS: constructs an empty list of chequing accounts with a name
    public ListOfChequingAccount(String name) {
        this.name = name;
        this.chequingAccounts = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    // REQUIRES: at least one account in list
    // EFFECTS: returns the account with appropriate index. first item is index 0
    public ChequingAccount getAccount(int index) {
        return chequingAccounts.get(index);
    }

    // REQUIRES: chequing account with same owner name
    // MODIFIES: this
    // EFFECTS: adds an account to the end of list of chequing accounts and returns updated list
    public List<ChequingAccount> addAccount(ChequingAccount account) {
        EventLog.getInstance().logEvent(new Event("Added account: " + account.getName()));
        chequingAccounts.add(account);
        return chequingAccounts;
    }

    // REQUIRES: at least one account in list is suspended
    // MODIFIES: this
    // EFFECTS: removes every account that is suspended in the list then returns the updated list
    public List<ChequingAccount> removeAccount() {
        chequingAccounts.removeIf(account -> account.isSuspended());
        return chequingAccounts;
    }

    // REQUIRES: ca must be in list
    // MODIFIES: this
    // EFFECTS: removes ca from chequing accounts
    public List<ChequingAccount> removeAccountSpecific(ChequingAccount ca) {
        EventLog.getInstance().logEvent((new Event("Removed account: " + ca.getName())));
        chequingAccounts.remove(ca);
        return chequingAccounts;
    }

    // EFFECTS: add the balances of each account in the list and return the total
    public double addAll() {
        double total = 0.0;
        for (ChequingAccount account : chequingAccounts) {
            total += account.getBalance();
        }
        return total;
    }

    // EFFECTS: returns the number of accounts in the list
    public int length() {
        return chequingAccounts.size();
    }

    // EFFECTS: return true if no accounts in list, false otherwise
    public boolean isEmpty() {
        if (chequingAccounts.size() == 0) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if list contains the account, otherwise false
    public boolean hasAccount(ChequingAccount ca) {
        if (chequingAccounts.contains(ca)) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns true if list contains the account with index, otherwise false
    public boolean hasAccount(int index) {
        if (chequingAccounts.size() > index) {
            return true;
        } else {
            return false;
        }
    }

    // EFFECTS: returns an unmodifiable list of accounts in this list // make tests
    public List<ChequingAccount> getChequingAccounts() {
        return Collections.unmodifiableList(chequingAccounts);
    }

    @Override
    public JSONObject toJson() { // referred to jsondemo
        JSONObject json = new JSONObject();
        json.put("name of chequing accounts", name);
        json.put("chequing accounts", accountsToJson());
        return json;
    }

    // EFFECTS: returns accounts in this list as a JSON array
    private JSONArray accountsToJson() {
        JSONArray jsonArray = new JSONArray();

        for (ChequingAccount ca : chequingAccounts) {
            jsonArray.put(ca.toJson());
        }

        return jsonArray;
    }
}
