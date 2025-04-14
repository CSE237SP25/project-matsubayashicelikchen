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
     
    public CustomerBase() {
        users = new HashMap<>();
        loadCustomers();
    }

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
    public boolean exist(String username) {
    	return this.users.containsKey(username);
    }
    public Customer get(String username) {
    	return this.users.get(username);
    }
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
