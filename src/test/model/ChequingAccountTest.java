package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class ChequingAccountTest {
    private ChequingAccount zeroAccount;
    private ChequingAccount moneyAccount;

    @BeforeEach
    void setUp() {
        zeroAccount = new ChequingAccount("Joe", 0.0);
        moneyAccount = new ChequingAccount("Jen", 500.0);
    }

    @Test
    void constructorTest() {
        assertEquals("Joe", zeroAccount.getName());
        assertEquals(0.0, zeroAccount.getBalance());
        assertFalse(zeroAccount.isSuspended());

        assertEquals("Jen", moneyAccount.getName());
        assertEquals(500.0, moneyAccount.getBalance());
        assertFalse(moneyAccount.isSuspended());
    }

    @Test
    void isSuspendedTrueTest() {
        zeroAccount.suspend();
        assertTrue(zeroAccount.isSuspended());
    }

    @Test
    void isSuspendedFalseTest() {
        assertFalse(moneyAccount.isSuspended());
    }

    @Test
    void suspendZeroBalanceTest() {
        zeroAccount.suspend();
        assertTrue(zeroAccount.isSuspended());
    }

    @Test
    void depositOnceTest() {
        zeroAccount.deposit(0.01);
        assertEquals(0.01, zeroAccount.getBalance());

        moneyAccount.deposit(100.0);
        assertEquals(600.0, moneyAccount.getBalance());
    }

    @Test
    void depositMultipleTest() {
        zeroAccount.deposit(1.25);
        zeroAccount.deposit(5.75);
        assertEquals(7.0, zeroAccount.getBalance());

        moneyAccount.deposit(150.0);
        moneyAccount.deposit(200.45);
        moneyAccount.deposit(76.64);
        assertEquals(927.09, moneyAccount.getBalance());
    }

    @Test
    void withdrawOnceTest() {
        zeroAccount.withdraw(0.0);
        assertEquals(0.0, zeroAccount.getBalance());

        moneyAccount.withdraw(500.0);
        assertEquals(0.0, moneyAccount.getBalance());
    }

    @Test
    void withdrawMultipleTest() {
        moneyAccount.withdraw(0.01);
        assertEquals(499.99, moneyAccount.getBalance());
        moneyAccount.withdraw(99.99);
        assertEquals(400.0, moneyAccount.getBalance());
        moneyAccount.withdraw(400.0);
        assertEquals(0.0, moneyAccount.getBalance());
    }

    @Test
    void toStringTest() {
        assertEquals("Joe - $0.00", zeroAccount.toString());
        assertEquals("Jen - $500.00", moneyAccount.toString());
        ChequingAccount ca1 = new ChequingAccount("Evan", 678.494);
        ChequingAccount ca2 = new ChequingAccount("Oliver", 678.495);
        assertEquals("Evan - $678.49", ca1.toString());
        assertEquals("Oliver - $678.50", ca2.toString());
    }
}
