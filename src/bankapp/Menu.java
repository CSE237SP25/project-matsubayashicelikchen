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
        int selectionScreen = 0;
        
        
        while(!exit) {
        	switch(selectionScreen) {
        	default:
        		testMenu.printOptions1();
                int option = testMenu.getUserInput();
                if (option == 1) {
                	exit = true;
                }
                testMenu.chooseOption1(option);
                selectionScreen = option;
        	case 1:
        		
        		break;
        	case 2:
        		exit = true;
        		break;
        	case 3:
        		selectionScreen = 0;
        		break;
        		
        	} 
        }
    }
    
    public void printOptions1() {
    	System.out.println("1. Exit");
    	System.out.println("2. Sign into Bank Account");
    	System.out.println("3. Create Bank Account");
    }
    
    public void chooseOption1(int option) {
    	switch(option) {
        case 1:
            System.out.println("you chose to exit");
            break;
        case 2:
            System.out.println("you chose to Sign in");
            break;
        case 3: 
            System.out.println("you chose to create a Bank Account");
            Customer newCustomer = createCustomer();
        	break;
        default:
            System.out.println("Invalid option.");
    	}
    }
    
    public void printOptions2() {
    	System.out.println("1. Exit");
    	
    	System.out.println("2. Access checkings account");
    	System.out.println("3. Access a savings account");
    	System.out.println("4. Access a credit account");
    }
    
    public void printOptions3() {
    	System.out.println("5. deposit money into checkings account");
    	System.out.println("6. withdraw money from checkings account");
    	
    	System.out.println("7. deposit into savings account");
    	System.out.println("8. withdraw money from checkings account");
    	
    	System.out.println("9. use credit");
    	System.out.println("10. pay off credit");
    }
    
    public int getUserInput() {
    	System.out.println("Select an option");
    	int userInput = keyboardInput.nextInt();
    	return userInput;
    }
    
    public Customer createCustomer() {
    	System.out.println("Enter First Name");
    	String firstName = keyboardInput.next();
    	
    	System.out.println("Enter Middle Name");
    	String middleName = keyboardInput.next();
    	
    	System.out.println("Enter Last Name");
    	String lastName = keyboardInput.next();
    	
    	System.out.println("Enter Email");
    	String email = keyboardInput.next();
    	
    	System.out.println("Enter Phone Number");
    	String phoneNumber = keyboardInput.next();
    	
    	System.out.println("Enter Address");
    	String address = keyboardInput.next();
    	
    	Customer customer = new Customer(firstName, middleName, lastName, email, phoneNumber, address);
    	return customer;
    }

}
