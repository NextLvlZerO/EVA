package Services;

import Interfaces.CustomerServiceInterface;
import Models.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService implements Interfaces.CustomerServiceInterface{

    private List<Customer> customers;
    private IDService idService;

    public CustomerService() {
        idService = new IDService();
        customers = new ArrayList<Customer>();
    }

    @Override
    public void createCustomer(String username, String email, LocalDate birthday) throws IllegalArgumentException {

        if (LocalDate.now().minusYears(18).minusDays(1).isBefore(birthday)) {
            throw new IllegalArgumentException("Customer has to be at least 18 years old");
        }
        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("invalid email");
        }

        Customer customer = new Customer(idService.addId(), username, email, birthday);

        customers.add(customer);


    }

    @Override
    public void deleteCustomer(int id) throws IllegalArgumentException {
        Customer tempCustomer = null;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                tempCustomer = customer;
                break;
            }
        }

        if (tempCustomer == null) {
            throw new IllegalArgumentException("Customer not found");
        }

        int current_id = tempCustomer.getId();
        idService.removeId(current_id);
        customers.remove(tempCustomer);
    }

    @Override
    public void updateCustomer(int id, String username, String email, LocalDate birthday) {

        if (LocalDate.now().minusYears(18).isBefore(birthday)) {
            throw new IllegalArgumentException("Customer has to be at least 18 years old");
        }
        if (email.contains("@")) {
            throw new IllegalArgumentException("invalid email");
        }

        Customer tempCustomer = null;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                tempCustomer = customer;
                break;
            }
        }

        if (tempCustomer != null) {
            tempCustomer.setUsername(username);
            tempCustomer.setEmail(email);
            tempCustomer.setBirthday(birthday);
        }
    }

    @Override
    public Customer getCustomer(int id) {

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                System.out.println(customer);
                return customer;
            }
        }
        return null;
    }

    @Override
    public void printAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        return customers;
    }

    @Override
    public void deleteAllCustomers() {
        customers.clear();
    }


}
