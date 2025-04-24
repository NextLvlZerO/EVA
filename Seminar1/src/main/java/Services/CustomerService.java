package Services;

import Main.Customer;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class CustomerService {

    private List<Customer> customers;
    private IDService idService;

    public CustomerService() {
        idService = new IDService();
        customers = new ArrayList<Customer>();
    }

    public void createCustomer(String username, String email, LocalDate birthday) {

        if (LocalDate.now().minusYears(18).minusDays(1).isBefore(birthday)) {
            throw new IllegalArgumentException("Customer has to be at least 18 years old");
        }
        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("invalid email");
        }

        Customer customer = new Customer(idService.addId(), username, email, birthday);

        customers.add(customer);


    }

    public void deleteCustomer(int id) {
        Customer tempCustomer = null;
        for (Customer customer : customers) {
            if (customer.getId() == id) {
                tempCustomer = customer;
                break;
            }
        }
        if (tempCustomer != null) {
            int current_id = tempCustomer.getId();
            idService.removeId(current_id);
            customers.remove(tempCustomer);
        }
    }

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

    public Customer readCustomer(int id) {

        for (Customer customer : customers) {
            if (customer.getId() == id) {
                System.out.println(customer);
                return customer;
            }
        }
        return null;
    }

    public void printAllCustomers() {
        for (Customer customer : customers) {
            System.out.println(customer);
        }
    }

    public void deleteAllCustomers() {
        customers.clear();
    }


}
