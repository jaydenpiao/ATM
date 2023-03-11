package ui;

import model.ChequingAccount;
import model.ListOfChequingAccount;
import model.ListOfSavingsAccount;
import model.SavingsAccount;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Scanner;

// ATM Machine Application with maximum of 3 chequing and 3 saving accounts
public class AtmMachine {
    private ChequingAccount ca1;
    private ChequingAccount ca2;
    private ChequingAccount ca3;
    private SavingsAccount sa1;
    private SavingsAccount sa2;
    private SavingsAccount sa3;
    private ListOfChequingAccount loca;
    private ListOfSavingsAccount losa;
    private static final String CHEQ = "./data/chequing.json";
    private static final String SAV = "./data/saving.json";
    private Scanner input;
    private JsonWriter jsonWriterCheq;
    private JsonReader jsonReaderCheq;
    private JsonWriter jsonWriterSav;
    private JsonReader jsonReaderSav;

    // EFFECTS: runs the ATM Machine
    public AtmMachine() throws FileNotFoundException {
        runMachine();
    }

    // MODIFIES: this
    // EFFECTS: processes user input
    private void runMachine() { // referred to teller app
        boolean keepGoing = true;
        String command = null;
        loca = new ListOfChequingAccount("Chequing Accounts");
        losa = new ListOfSavingsAccount("Saving Accounts");
        input = new Scanner(System.in);
        input.useDelimiter("\n");
        jsonWriterCheq = new JsonWriter(CHEQ);
        jsonReaderCheq = new JsonReader(CHEQ);
        jsonWriterSav = new JsonWriter(SAV);
        jsonReaderSav = new JsonReader(SAV);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("\nGoodbye!");
    }

    // MODIFIES: this
    // EFFECTS: processes user command
    private void processCommand(String command) { // referred to teller app
        if (command.equals("a")) {
            createAcc();
        } else if (command.equals("s")) {
            displaySaving();
        } else if (command.equals("c")) {
            displayChequing();
        } else if (command.equals("addc")) {
            doAddAllCheq();
        } else if (command.equals("adds")) {
            doAddAllSav();
        } else if (command.equals("addt")) {
            doAddAllWithMonths();
        } else if (command.equals("rc")) {
            removeCheq();
        } else if (command.equals("rs")) {
            removeSav();
        } else if (command.equals("sa")) {
            saveAccounts();
        } else if (command.equals("la")) {
            loadAccounts();
        } else {
            System.out.println("Selection not valid");
        }
    }

    // EFFECTS: displays options user can choose from
    private void displayMenu() {
        System.out.println("\nHello! Choose from:");
        System.out.println("\ta -> account creation");
        System.out.println("\ts -> saving accounts");
        System.out.println("\tc -> chequing accounts");
        System.out.println("\taddc -> add all chequing accounts");
        System.out.println("\tadds -> add all saving accounts");
        System.out.println("\taddt -> add all saving accounts with months");
        System.out.println("\trc -> remove suspended chequing accounts");
        System.out.println("\trs -> remove suspended saving accounts");
        System.out.println("\tsa -> save accounts to file");
        System.out.println("\tla -> load accounts from file");
        System.out.println("\tq -> quit");
    }

    // EFFECTS: user can choose either chequing or savings
    private void createAcc() {
        System.out.println("Chequing(c) or Savings(s)");
        String accType = input.next();

        if (accType.equals("c")) {
            createCheq();
        } else if (accType.equals("s")) {
            createSav();
        }
    }

    // MODIFIES: this
    // EFFECTS: create chequing account
    private void createCheq() {
        System.out.println("Name of account?");
        System.out.println("What's the initial balance? *must include a decimal, answer each on different lines*");
        if (ca1 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            ca1 = new ChequingAccount(name, balance);
            loca.addAccount(ca1);
        } else if (ca2 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            ca2 = new ChequingAccount(name, balance);
            loca.addAccount(ca2);
        } else if (ca3 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            ca3 = new ChequingAccount(name, balance);
            loca.addAccount(ca3);
        }
    }

    // MODIFIES: this
    // EFFECTS: create savings account
    private void createSav() {
        System.out.println("Name of account?");
        System.out.println("What's the initial balance? *must include a decimal*");
        System.out.println("What's the interest rate in percentage? *answer each on different lines*");
        if (sa1 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            double interest = input.nextDouble();
            sa1 = new SavingsAccount(name, balance, interest);
            losa.addAccount(sa1);
        } else if (sa2 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            double interest = input.nextDouble();
            sa2 = new SavingsAccount(name, balance, interest);
            losa.addAccount(sa2);
        } else if (sa3 == null) {
            String name = input.next();
            double balance = input.nextDouble();
            double interest = input.nextDouble();
            sa3 = new SavingsAccount(name, balance, interest);
            losa.addAccount(sa3);
        }
    }


    // EFFECTS: displays chequing accounts
    private void displayChequing() {
        if (loca.hasAccount(loca.getAccount(0))) {
            ChequingAccount ca = loca.getAccount(0);
            System.out.printf("0. Name: " + ca.getName() + " Balance: " + ca.getBalance());
        }
        if (loca.hasAccount(loca.getAccount(1))) {
            ChequingAccount ca = loca.getAccount(1);
            System.out.printf("\n1. Name: " + ca.getName() + " Balance: " + ca.getBalance());
        }
        if (loca.hasAccount(2)) { // make hasAccount but take in index
            ChequingAccount ca = loca.getAccount(2);
            System.out.printf("\n2. Name: " + ca.getName() + " Balance: " + ca.getBalance());
        }

        System.out.println("\nWhich account would you like to access? *number*");
        int accOption = input.nextInt();
        if (accOption == 0) {
            cheqAccOptions(loca.getAccount(0));
        } else if (accOption == 1) {
            cheqAccOptions(loca.getAccount(1));
        } else {
            cheqAccOptions(loca.getAccount(2));
        }
    }

    // EFFECTS: displays saving accounts
    private void displaySaving() {
        if (losa.hasAccount(losa.getAccount(0))) {
            SavingsAccount sa = losa.getAccount(0);
            System.out.printf("0. Name: " + sa.getName() + " Balance: " + sa.getBalance() + " Interest Rate: "
                    + sa.getInterest());
        }
        if (losa.hasAccount(losa.getAccount(1))) {
            SavingsAccount sa = losa.getAccount(1);
            System.out.printf("\n1. Name: " + sa.getName() + " Balance: " + sa.getBalance() + " Interest Rate: "
                    + sa.getInterest());
        }
        if (losa.hasAccount(2)) { // make hasAccount but take in index
            SavingsAccount sa = losa.getAccount(2);
            System.out.printf("\n2. Name: " + sa.getName() + " Balance: " + sa.getBalance());
        }

        System.out.println("\nWhich account would you like to access? *number*");
        int accOption = input.nextInt();
        if (accOption == 0) {
            savAccOptions(losa.getAccount(0));
        } else if (accOption == 1) {
            savAccOptions(losa.getAccount(1));
        } else {
            savAccOptions(losa.getAccount(2));
        }
    }

    // EFFECTS: displays all the options user can do with chosen chequing account
    private void cheqAccOptions(ChequingAccount ca) {
        System.out.println("Deposit(d), Withdraw(w), Suspend(s)");
        String option = input.next();
        if (option.equals("d")) {
            doDepositCheq(ca);
        } else if (option.equals("w")) {
            doWithdrawCheq(ca);
        } else if (option.equals("s")) {
            doSuspendCheq(ca);
        }
    }

    // EFFECTS: displays all the options user can do with chosen chequing account
    private void savAccOptions(SavingsAccount sa) {
        System.out.println("Deposit(d), Withdraw(w), GainInterest(i), Suspend(s)");
        String option = input.next();
        if (option.equals("d")) {
            doDepositSav(sa);
        } else if (option.equals("w")) {
            doWithdrawSav(sa);
        } else if (option.equals("i")) {
            doGainInterest(sa);
        } else if (option.equals("s")) {
            doSuspendSav(sa);
        }
    }

    // EFFECTS: add all chequing balances
    private void doAddAllCheq() {
        System.out.printf("Total Balance: $%.2f\n", loca.addAll());
    }

    // EFFECTS: add all saving balances
    private void doAddAllSav() {
        System.out.printf("Total Balance: $%.2f\n", losa.addAll());
    }

    // MODIFIES: this
    // EFFECTS: applies gainInterest() on all saving accounts then adds all of them
    private void doAddAllWithMonths() {
        System.out.println("How many months?");
        int month = input.nextInt();
        System.out.printf("Total Balance: $%.2f\n", losa.addAllWithMonths(month));
    }

    // MODIFIES: this
    // EFFECTS: removes all chequing accounts that are suspended
    private void removeCheq() {
        loca.removeAccount();
        System.out.println("All suspended accounts are removed.");
    }

    // MODIFIES: this
    // EFFECTS: removes all saving accounts that are suspended
    private void removeSav() {
        losa.removeAccount();
        System.out.println("All suspended accounts are removed.");
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doDepositCheq(ChequingAccount ca) {
        System.out.println("How much to deposit?");
        double amount = input.nextDouble();
        ca.deposit(amount);
        printBalanceCheq(ca);
    }

    // MODIFIES: this
    // EFFECTS: conducts a deposit transaction
    private void doDepositSav(SavingsAccount sa) {
        System.out.println("How much to deposit?");
        double amount = input.nextDouble();
        sa.deposit(amount);
        printBalanceSav(sa);
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void doWithdrawCheq(ChequingAccount ca) {
        System.out.println("How much to withdraw?");
        double amount = input.nextDouble();
        ca.withdraw(amount);
        printBalanceCheq(ca);
    }

    // MODIFIES: this
    // EFFECTS: conducts a withdraw transaction
    private void doWithdrawSav(SavingsAccount sa) {
        System.out.println("How much to withdraw?");
        double amount = input.nextDouble();
        sa.withdraw(amount);
        printBalanceSav(sa);
    }

    // MODIFIES: this
    // EFFECTS: gains interest on the account
    private void doGainInterest(SavingsAccount sa) {
        System.out.println("How many months?");
        int amount = input.nextInt();
        sa.gainInterest(amount);
        printBalanceSav(sa);
    }

    // MODIFIES: this
    // EFFECTS: user can suspend account
    private void doSuspendCheq(ChequingAccount ca) {
        ca.suspend();
        System.out.println("Account is now suspended.");
    }

    // MODIFIES: this
    // EFFECTS: user can suspend account
    private void doSuspendSav(SavingsAccount sa) {
        sa.suspend();
        System.out.println("Account is now suspended.");
    }

    // EFFECTS: prints balance of account
    private void printBalanceCheq(ChequingAccount ca) {
        System.out.printf("Balance: $%.2f\n", ca.getBalance());
    }

    // EFFECTS: prints balance of account
    private void printBalanceSav(SavingsAccount sa) {
        System.out.printf("Balance: $%.2f\n", sa.getBalance());
    }

    // EFFECTS: saves accounts to file
    private void saveAccounts() { // referred to jsondemo
        try {
            jsonWriterCheq.open();;
            jsonWriterCheq.writeChequing(loca);
            jsonWriterCheq.close();
            jsonWriterSav.open();;
            jsonWriterSav.writeSaving(losa);
            jsonWriterSav.close();
            System.out.println("Saved");
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file");
        }
    }

    // MODIFIES: this
    // EFFECTS: loads accounts from file
    private void loadAccounts() {
        try {
            loca = jsonReaderCheq.readCheq();
            losa = jsonReaderSav.readSav();
            System.out.println("Loaded accounts");
        } catch (IOException e) {
            System.out.println("Unable to read from file");
        }
    }
}

