package bankapp;

public class BankAccount {

    private double balance;
    private double creditBalance; 

    public BankAccount(double creditBalance) {
        if (creditBalance < 0) {
            throw new IllegalArgumentException("Credit balance cannot be negative");
        }
        this.balance = 0;
        this.creditBalance = creditBalance;
    }
    
    public void deposit(double amount) {
        if(amount < 0) {
            throw new IllegalArgumentException("Deposit amount cannot be negative");
        }
        this.balance += amount;
    }

    public double getCreditBalance(){
        return this.creditBalance;
    }

    public void changeCredit(double newCredit){
        if(newCredit < 0){
            throw new IllegalArgumentException("Credit cannot be negative");
        }
        this.creditBalance = newCredit;
    }

    public double getCurrentBalance() {
        return this.balance;
    }
}
