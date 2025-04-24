package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class CustomerClient {

    CustomerService customerService;

    public CustomerClient() {
        this.customerService = new CustomerService();
    }

    public void customerManagement(Scanner scanner){
        System.out.println("What would you like to do?");
        System.out.println("1 - Create a new customer");
        System.out.println("2 - Get a customer");
        System.out.println("3 - Update a customer");
        System.out.println("4 - Delete a customer");
        System.out.println("5 - Get all customers");
        System.out.println("6 - Delete all customers");
        System.out.println("7 - Exit");

        String command = scanner.nextLine();

        switch(command){
            case "1":createCustomer(scanner);
                break;


        }
    }

    public void createCustomer(Scanner scanner){
        System.out.println("Creating an new customer");

        System.out.println("Username:");
        String name = scanner.nextLine();

        System.out.println("Email:");
        String email = scanner.nextLine();

        System.out.println("Bithday:");
        String birthdayStr = scanner.nextLine();
        LocalDate birthday = LocalDate.parse(birthdayStr);

        try {
            customerService.createCustomer(name, email, birthday);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    public void deleteCustomer(Scanner scanner){
        System.out.println("Deleting a customer");
        System.out.println("Please enter the id of the event you would like to delete");
        String input = scanner.nextLine();
        customerService.deleteCustomer(Integer.parseInt(input));
    }


}
