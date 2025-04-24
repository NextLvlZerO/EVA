package org.example;

import java.time.LocalDate;
import java.util.Scanner;

public class CustomerClient {

    CustomerService customerService;

    public CustomerClient() {
        this.customerService = new CustomerService();
    }

    public void customerManagement(Scanner scanner){
        while (true) {
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
                case "2":getCustomer(scanner);
                    break;
                case "3":updateCustomer(scanner);
                    break;
                case "4":deleteCustomer(scanner);
                    break;
                case "5":getAllCustomers();
                    break;
                case "6":deleteAllCustomers();
                    break;
                case "7": return;
            }
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

    public void getCustomer(Scanner scanner){
        System.out.println("Reading a customer");
        System.out.println("Please enter the id of the event you would like to read");
        String input = scanner.nextLine();
        customerService.readCustomer(Integer.parseInt(input));
    }

    public void updateCustomer(Scanner scanner){
        System.out.println("Updating a customer");
        System.out.println("Please enter the data of the event you would like to update");
        System.out.println("Pattern:id, username, email, birthday");
        String input = scanner.nextLine();
        customerService.updateCustomer(Integer.parseInt(input.split(",")[0]), input.split(",")[1], input.split(",")[2], LocalDate.parse(input.split(",")[3]));
    }

    public void getAllCustomers(){
        System.out.println("Reading all the customers");
        customerService.printAllCustomers();
    }

    public void deleteAllCustomers(){
        System.out.println("Deleting all the customers");
        customerService.deleteAllCustomers();
    }


}
