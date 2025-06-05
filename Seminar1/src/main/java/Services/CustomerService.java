package Services;

import Interfaces.CustomerServiceInterface;
import Models.Customer;

import java.time.LocalDate;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

public class CustomerService implements Interfaces.CustomerServiceInterface{

    private Map<Integer, Customer> customers;
    private IDServiceParallel idService;

    public CustomerService() {
        idService = new IDServiceParallel();
        customers = new ConcurrentHashMap<>();
    }

    @Override
    public Customer createCustomer(String username, String email, LocalDate birthday) throws IllegalArgumentException {

        if (LocalDate.now().minusYears(18).minusDays(1).isBefore(birthday)) {
            throw new IllegalArgumentException("Customer has to be at least 18 years old");
        }
        if (!email.contains("@") || email.indexOf("@") != email.lastIndexOf("@") || email.indexOf("@") > email.lastIndexOf(".")) {
            throw new IllegalArgumentException("invalid email");
        }


        Customer customer = null;
        try{
            customer = new Customer(idService.addId(), username, email, birthday);
            customers.put(customer.getId(),customer);
        }
        catch (Exception e){
            System.out.println(e.getMessage());
        }
        return customer;
    }

    @Override
    public void deleteCustomer(int id) throws IllegalArgumentException {

        Customer tempCustomer = customers.remove(id);

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

        Customer tempCustomer = customers.getOrDefault(id, null);

        if (tempCustomer != null) {
            tempCustomer.setUsername(username);
            tempCustomer.setEmail(email);
            tempCustomer.setBirthday(birthday);
        }
    }

    @Override
    public Customer getCustomer(int id) {
       return customers.getOrDefault(id, null);
    }

    @Override
    public void printAllCustomers() {
        for (Map.Entry<Integer, Customer> customer : customers.entrySet()) {
            System.out.println(customer.getValue());
        }
    }

    @Override
    public List<Customer> getAllCustomer() {
        List<Customer> customerList = new ArrayList<>();
        for (Map.Entry<Integer, Customer> customer : customers.entrySet()){
            customerList.add(customer.getValue());
        }
        return customerList;
    }

    @Override
    public void deleteAllCustomers() {
        customers.clear();
    }


}
