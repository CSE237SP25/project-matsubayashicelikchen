package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import bankapp.FinancialStatement;

public class FinancialStatementTest {
    @Test
    public void testInitialStatement() {
        FinancialStatement fs = new FinancialStatement();
        String expected = "initial:\t0.0\n";
        assertEquals(expected, fs.toString());
    }

    @Test
    public void testAdd() {
        FinancialStatement fs = new FinancialStatement();
        fs.add(100.0);
        String expected = "initial:\t0.0\n+\t100.0\n";
        assertEquals(expected, fs.toString());
    }

    @Test
    public void testMinus() {
        FinancialStatement fs = new FinancialStatement();
        fs.minus(50.0);
        String expected = "initial:\t0.0\n-\t50.0\n";
        assertEquals(expected, fs.toString());
    }

    @Test
    public void testMixedTransactions() {
        FinancialStatement fs = new FinancialStatement();
        fs.add(150.5);
        fs.minus(25.25);
        fs.add(10.0);
        String expected = "initial:\t0.0\n+\t150.5\n-\t25.25\n+\t10.0\n";
        assertEquals(expected, fs.toString());
    }
}
