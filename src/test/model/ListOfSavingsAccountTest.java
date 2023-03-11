package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfSavingsAccountTest {
    private ListOfSavingsAccount savingAccounts;
    private SavingsAccount account1;
    private SavingsAccount account2;
    private SavingsAccount account3;

    @BeforeEach
    void setUp() {
        savingAccounts = new ListOfSavingsAccount("Joe");
        account1 = new SavingsAccount("Joe", 0.0, 10.0);
        account2 = new SavingsAccount("Joe", 500.0, 1.25);
        account3 = new SavingsAccount("Joe", 1000.0, 2.5);
    }

    @Test
    void constructorTest() {
        assertEquals("Joe", savingAccounts.getName());
        assertTrue(savingAccounts.isEmpty());
    }

    @Test
    void getAccountTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);

        assertEquals(account1, savingAccounts.getAccount(0));
        assertEquals(account2, savingAccounts.getAccount(1));
        assertEquals(account3, savingAccounts.getAccount(2));
    }

    @Test
    void addOneAccountTest() {
        savingAccounts.addAccount(account1);
        assertEquals(1, savingAccounts.length());
        assertEquals(account1, savingAccounts.getAccount(0));
    }

    @Test
    void addMultipleAccountsTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);

        assertEquals(3, savingAccounts.length());
        assertEquals(account1, savingAccounts.getAccount(0));
        assertEquals(account2, savingAccounts.getAccount(1));
        assertEquals(account3, savingAccounts.getAccount(2));
    }

    @Test
    void removeOneAccountTest() {
        savingAccounts.addAccount(account1);
        account1.suspend();
        savingAccounts.removeAccount();
        assertEquals(0, savingAccounts.length());
    }

    @Test
    void removeMultipleAccountsTest() {
        SavingsAccount account4 = new SavingsAccount("Joe", 0.0, 2.45);
        SavingsAccount account5 = new SavingsAccount("Joe", 0.0, 1.25);
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);
        savingAccounts.addAccount(account4);
        savingAccounts.addAccount(account5);

        assertEquals(5, savingAccounts.length());
        account1.suspend();
        account4.suspend();
        account5.suspend();
        savingAccounts.removeAccount();
        assertEquals(2, savingAccounts.length());
        assertEquals(account2, savingAccounts.getAccount(0));
        assertEquals(account3, savingAccounts.getAccount(1));
    }

    @Test
    void addAllTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);
        savingAccounts.addAccount(new SavingsAccount("Joe", 123.45, 1.45));

        assertEquals(1623.45, savingAccounts.addAll());
    }

    @Test
    void lengthTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);

        assertEquals(3, savingAccounts.length());
    }

    @Test
    void addAllWithMonthsTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        savingAccounts.addAccount(account3);
        savingAccounts.addAccount(new SavingsAccount("Joe", 750.0, 1.05));

        assertEquals(2369.7499072812498, savingAccounts.addAllWithMonths(3));
        // 518.985352, 1076.89062, 773.873931
    }

    @Test
    void isEmptyTest() {
        assertTrue(savingAccounts.isEmpty());

        savingAccounts.addAccount(account1);
        assertFalse(savingAccounts.isEmpty());
    }

    @Test
    void hasAccountTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        assertTrue(savingAccounts.hasAccount(account1));
        assertTrue(savingAccounts.hasAccount(account2));
        assertFalse(savingAccounts.hasAccount(account3));
    }

    @Test
    void hasAccountIndexTest() {
        savingAccounts.addAccount(account1);
        savingAccounts.addAccount(account2);
        assertTrue(savingAccounts.hasAccount(0));
        assertTrue(savingAccounts.hasAccount(1));
        assertFalse(savingAccounts.hasAccount(2));
    }
}
