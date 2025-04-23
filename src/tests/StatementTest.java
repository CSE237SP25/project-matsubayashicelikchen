// test/tests/StatementTest.java
package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.*;
import java.util.List;

import org.junit.jupiter.api.*;

import bankapp.Customer;
import bankapp.Statement;

public class StatementTest {

    private static final String BASE_DIR = "data/user/";
    private static final String FILE_NAME = "statement.txt";

    @BeforeEach
    public void setUp() throws IOException {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) deleteDirectory(baseDir);
        baseDir.mkdirs();
    }

    @AfterEach
    public void tearDown() throws IOException {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) deleteDirectory(baseDir);
    }

    private boolean deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) {
            for (File f : files) {
                if (f.isDirectory()) deleteDirectory(f);
                else f.delete();
            }
        }
        return dir.delete();
    }

    @Test
    public void testLoadStatementCreatesEmptyFile() {
        String username = "testUser";
        new File(BASE_DIR + username).mkdirs();

        Statement statement = new Statement(FILE_NAME);
        File stmtFile = new File(BASE_DIR + username, FILE_NAME);
        assertTrue(stmtFile.exists(), "Statement file should have been created.");

        Customer customer = new Customer(username, "pass", "Test", "User", "test@example.com");
        List<String> records = statement.getStatement(customer);
        assertNotNull(records, "Record list should not be null.");
        assertTrue(records.isEmpty(), "Record list should be empty.");
    }

    @Test
    public void testAddTransactionUpdatesContentAndFile() throws IOException {
        String username = "testUser";
        new File(BASE_DIR + username).mkdirs();

        Statement statement = new Statement(FILE_NAME);
        Customer customer = new Customer(username, "pass", "Test", "User", "test@example.com");

        statement.add(customer, 150);
        List<String> records = statement.getStatement(customer);
        assertEquals(1, records.size());
        assertEquals("Deposit | 150", records.get(0));

        File stmtFile = new File(BASE_DIR + username, FILE_NAME);
        try (BufferedReader reader = new BufferedReader(new FileReader(stmtFile))) {
            assertEquals("150", reader.readLine().trim());
            assertNull(reader.readLine(), "There should be only one line in the file.");
        }

        statement.add(customer, -50);
        records = statement.getStatement(customer);
        assertEquals(2, records.size());
        assertEquals("Deposit | 150", records.get(0));
        assertEquals("Withdraw | -50", records.get(1));

        try (BufferedReader reader = new BufferedReader(new FileReader(stmtFile))) {
            assertEquals("150", reader.readLine().trim());
            assertEquals("-50", reader.readLine().trim());
            assertNull(reader.readLine(), "No extra lines should be in the file.");
        }
    }
}
