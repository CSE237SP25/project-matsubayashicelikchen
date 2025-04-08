package bankapp;

import java.util.HashMap;

public class CustomerBase {
    private static HashMap<Integer, Customer> customers = new HashMap<>();

    public static void addCustomer(Customer customer) {
        customers.put(customer.getCustomerID(), customer);
    }

    public static Customer getCustomer(int id) {
        return customers.get(id);
    }

    public static boolean customerExists(int id) {
        return customers.containsKey(id);
    }
}
