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

public class JsonWriterTest extends JsonTest {

    @Test
    void testWriterInvalidFileCheq() {
        try {
            ListOfChequingAccount loca = new ListOfChequingAccount("Chequing Accounts");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterInvalidFileSav() {
        try {
            ListOfSavingsAccount losa = new ListOfSavingsAccount("Saving Accounts");
            JsonWriter writer = new JsonWriter("./data/my\0illegal:fileName.json");
            writer.open();
            fail("IOException was expected");
        } catch (IOException e) {
            // pass
        }
    }

    @Test
    void testWriterEmptyChequing() {
        try {
            ListOfChequingAccount loca = new ListOfChequingAccount("Chequing Accounts");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptyChequing.json");
            writer.open();
            writer.writeChequing(loca);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptyChequing.json");
            loca = reader.readCheq();
            assertEquals("Chequing Accounts", loca.getName());
            assertEquals(0, loca.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterEmptySavings() {
        try {
            ListOfSavingsAccount losa = new ListOfSavingsAccount("Saving Accounts");
            JsonWriter writer = new JsonWriter("./data/testWriterEmptySavings.json");
            writer.open();
            writer.writeSaving(losa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterEmptySavings.json");
            losa = reader.readSav();
            assertEquals("Saving Accounts", losa.getName());
            assertEquals(0, losa.length());
        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralChequing() {
        try {
            ListOfChequingAccount loca = new ListOfChequingAccount("Chequing Accounts");
            loca.addAccount(new ChequingAccount("joe", 500.0));
            loca.addAccount(new ChequingAccount("evan", 400.0));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralChequing.json");
            writer.open();
            writer.writeChequing(loca);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralChequing.json");
            loca = reader.readCheq();
            assertEquals("Chequing Accounts", loca.getName());
            List<ChequingAccount> chequingAccounts = loca.getChequingAccounts();
            assertEquals(2, chequingAccounts.size());
            checkChequingAccount("joe", 500, chequingAccounts.get(0));
            checkChequingAccount("evan", 400, chequingAccounts.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }

    @Test
    void testWriterGeneralSavings() {
        try {
            ListOfSavingsAccount losa = new ListOfSavingsAccount("Saving Accounts");
            losa.addAccount(new SavingsAccount("jennifer", 200.0, 2.5));
            losa.addAccount(new SavingsAccount("jay", 600.0, 5.5));
            JsonWriter writer = new JsonWriter("./data/testWriterGeneralSavings.json");
            writer.open();
            writer.writeSaving(losa);
            writer.close();

            JsonReader reader = new JsonReader("./data/testWriterGeneralSavings.json");
            losa = reader.readSav();
            assertEquals("Saving Accounts", losa.getName());
            List<SavingsAccount> savingsAccounts = losa.getSavingAccounts();
            assertEquals(2, savingsAccounts.size());
            checkSavingsAccount("jennifer", 200, 2.5, savingsAccounts.get(0));
            checkSavingsAccount("jay", 600, 5.5, savingsAccounts.get(1));

        } catch (IOException e) {
            fail("Exception should not have been thrown");
        }
    }
}
