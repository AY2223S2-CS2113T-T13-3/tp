package seedu.bankwithus;

 import org.junit.jupiter.api.Test;
 import seedu.bankwithus.exceptions.NegativeAmountException;

 import java.io.ByteArrayInputStream;
 import java.io.InputStream;
 import java.util.Scanner;

 import static org.junit.jupiter.api.Assertions.assertEquals;
 import static org.junit.jupiter.api.Assertions.assertThrows;

class AccountListTest {
    Ui ui = new Ui();

    @Test
     void depositMoney_notANumber_expectException() {
         String amountString = "abc";
         String name = "Bob";
         String balance = "0";
         AccountList accountList = new AccountList();
         accountList.addAccount(name, balance);
         assertThrows(NumberFormatException.class,
                 () -> accountList.depositMoney(amountString));
     }

    @Test
    void depositMoney_negativeNumber_expectException() {
        String amountString = "-1000";
        String name = "Bob";
        String balance = "100";
        AccountList accountList = new AccountList();
        accountList.addAccount(name, balance);
        assertThrows(NegativeAmountException.class,
                () -> accountList.depositMoney(amountString));
    }

     @Test
     void depositMoney_largeAmount_expectDeposit() {
         String amountString = "12345678.85";
         String name = "Bob";
         String balance = "100.05";
         AccountList accountList = new AccountList();
         Ui ui = new Ui();
         accountList.addAccount(name, balance);
         try {
             accountList.depositMoney(amountString);
         } catch (NumberFormatException e) {
             ui.showNumberFormatError();
         } catch (NullPointerException e) {
             ui.showNullInputError();
         } catch (NegativeAmountException e) {
             ui.showNegativeAmountError();
         }
         assertEquals("12345778.9", accountList.getMainAccount().getAccountBalance());
     }


     @Test
     void testingAddAccount() {
         AccountList TestAccountList = new AccountList();
         TestAccountList.addAccount("Jane", "1000");
         assertEquals("Jane", TestAccountList.getMainAccount().getName());
         assertEquals("1000", TestAccountList.getMainAccount().getAccountBalance());
     }
}
