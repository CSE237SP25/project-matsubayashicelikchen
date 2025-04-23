package bankapp;

/**
 * Represents a simple bank account with basic deposit and withdrawal operations.
 */
public class BankAccount {
    private double balance;

    /**
     * Initializes a new BankAccount with a zero balance.
     */
    public BankAccount() {
        this.balance = 0;
    }

    /**
     * Deposits the specified amount into this account.
     *
     * @param amount the amount to deposit; must be non-negative
     * @throws IllegalArgumentException if amount is negative
     */
    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }

    /**
     * Returns the current balance of this account.
     *
     * @return the current balance
     */
    public double getCurrentBalance() {
        return balance;
    }

    /**
     * Attempts to withdraw the specified amount from this account.
     *
     * @param amount the amount to withdraw
     * @return true if the withdrawal succeeded; false otherwise
     * @throws IllegalArgumentException if amount is non-positive or exceeds the balance
     */
    public boolean withdraw(double amount) {
        if (ensureValid(amount)) {
            this.balance -= amount;
            return true;
        }
        return false;
    }

    /**
     * Validates that a withdrawal amount is positive and does not exceed the balance.
     *
     * @param withdrawalAmt the amount to validate
     * @return true if the amount is valid
     * @throws IllegalArgumentException if withdrawalAmt ≤ 0 or withdrawalAmt > balance
     */
    public boolean ensureValid(double withdrawalAmt) {
        if (withdrawalAmt > getCurrentBalance()) {
            throw new IllegalArgumentException("Attempting to withdraw more than the current balance");
        } else if (withdrawalAmt <= 0) {
            throw new IllegalArgumentException("Withdrawal amount must be positive");
        }
        return true;
    }
}
