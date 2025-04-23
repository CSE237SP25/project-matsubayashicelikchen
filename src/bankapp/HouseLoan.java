package bankapp;

public class HouseLoan {

	private static final double MIN_PAYMENT = 0;

	private double homePrice;
	private double downPayment;
	private double interestRate;
	private double principalLoan;
	private double leftOverLoan;

	public HouseLoan(double homePrice, double downPayment) {
		this.homePrice = homePrice;
		this.downPayment = downPayment;
		this.interestRate = 2.15;
		// Simulating monthly interest by applying high interest amount to principal loan amount
		this.principalLoan = (homePrice - downPayment) * interestRate;
		this.leftOverLoan = principalLoan;
	}

	/**
	 * Accepts a valid payment to pay off the loan. Otherwise, returns false indicating 
	 * that the amount entered is invalid.
	 * 
	 * @param paymentAmount The amount of money the user wants to pay off from the loan
	 * @return Whether the transaction was successful or not.
	 */
	public boolean makePayment(double paymentAmount) {
		
		if (paymentAmount < MIN_PAYMENT) {
			System.out.println("Payment must be greater than $" + MIN_PAYMENT);
			return false;
		} else if (paymentAmount > leftOverLoan) {
			System.out.println("Payment must be less than or equal to the left over loan: $" + leftOverLoan);
			return false;
		} else {
			System.out.println("Payment Successful!");
			leftOverLoan = leftOverLoan - paymentAmount;
			if (leftOverLoan < 0.01) {
				leftOverLoan = 0;
			}
			System.out.println("Left over loan to pay: $" + leftOverLoan);
			return true;
		}
		
	}

	// Getters for all fields
	public double getMinPaymentAmount() {
		return MIN_PAYMENT;
	}

	public double getHomePrice() {
		return homePrice;
	}

	public double getDownPaymentAmount() {
		return downPayment;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public double getPrincipalLoan() {
		return principalLoan;
	}

	public double getLeftOverLoan() {
		return leftOverLoan;
	}
	
	
	
	/**
	 * Serializes the house loan data to a string
	 * @return string representation of loan data
	 */
	public String toDataString() {
	    return String.join(":",
	        String.valueOf(homePrice),
	        String.valueOf(downPayment),
	        String.valueOf(interestRate),
	        String.valueOf(principalLoan),
	        String.valueOf(leftOverLoan)
	    );
	}

	/**
	 * Deserializes house loan data from string
	 * @param data string containing loan data
	 */
	public void fromDataString(String data) {
	    String[] parts = data.split(":");
	    if (parts.length == 5) {
	        this.homePrice = Double.parseDouble(parts[0]);
	        this.downPayment = Double.parseDouble(parts[1]);
	        this.interestRate = Double.parseDouble(parts[2]);
	        this.principalLoan = Double.parseDouble(parts[3]);
	        this.leftOverLoan = Double.parseDouble(parts[4]);
	    }
	}

	// Add setters for all fields that need to be modified
	public void setHomePrice(double homePrice) {
	    this.homePrice = homePrice;
	}

	public void setDownPayment(double downPayment) {
	    this.downPayment = downPayment;
	}

	public void setInterestRate(double interestRate) {
	    this.interestRate = interestRate;
	}

	public void setPrincipalLoan(double principalLoan) {
	    this.principalLoan = principalLoan;
	}

	public void setLeftOverLoan(double leftOverLoan) {
	    this.leftOverLoan = leftOverLoan;
	}
}
