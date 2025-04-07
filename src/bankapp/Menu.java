package bankapp;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Customer customer = new Customer("John", "Doe", "john@example.com", "1234567890", "123 Main St");
        
        while(true) {
            System.out.println("\nSelect an option:");
            System.out.println("1. Deposit to Checking");
            System.out.println("2. Borrow Credit");
            System.out.println("3. Repay Credit");
            System.out.println("4. Check Checking Balance");
            System.out.println("5. Check Credit Balance");
            System.out.println("6. Open Savings Account");
            System.out.println("7. Deposit to Savings");
            System.out.println("8. Withdraw from Savings");
            System.out.println("9. Check Savings Balance");
            System.out.println("10. Transfer to Savings");
            System.out.println("11. Transfer from Savings");
            System.out.println("12. Calculate Interest");
            System.out.println("13. Exit");
            
            System.out.print("Enter your choice: ");
            int option = scanner.nextInt();
            
            switch(option) {
                // Existing checking account operations
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        customer.getaccount().deposit(depositAmount);
                        System.out.println("Deposited successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 2:
                    System.out.print("Enter amount to borrow: ");
                    double borrowAmount = scanner.nextDouble();
                    try {
                        customer.getaccount().borrowCredit(borrowAmount);
                        System.out.println("Credit borrowed successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 3:
                    System.out.print("Enter amount to repay: ");
                    double repayAmount = scanner.nextDouble();
                    try {
                        customer.getaccount().repayCredit(repayAmount);
                        System.out.println("Credit repaid successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 4:
                    System.out.println("Current checking balance: " + 
                        customer.getaccount().getCurrentBalance());
                    break;
                    
                case 5:
                    System.out.println("Credit balance: " + 
                        customer.getaccount().getCreditBalance());
                    break;
                    
                // New savings account operations
                case 6:
                    System.out.print("Enter initial deposit for savings account: ");
                    double initialDeposit = scanner.nextDouble();
                    try {
                        customer.openSavingsAccount(initialDeposit);
                        System.out.println("Savings account opened successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 7:
                    try {
                        System.out.print("Enter deposit amount: ");
                        double savingsDeposit = scanner.nextDouble();
                        customer.getSavingsAccount().deposit(savingsDeposit);
                        System.out.println("Deposited to savings successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 8:
                    try {
                        System.out.print("Enter withdrawal amount: ");
                        double savingsWithdraw = scanner.nextDouble();
                        if (customer.getSavingsAccount().withdraw(savingsWithdraw)) {
                            System.out.println("Withdrew from savings successfully.");
                        } else {
                            System.out.println("Withdrawal failed - insufficient funds.");
                        }
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 9:
                    try {
                        System.out.println("Savings balance: " + 
                            customer.getSavingsAccount().getBalance());
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 10:
                    try {
                        System.out.print("Enter transfer amount to savings: ");
                        double toSavings = scanner.nextDouble();
                        customer.transferToSavings(toSavings);
                        System.out.println("Transferred to savings successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 11:
                    try {
                        System.out.print("Enter transfer amount from savings: ");
                        double fromSavings = scanner.nextDouble();
                        customer.transferFromSavings(fromSavings);
                        System.out.println("Transferred from savings successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 12:
                    try {
                        customer.getSavingsAccount().calculateInterest();
                        System.out.println("Interest calculated. New balance: " + 
                            customer.getSavingsAccount().getBalance());
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                    
                case 13:
                    System.out.println("Exiting.");
                    scanner.close();
                    System.exit(0);
                    
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
