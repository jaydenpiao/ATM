package persistence;

import model.ChequingAccount;
import model.ListOfChequingAccount;
import model.ListOfSavingsAccount;
import model.SavingsAccount;
import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.stream.Stream;

// Represents a reader that reads accounts from JSON data stored in file
public class JsonReader { // referred to jsondemo
    private String source;

    // EFFECTS: constructs reader to read from source file
    public JsonReader(String source) {
        this.source = source;
    }

    // EFFECTS: reads chequing accounts from file and returns it
    // throws IOException if an error occurs reading data from file
    public ListOfChequingAccount readCheq() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseCheq(jsonObject);
    }

    // EFFECTS: reads saving accounts from file and returns it
    // throws IOException if an error occurs reading data from file
    public ListOfSavingsAccount readSav() throws IOException {
        String jsonData = readFile(source);
        JSONObject jsonObject = new JSONObject(jsonData);
        return parseSav(jsonObject);
    }

    // EFFECTS: reads source file as string and returns it
    private String readFile(String source) throws IOException {
        StringBuilder contentBuilder = new StringBuilder();

        try (Stream<String> stream = Files.lines(Paths.get(source), StandardCharsets.UTF_8)) {
            stream.forEach(s -> contentBuilder.append(s));
        }

        return contentBuilder.toString();
    }

    // EFFECTS: parses chequing accounts from JSON object and returns it
    private ListOfChequingAccount parseCheq(JSONObject jsonObject) {
        String name = jsonObject.getString("name of chequing accounts");
        ListOfChequingAccount loca = new ListOfChequingAccount(name);
        addChequingAccounts(loca, jsonObject);
        return loca;
    }

    // EFFECTS: parses saving accounts from JSON object and returns it
    private ListOfSavingsAccount parseSav(JSONObject jsonObject) {
        String name = jsonObject.getString("name of saving accounts");
        ListOfSavingsAccount losa = new ListOfSavingsAccount(name);
        addSavingAccounts(losa, jsonObject);
        return losa;
    }

    // MODIFIES: loca
    // EFFECTS: parses chequing accounts from JSON object and adds them to list
    private void addChequingAccounts(ListOfChequingAccount loca, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("chequing accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addChequingAccount(loca, nextAccount);
        }
    }

    // MODIFIES: losa
    // EFFECTS: parses saving accounts from JSON object and adds them to list
    private void addSavingAccounts(ListOfSavingsAccount losa, JSONObject jsonObject) {
        JSONArray jsonArray = jsonObject.getJSONArray("saving accounts");
        for (Object json : jsonArray) {
            JSONObject nextAccount = (JSONObject) json;
            addSavingAccount(losa, nextAccount);
        }
    }

    // MODIFIES: loca
    // EFFECTS: parses account from JSON object and adds it to chequing accounts
    private void addChequingAccount(ListOfChequingAccount loca, JSONObject jsonObject) {
        String name = jsonObject.getString("chequing name");
        double balance = jsonObject.getDouble("chequing balance");
        ChequingAccount ca = new ChequingAccount(name, balance);
        loca.addAccount(ca);
    }

    // MODIFIES: losa
    // EFFECTS: parses account from JSON object and adds it to saving accounts
    private void addSavingAccount(ListOfSavingsAccount losa, JSONObject jsonObject) {
        String name = jsonObject.getString("savings name");
        double interest = jsonObject.getDouble("savings interest");
        double balance = jsonObject.getDouble("savings balance");
        SavingsAccount sa = new SavingsAccount(name, balance, interest);
        losa.addAccount(sa);
    }
}
