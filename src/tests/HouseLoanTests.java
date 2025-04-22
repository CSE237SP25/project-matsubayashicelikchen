package tests;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import bankapp.HouseLoan;

public class HouseLoanTests {

	private static final double HOUSE_PRICE = 300000.00;
	private static final double DOWN_PAYMENT = 50000.00;

	private HouseLoan loan;

	@BeforeEach
	public void setUp() {
		loan = new HouseLoan(HOUSE_PRICE, DOWN_PAYMENT);
	}

	@Test
	public void testValidPaymentGeneralCase() {

		double paymentAmount = 1000;
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan() - paymentAmount;
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertTrue(isValidPayment, "Valid Payment should return true");
	}

	@Test
	public void testNegativePaymentGeneralCase() {

		double paymentAmount = -1000;
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan();
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertFalse(isValidPayment, "Invalid Payment should return false");
	}

	@Test
	public void testLeastPossibleValidPayment() {

		double paymentAmount = loan.getMinPaymentAmount();
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan() - paymentAmount;
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertTrue(isValidPayment, "Valid Payment should return true");
	}

	@Test
	public void testGreatestPossibleValidPayment() {

		double paymentAmount = loan.getLeftOverLoan();
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan() - paymentAmount;
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertTrue(isValidPayment, "Valid Payment should return true");
	}

	@Test
	public void testInvalidPaymentLowerEdgeCase() {

		double paymentAmount = loan.getMinPaymentAmount() - 0.01;
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan();
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertFalse(isValidPayment, "Invalid Payment should return flase");
	}

	@Test
	public void testInvalidPaymentUpperEdgeCase() {

		double paymentAmount = loan.getLeftOverLoan() + 0.01;
		boolean isValidPayment = loan.makePayment(paymentAmount);

		double expectedLeftOverLoan = loan.getPrincipalLoan();
		double actualLeftOverLoan = loan.getLeftOverLoan();

		assertEquals(expectedLeftOverLoan, actualLeftOverLoan, 0.001);
		assertFalse(isValidPayment, "Invalid Payment should return flase");
	}

}
