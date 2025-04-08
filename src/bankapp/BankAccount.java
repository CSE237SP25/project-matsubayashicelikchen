package bankapp;

public class BankAccount {
    private double balance;

    public BankAccount() {
        this.balance = 0;
    }

    public void deposit(double amount) {
        if (amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }

    public double getCurrentBalance() {
        return balance;
    }

    /* Withdraw method
       Removes user-specified amount from balance, validates
       desired withdrawal amount with ensureValid
    */
    public boolean withdraw(double amount) {
        if (ensureValid(amount)) {
            this.balance -= amount;
            return true;
        }
        return false;
    }


    /*
       Verifies user's withdrawal amount is positive and not
       greater than the amount in the user's balance. Throws
       IllegalArgumentException if either of the above cases
       is not true.
    */
    public boolean ensureValid(double withdrawalAmt) {
        if (withdrawalAmt > getCurrentBalance()) {
            throw new IllegalArgumentException("Attempting to withdraw sum greater than balance");
        } else if (withdrawalAmt <= 0) {
            throw new IllegalArgumentException("Must withdraw a positive sum of money");
        } else {
            return true;
        }
    }
}