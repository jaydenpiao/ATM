package persistence;

import model.ChequingAccount;
import model.SavingsAccount;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class JsonTest {

    protected void checkChequingAccount(String name, double balance, ChequingAccount account) {
        assertEquals(name, account.getName());
        assertEquals(balance, account.getBalance());
    }

    protected void checkSavingsAccount(String name, double balance, double interest, SavingsAccount account) {
        assertEquals(name, account.getName());
        assertEquals(balance, account.getBalance());
        assertEquals(interest, account.getInterest());
    }
}
