package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class ListOfChequingAccountTest {
    private ListOfChequingAccount chequingAccounts;
    private ChequingAccount account1;
    private ChequingAccount account2;
    private ChequingAccount account3;

    @BeforeEach
    void setUp() {
        chequingAccounts = new ListOfChequingAccount("Joe");
        account1 = new ChequingAccount("Joe", 0.0);
        account2 = new ChequingAccount("Joe", 500.0);
        account3 = new ChequingAccount("Joe", 1000.0);
    }

    @Test
    void constructorTest() {
        assertEquals("Joe", chequingAccounts.getName());
        assertTrue(chequingAccounts.isEmpty());
    }

    @Test
    void getAccountTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);

        assertEquals(account1, chequingAccounts.getAccount(0));
        assertEquals(account2, chequingAccounts.getAccount(1));
        assertEquals(account3, chequingAccounts.getAccount(2));
    }

    @Test
    void addOneAccountTest() {
        chequingAccounts.addAccount(account1);
        assertEquals(1, chequingAccounts.length());
        assertEquals(account1, chequingAccounts.getAccount(0));
    }

    @Test
    void addMultipleAccountsTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);

        assertEquals(3, chequingAccounts.length());
        assertEquals(account1, chequingAccounts.getAccount(0));
        assertEquals(account2, chequingAccounts.getAccount(1));
        assertEquals(account3, chequingAccounts.getAccount(2));
    }

    @Test
    void removeOneAccountTest() {
        chequingAccounts.addAccount(account1);
        account1.suspend();
        chequingAccounts.removeAccount();
        assertEquals(0, chequingAccounts.length());
    }

    @Test
    void removeMultipleAccountsTest() {
        ChequingAccount account4 = new ChequingAccount("Joe", 0.0);
        ChequingAccount account5 = new ChequingAccount("Joe", 0.0);
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);
        chequingAccounts.addAccount(account4);
        chequingAccounts.addAccount(account5);

        assertEquals(5, chequingAccounts.length());
        account1.suspend();
        account4.suspend();
        account5.suspend();
        chequingAccounts.removeAccount();
        assertEquals(2, chequingAccounts.length());
        assertEquals(account2, chequingAccounts.getAccount(0));
        assertEquals(account3, chequingAccounts.getAccount(1));
    }

    @Test
    void addAllTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);
        chequingAccounts.addAccount(new ChequingAccount("Joe", 123.45));

        assertEquals(1623.45, chequingAccounts.addAll());
    }

    @Test
    void lengthTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);

        assertEquals(3, chequingAccounts.length());
    }

    @Test
    void isEmptyTest() {
        assertTrue(chequingAccounts.isEmpty());

        chequingAccounts.addAccount(account1);
        assertFalse(chequingAccounts.isEmpty());
    }

    @Test
    void hasAccountTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        assertTrue(chequingAccounts.hasAccount(account1));
        assertTrue(chequingAccounts.hasAccount(account2));
        assertFalse(chequingAccounts.hasAccount(account3));
    }

    @Test
    void hasAccountIndexTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        assertTrue(chequingAccounts.hasAccount(0));
        assertTrue(chequingAccounts.hasAccount(1));
        assertFalse(chequingAccounts.hasAccount(2));
    }

    @Test
    void removeAccountSpecificTest() {
        chequingAccounts.addAccount(account1);
        chequingAccounts.addAccount(account2);
        chequingAccounts.addAccount(account3);
        chequingAccounts.removeAccountSpecific(account1);
        assertFalse(chequingAccounts.hasAccount(account1));
        assertTrue(chequingAccounts.hasAccount(account2));
        assertTrue(chequingAccounts.hasAccount(account3));
        chequingAccounts.removeAccountSpecific(account2);
        assertFalse(chequingAccounts.hasAccount(account2));
        assertTrue(chequingAccounts.hasAccount(account3));
    }
}
