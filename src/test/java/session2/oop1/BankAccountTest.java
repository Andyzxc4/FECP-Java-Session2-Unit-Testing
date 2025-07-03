package session2.oop1;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.BeforeEach;
import static org.junit.jupiter.api.Assertions.*;

class BankAccountTest {
    private BankAccount bankAccountWithInitialDeposit;
    private BankAccount bankAccountNoInitialDeposit;

    @BeforeEach
    void setup() {
        /*
            initialized objects with parameters for constructor to behave as "initial deposit operations"
            bankAccountWithInitialDeposit -> when user agree to have an initial deposit upon creation of bank account
            bankAccountNoInitialDeposit -> when user DOES NOT agree to have an initial deposit upon creation of bank account
         */
        bankAccountWithInitialDeposit = new BankAccount("Andre1", "101", 1000.00);
        bankAccountNoInitialDeposit = new BankAccount("Andre2", "102");
    }

    //  test creation of account with deposits
    @Test
    void testBankAccountWithInitialDeposit() {
        assertEquals("Andre1", bankAccountWithInitialDeposit.getHolder());
        assertEquals("101", bankAccountWithInitialDeposit.getAccountNumber());
        assertEquals(1000.00, bankAccountWithInitialDeposit.getBalance());
    }

    //  test creation of account with without deposits
    @Test
    void testBankAccountNoInitialDeposit() {
        assertEquals("Andre2", bankAccountNoInitialDeposit.getHolder());
        assertEquals("102", bankAccountNoInitialDeposit.getAccountNumber());
        assertEquals(0.00, bankAccountNoInitialDeposit.getBalance());
    }

    //  test withdraw valid amounts
    @Test
    void testWithdrawValidAmount() {
        bankAccountWithInitialDeposit.withdraw(500.00);
        assertEquals(500.00, bankAccountWithInitialDeposit.getBalance());
    }

    //  test withdraw zero amount
    @Test
    void testWithdrawZeroAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountWithInitialDeposit.withdraw(0.00);
        });
        assertEquals("\nWithdraw amount must be positive.", exception.getMessage());
    }

    //  test withdraw negative amount
    @Test
    void testWithdrawNegativeAmount() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountWithInitialDeposit.withdraw(-500.00);
        });
        assertEquals("\nWithdraw amount must be positive.", exception.getMessage());
    }

    //  test withdraw more than balance
    @Test
    void testWithdrawMoreThanBalance() {
        IllegalArgumentException exception = assertThrows(IllegalArgumentException.class, () -> {
            bankAccountWithInitialDeposit.withdraw(6000.00);
        });
        assertEquals("\nYou can't withdraw amount larger than your balance.", exception.getMessage());
    }

    //  test get account number
    @Test
    void testGetAccountNumber() {
        assertEquals("101", bankAccountWithInitialDeposit.getAccountNumber());
        assertEquals("102", bankAccountNoInitialDeposit.getAccountNumber());
    }
}