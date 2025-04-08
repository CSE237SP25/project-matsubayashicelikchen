package bankapp;

import java.util.Scanner;

public class Menu {

    private Scanner keyboardInput;

    public Menu() {
        keyboardInput = new Scanner(System.in);
    }

    public static void main(String[] args) {
        Menu testMenu = new Menu();
        boolean exit = false;

        while (!exit) {
            testMenu.printOptions1();
            int option = testMenu.getUserInput();
            switch (option) {
                case 1:
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                case 2:
                    testMenu.signIn();
                    break;
                case 3:
                    Customer newCustomer = testMenu.createCustomer();
                    CustomerBase.addCustomer(newCustomer);
                    System.out.println("Account created! Your Customer ID is: " + newCustomer.getCustomerID());
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void printOptions1() {
        System.out.println("\n--- Welcome to the Bank ---");
        System.out.println("1. Exit");
        System.out.println("2. Sign into Bank Account");
        System.out.println("3. Create Bank Account");
    }

    public int getUserInput() {
        System.out.print("Select an option: ");
        return keyboardInput.nextInt();
    }

    public Customer createCustomer() {
        System.out.print("First Name: ");
        String firstName = keyboardInput.next();

        System.out.print("Middle Name (type 'none' if none): ");
        String middleName = keyboardInput.next();
        if (middleName.equalsIgnoreCase("none")) middleName = null;

        System.out.print("Last Name: ");
        String lastName = keyboardInput.next();

        System.out.print("Email: ");
        String email = keyboardInput.next();

        System.out.print("Phone Number: ");
        String phoneNumber = keyboardInput.next();

        // Consume leftover newline
        keyboardInput.nextLine();

        System.out.print("Address: ");
        String address = keyboardInput.nextLine();

        System.out.print("Password: ");
        String password = keyboardInput.next();

        if (middleName != null) {
            return new Customer(firstName, middleName, lastName, email, phoneNumber, address, password);
        } else {
            return new Customer(firstName, lastName, email, phoneNumber, address, password);
        }
    }


    public void signIn() {
        System.out.print("Enter Customer ID: ");
        int id = keyboardInput.nextInt();

        if (!CustomerBase.customerExists(id)) {
            System.out.println("No customer with that ID.");
            return;
        }

        System.out.print("Enter Password: ");
        String password = keyboardInput.next();

        Customer customer = CustomerBase.getCustomer(id);

        if (!customer.authenticate(password)) {
            System.out.println("Incorrect password.");
            return;
        }

        System.out.println("Welcome, " + customer.getFirstName() + "!");
        userDashboard(customer);
    }

    public void userDashboard(Customer customer) {
        boolean signedIn = true;

        while (signedIn) {
            printOptions2();
            int option = getUserInput();

            switch (option) {
                case 1:
                    signedIn = false;
                    break;
                case 2:
                    handleChecking(customer);
                    break;
                case 3:
                    handleSavings(customer);
                    break;
                case 4:
                    handleCredit(customer);
                    break;
                default:
                    System.out.println("Invalid option.");
            }
        }
    }

    public void printOptions2() {
        System.out.println("\n--- Account Menu ---");
        System.out.println("1. Log Out");
        System.out.println("2. Access Checking Account");
        System.out.println("3. Access Savings Account");
        System.out.println("4. Access Credit Account");
    }

    public void handleChecking(Customer customer) {
        boolean done = false;
        while (!done) {
            System.out.println("\n-- Checking Account --");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Check Balance");
            System.out.println("4. Back");
            int option = getUserInput();
            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = keyboardInput.nextDouble();
                    customer.getCheckingAccount().deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = keyboardInput.nextDouble();
                    customer.getCheckingAccount().withdraw(wd);
                    break;
                case 3:
                    System.out.println("Balance: $" + customer.getCheckingAccount().getCurrentBalance());
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
    }

    public void handleSavings(Customer customer) {
        boolean done = false;
        while (!done) {
            System.out.println("\n-- Savings Account --");
            System.out.println("1. Deposit");
            System.out.println("2. Withdraw");
            System.out.println("3. Calculate Interest");
            System.out.println("4. Check Balance");
            System.out.println("5. Back");
            int option = getUserInput();
            switch (option) {
                case 1:
                    System.out.print("Enter amount to deposit: ");
                    double dep = keyboardInput.nextDouble();
                    customer.getSavingsAccount().deposit(dep);
                    break;
                case 2:
                    System.out.print("Enter amount to withdraw: ");
                    double wd = keyboardInput.nextDouble();
                    customer.getSavingsAccount().withdraw(wd);
                    break;
                case 3:
                    customer.getSavingsAccount().calculateInterest();
                    break;
                case 4:
                    System.out.println("Balance: $" + customer.getSavingsAccount().getBalance());
                    break;
                case 5:
                    done = true;
                    break;
            }
        }
    }

    public void handleCredit(Customer customer) {
        boolean done = false;
        while (!done) {
            System.out.println("\n-- Credit Account --");
            System.out.println("1. Borrow Credit");
            System.out.println("2. Repay Credit");
            System.out.println("3. Check Balance");
            System.out.println("4. Back");
            int option = getUserInput();
            switch (option) {
                case 1:
                    System.out.print("Enter amount to borrow: ");
                    double borrow = keyboardInput.nextDouble();
                    customer.getCreditAccount().borrowCredit(borrow);
                    break;
                case 2:
                    System.out.print("Enter amount to repay: ");
                    double repay = keyboardInput.nextDouble();
                    customer.getCreditAccount().repayCredit(repay);
                    break;
                case 3:
                    System.out.println("Credit Owed: $" + customer.getCreditAccount().getCreditBalance());
                    break;
                case 4:
                    done = true;
                    break;
            }
        }
    }
}
