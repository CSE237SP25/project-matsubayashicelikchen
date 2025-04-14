package tests;

import static org.junit.jupiter.api.Assertions.*;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.List;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.Customer;
import bankapp.Statement;

public class StatementTest {

    private final String BASE_DIR = "data/user/";
    private final String FILE_NAME = "statement.txt";

    @BeforeEach
    public void setUp() throws IOException {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) {
            deleteDirectory(baseDir);
        }
        baseDir.mkdirs();
    }

    @AfterEach
    public void tearDown() throws IOException {
        File baseDir = new File(BASE_DIR);
        if (baseDir.exists()) {
            deleteDirectory(baseDir);
        }
    }
    
    private boolean deleteDirectory(File dir) {
        File[] files = dir.listFiles();
        if (files != null) { 
            for (File f : files) {
                if (f.isDirectory()) {
                    deleteDirectory(f);
                } else {
                    f.delete();
                }
            }
        }
        return dir.delete();
    }
    
    @Test
    public void testLoadStatementCreatesEmptyFile() {
        String username = "testUser";
        File userDir = new File(BASE_DIR + username);
        userDir.mkdirs();
        
        Statement statement = new Statement(FILE_NAME);
        File statementFile = new File(userDir, FILE_NAME);
        assertTrue(statementFile.exists(), "Statement file should have been created.");
        
        Customer customer = new Customer(username, "pass", "Test", "User", "test@example.com");
        List<Integer> transactions = statement.getStatement(customer);
        assertNotNull(transactions, "Transaction list should not be null.");
        assertTrue(transactions.isEmpty(), "Transaction list should be empty.");
    }
    
    @Test
    public void testAddTransactionUpdatesContentAndFile() throws IOException {
        String username = "testUser";
        File userDir = new File(BASE_DIR + username);
        userDir.mkdirs();
        
        Statement statement = new Statement(FILE_NAME);
        Customer customer = new Customer(username, "pass", "Test", "User", "test@example.com");
        
        statement.add(customer, 150);
        List<Integer> transactions = statement.getStatement(customer);
        assertNotNull(transactions);
        assertEquals(1, transactions.size());
        assertEquals(150, transactions.get(0));
        
        File statementFile = new File(userDir, FILE_NAME);
        try (BufferedReader reader = new BufferedReader(new FileReader(statementFile))) {
            String line = reader.readLine();
            assertNotNull(line, "File should contain a line.");
            assertEquals("150", line.trim());
            assertNull(reader.readLine(), "There should be only one line in the file.");
        }
        
        statement.add(customer, -50);
        transactions = statement.getStatement(customer);
        assertEquals(2, transactions.size());
        assertEquals(150, transactions.get(0));
        assertEquals(-50, transactions.get(1));
        
        try (BufferedReader reader = new BufferedReader(new FileReader(statementFile))) {
            String line1 = reader.readLine();
            String line2 = reader.readLine();
            assertNotNull(line1);
            assertNotNull(line2);
            assertEquals("150", line1.trim());
            assertEquals("-50", line2.trim());
            assertNull(reader.readLine(), "No extra lines should be in the file.");
        }
    }
}
