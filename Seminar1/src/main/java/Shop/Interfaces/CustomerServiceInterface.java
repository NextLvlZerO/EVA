package Shop.Interfaces;

import Shop.Models.Customer;

import java.time.LocalDate;
import java.util.List;

public interface CustomerServiceInterface {
    Customer createCustomer(String username, String email, LocalDate birthday) throws IllegalArgumentException;

    void deleteCustomer(int id) throws IllegalArgumentException;

    void updateCustomer(int id, String username, String email, LocalDate birthday);

    Customer getCustomer(int id);

    void printAllCustomers();

    void deleteAllCustomers();

    List<Customer> getAllCustomer();
}
