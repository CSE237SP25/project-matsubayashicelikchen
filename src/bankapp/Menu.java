package bankapp;

import java.util.Scanner;

public class Menu {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        BankAccount account = new BankAccount();
        while(true) {
            System.out.println("Select an option:");
            System.out.println("1. Deposit");
            System.out.println("2. Borrow Credit");
            System.out.println("3. Repay Credit");
            System.out.println("4. Check Balance");
            System.out.println("5. Check Credit Balance");
            System.out.println("6. Exit");
            int option = scanner.nextInt();
            switch(option) {
                case 1:
                    System.out.print("Enter deposit amount: ");
                    double depositAmount = scanner.nextDouble();
                    try {
                        account.deposit(depositAmount);
                        System.out.println("Deposited successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 2:
                    System.out.print("Enter amount to borrow: ");
                    double borrowAmount = scanner.nextDouble();
                    try {
                        account.borrowCredit(borrowAmount);
                        System.out.println("Credit borrowed successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 3:
                    System.out.print("Enter amount to repay: ");
                    double repayAmount = scanner.nextDouble();
                    try {
                        account.repayCredit(repayAmount);
                        System.out.println("Credit repaid successfully.");
                    } catch(Exception e) {
                        System.out.println(e.getMessage());
                    }
                    break;
                case 4:
                    System.out.println("Current balance: " + account.getCurrentBalance());
                    break;
                case 5:
                    System.out.println("Credit balance: " + account.getCreditBalance());
                    break;
                case 6:
                    System.out.println("Exiting.");
                    scanner.close();
                    System.exit(0);
                default:
                    System.out.println("Invalid option.");
            }
        }
    }
}
