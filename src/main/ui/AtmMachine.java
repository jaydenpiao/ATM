package ui;

import model.ChequingAccount;
import model.ListOfChequingAccount;
import model.ListOfSavingsAccount;
import model.SavingsAccount;

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
    private Scanner input;

    // EFFECTS: runs the ATM Machine
    public AtmMachine() {
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
        if (loca.hasAccount(ca1)) {
            //System.out.println("1 account is here");
            System.out.printf("1. Name: " + ca1.getName() + " Balance: " + ca1.getBalance());
        }
        if (loca.hasAccount(ca2)) {
            System.out.printf("\n2. Name: " + ca2.getName() + " Balance: " + ca2.getBalance());
        }
        if (loca.hasAccount(ca3)) {
            System.out.printf("\n3. Name: " + ca3.getName() + " Balance: " + ca3.getBalance());
        }
        System.out.println("\nWhich account would you like to access? *number*");
        int accOption = input.nextInt();
        if (accOption == 1) {
            cheqAccOptions(ca1);
        } else if (accOption == 2) {
            cheqAccOptions(ca2);
        } else {
            cheqAccOptions(ca3);
        }
    }

    // EFFECTS: displays saving accounts
    private void displaySaving() {
        if (losa.hasAccount(sa1)) {
            System.out.printf("1. Name: " + sa1.getName() + " Balance: " + sa1.getBalance() + " Interest Rate: "
                    + sa1.getInterest());
        }
        if (losa.hasAccount(sa2)) {
            System.out.printf("\n2. Name: " + sa2.getName() + " Balance: " + sa2.getBalance() + " Interest Rate: "
                    + sa2.getInterest());
        }
        if (losa.hasAccount(sa3)) {
            System.out.printf("\n3. Name: " + sa3.getName() + " Balance: " + sa3.getBalance() + " Interest Rate: "
                    + sa3.getInterest());
        }
        System.out.println("\nWhich account would you like to access? *number*");
        int accOption = input.nextInt();
        if (accOption == 1) {
            savAccOptions(sa1);
        } else if (accOption == 2) {
            savAccOptions(sa2);
        } else {
            savAccOptions(sa3);
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
}

