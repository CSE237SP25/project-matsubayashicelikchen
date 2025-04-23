package bankapp;

import java.util.HashMap;
import java.io.File;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

public class CustomerBase {
    private HashMap<String, Customer> users;
    public final String pathToData = "data/user/";
    
    /**
     * Constructs a new CustomerBase and loads existing customers'
     * information from a text file.
     */
    public CustomerBase() {
        users = new HashMap<>();
        loadCustomers();
    }

    /**
     * Loads customer data from the data/user directory.
     * Reads customer information from each user's "info.txt" file.
     * For each valid file, it creates a Customer object and adds
     * it to the users hash map.
     */
    private void loadCustomers() {
        File baseDir = new File(pathToData);
        if (!baseDir.exists() || !baseDir.isDirectory()) {
            return;
        }
        File[] userDirs = baseDir.listFiles(File::isDirectory);
        if (userDirs == null) return;
        for (File userDir : userDirs) {
            File infoFile = new File(userDir, "info.txt");
            if (!infoFile.exists() || !infoFile.isFile()) {
                continue;
            }
            try (BufferedReader br = new BufferedReader(new FileReader(infoFile))) {
                String username = br.readLine();
                String password = br.readLine();
                String firstName = br.readLine();
                String lastName = br.readLine();
                String email = br.readLine();
                String phone = br.readLine();
                if (username != null && password != null && firstName != null && lastName != null && email != null && phone != null) {
                    Customer customer = new Customer(username, password, firstName, lastName, email, phone);
                    users.put(username, customer);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
    
    
    /**
     * Adds a new customer to the system.
     * Saves the customer data to a text file.
     *
     * @param customer The customer to add.
     * @return true if the customer was added successfully,
     * false if the username already exists or there was an error
     */
    public boolean add(Customer customer) {
        if (users.containsKey(customer.getUsername())) {
            return false;
        }
        File userDir = new File(pathToData + customer.getUsername());
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File infoFile = new File(userDir, "info.txt");
        try (FileWriter fw = new FileWriter(infoFile)) {
            fw.write(customer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        users.put(customer.getUsername(), customer);
        return true;
    }
    
    /**
     * Checks if a customer with the given username exists.
     *
     * @param username The username to check.
     * @return true if the customer exists, false otherwise
     */
    public boolean exist(String username) {
    	return users.containsKey(username);
    }
    
    /**
     * Retrieves the customer associated with the given username.
     *
     * @param username The username of the customer
     * @return The Customer object if found, or null if not found
     */
    public Customer get(String username) {
    	return users.get(username);
    }
    
    /**
     * Updates the information of an existing customer in the text file and in the hash map.
     *
     * @param customer The customer with updated information
     * @return true if the update was successful,
     * false if the customer does not exist or there was an error
     */
    public boolean update(Customer customer) {
        if (!users.containsKey(customer.getUsername())) {
            return false;
        }
        File userDir = new File(pathToData + customer.getUsername());
        if (!userDir.exists()) {
            userDir.mkdirs();
        }
        File infoFile = new File(userDir, "info.txt");
        try (FileWriter fw = new FileWriter(infoFile)) {
            fw.write(customer.toString());
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        users.put(customer.getUsername(), customer);
        return true;
    }
    
    /**
     * Deletes a customer from the text file and the hash map.
     *
     * @param customer The customer to delete
     * @return true if the deletion was successful
     * false if the customer does not exist or deletion failed
     */
    public boolean delete(Customer customer) {
        if (!users.containsKey(customer.getUsername())) {
            return false;
        }
        File userDir = new File(pathToData + customer.getUsername());
        if (!deleteDirectory(userDir)) {
            return false;
        }
        users.remove(customer.getUsername());
        return true;
    }

    /**
     * Recursively deletes a directory and all of its contents.
     * 
     * @param directory The directory to delete
     * @return true if the directory and all contents were successfully deleted, false otherwise
     */
    private boolean deleteDirectory(File directory) {
        if (directory.exists()) {
            File[] files = directory.listFiles();
            if (files != null) {
                for (File file : files) {
                    if (file.isDirectory()) {
                        if (!deleteDirectory(file)) {
                            return false;
                        }
                    } else {
                        if (!file.delete()) {
                            return false;
                        }
                    }
                }
            }
        }
        return directory.delete();
    }
}
