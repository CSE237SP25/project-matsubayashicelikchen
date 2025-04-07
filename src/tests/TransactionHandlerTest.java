package bankapp;

import org.junit.jupiter.api.*;
import java.io.*;
import java.nio.file.*;
import java.util.List;
import static org.junit.jupiter.api.Assertions.*;

class TransactionHandlerTest {
    private static final String TEST_TRANSACTIONS_FILE = "test_transactions.csv";
    private static final String TEST_ACCOUNTS_FILE = "test_accounts.csv";
    private BankAccount account1;
    private BankAccount account2;

    @BeforeAll
    static void setupClass() {
        // Override the file paths for testing
        TransactionHandler.TRANSACTIONS_FILE = TEST_TRANSACTIONS_FILE;
        TransactionHandler.ACCOUNTS_FILE = TEST_ACCOUNTS_FILE;
    }

    @BeforeEach
    void setUp() throws IOException {
        // Clear test files before each test
        Files.deleteIfExists(Paths.get(TEST_TRANSACTIONS_FILE));
        Files.deleteIfExists(Paths.get(TEST_ACCOUNTS_FILE));

        // Create fresh accounts for each test
        account1 = new BankAccount();
        account2 = new BankAccount();
        account1.deposit(1000.00); // Initialize with funds
    }

    @AfterEach
    void tearDown() throws IOException {
        // Clean up test files after each test
        Files.deleteIfExists(Paths.get(TEST_TRANSACTIONS_FILE));
        Files.deleteIfExists(Paths.get(TEST_ACCOUNTS_FILE));
    }

    @Test
    void testSuccessfulTransfer() {
        // Perform transfer
        boolean result = TransactionHandler.transfer(account1, account2, 200.00, "Test transfer");

        // Verify transfer result
        assertTrue(result);
        assertEquals(800.00, account1.getCurrentBalance(), 0.001);
        assertEquals(200.00, account2.getCurrentBalance(), 0.001);
    }

    @Test
    void testTransferRecordsTransaction() throws IOException {
        TransactionHandler.transfer(account1, account2, 150.00, "Coffee payment");

        // Verify transaction was recorded
        List<String> lines = Files.readAllLines(Paths.get(TEST_TRANSACTIONS_FILE));
        assertEquals(1, lines.size());
        assertTrue(lines.get(0).contains("150.00"));
        assertTrue(lines.get(0).contains("Coffee payment"));
    }

    @Test
    void testTransferUpdatesAccountFiles() throws IOException {
        TransactionHandler.transfer(account1, account2, 300.00, "Rent payment");

        // Verify account balances were saved
        List<String> accountLines = Files.readAllLines(Paths.get(TEST_ACCOUNTS_FILE));
        assertEquals(2, accountLines.size()); // Should have 2 account records
    }

    @Test
    void testTransferWithInsufficientFunds() {
        boolean result = TransactionHandler.transfer(account1, account2, 1500.00, "Large transfer");

        assertFalse(result);
        assertEquals(1000.00, account1.getCurrentBalance(), 0.001);
        assertEquals(0.00, account2.getCurrentBalance(), 0.001);
    }

    @Test
    void testTransferWithNegativeAmount() {
        boolean result = TransactionHandler.transfer(account1, account2, -50.00, "Invalid transfer");

        assertFalse(result);
        assertEquals(1000.00, account1.getCurrentBalance(), 0.001);
        assertEquals(0.00, account2.getCurrentBalance(), 0.001);
    }

    @Test
    void testTransferWithZeroAmount() {
        boolean result = TransactionHandler.transfer(account1, account2, 0.00, "Zero transfer");

        assertFalse(result);
        assertEquals(1000.00, account1.getCurrentBalance(), 0.001);
        assertEquals(0.00, account2.getCurrentBalance(), 0.001);
    }

    @Test
    void testConsecutiveTransfers() {
        // First transfer
        boolean result1 = TransactionHandler.transfer(account1, account2, 200.00, "First transfer");
        // Second transfer
        boolean result2 = TransactionHandler.transfer(account1, account2, 300.00, "Second transfer");

        assertTrue(result1);
        assertTrue(result2);
        assertEquals(500.00, account1.getCurrentBalance(), 0.001);
        assertEquals(500.00, account2.getCurrentBalance(), 0.001);

        try {
            List<String> transactions = Files.readAllLines(Paths.get(TEST_TRANSACTIONS_FILE));
            assertEquals(2, transactions.size());
        } catch (IOException e) {
            fail("Failed to read transaction file");
        }
    }
}