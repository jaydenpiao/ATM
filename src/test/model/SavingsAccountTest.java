package model;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class SavingsAccountTest {
    private SavingsAccount zeroAccount;
    private SavingsAccount moneyAccount;

    @BeforeEach
    void setUp() {
        zeroAccount = new SavingsAccount("Joe", 0.0, 10.55);
        moneyAccount = new SavingsAccount("Jen", 500.0, 2.75);
    }

    @Test
    void constructorTest() {
        assertEquals("Joe", zeroAccount.getName());
        assertEquals(0.0, zeroAccount.getBalance());
        assertEquals(10.55, zeroAccount.getInterest());

        assertEquals("Jen", moneyAccount.getName());
        assertEquals(500.0, moneyAccount.getBalance());
        assertEquals(2.75, moneyAccount.getInterest());
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
    void gainInterestOnce() {
        zeroAccount.gainInterest(1);
        assertEquals(0.0, zeroAccount.getBalance());

        moneyAccount.gainInterest(1);
        assertEquals(513.75, moneyAccount.getBalance());
    }

    @Test
    void gainInterestMultiple() {
        zeroAccount.gainInterest(1);
        zeroAccount.gainInterest(2);
        zeroAccount.gainInterest(3);
        assertEquals(0.0, zeroAccount.getBalance());

        moneyAccount.gainInterest(1);
        assertEquals(513.75, moneyAccount.getBalance());
        moneyAccount.gainInterest(2);
        assertEquals(542.394773, moneyAccount.getBalance());
        moneyAccount.gainInterest(3);
        assertEquals(588.384181, moneyAccount.getBalance());
    }
}
