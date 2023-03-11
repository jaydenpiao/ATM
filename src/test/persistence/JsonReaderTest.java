package persistence;

import model.ChequingAccount;
import model.ListOfChequingAccount;
import model.ListOfSavingsAccount;
import model.SavingsAccount;
import org.junit.jupiter.api.Test;

import java.io.IOException;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.fail;

public class JsonReaderTest extends JsonTest {

    @Test
    void testReaderNonExistentFileCheq() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfChequingAccount loca = reader.readCheq();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderNonExistentFileSav() {
        JsonReader reader = new JsonReader("./data/noSuchFile.json");
        try {
            ListOfSavingsAccount losa = reader.readSav();
            fail("IOException expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testReaderEmptyChequing() {
        JsonReader reader = new JsonReader("./data/testReaderEmptyChequing.json");
        try {
            ListOfChequingAccount loca = reader.readCheq();
            assertEquals("Chequing Accounts", loca.getName());
            assertEquals(0, loca.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderEmptySavings() {
        JsonReader reader = new JsonReader("./data/testReaderEmptySavings.json");
        try {
            ListOfSavingsAccount losa = reader.readSav();
            assertEquals("Saving Accounts", losa.getName());
            assertEquals(0, losa.length());
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralChequing() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralChequing.json");
        try {
            ListOfChequingAccount loca = reader.readCheq();
            assertEquals("Chequing Accounts", loca.getName());
            List<ChequingAccount> chequingAccounts = loca.getChequingAccounts();
            assertEquals(2, chequingAccounts.size());
            checkChequingAccount("joe", 500, chequingAccounts.get(0));
            checkChequingAccount("evan", 400, chequingAccounts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }

    @Test
    void testReaderGeneralSavings() {
        JsonReader reader = new JsonReader("./data/testReaderGeneralSavings.json");
        try {
            ListOfSavingsAccount losa = reader.readSav();
            assertEquals("Saving Accounts", losa.getName());
            List<SavingsAccount> savingsAccounts = losa.getSavingAccounts();
            assertEquals(2, savingsAccounts.size());
            checkSavingsAccount("jennifer", 200, 2.5, savingsAccounts.get(0));
            checkSavingsAccount("jay", 600, 5.5, savingsAccounts.get(1));
        } catch (IOException e) {
            fail("Couldn't read from file");
        }
    }
}
