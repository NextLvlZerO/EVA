package org.example;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    private EventService eventService;
    private CustomerService customerService;

    public Client() {
        eventService = new EventService();
        customerService = new CustomerService();
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        outer: while(true){

            System.out.println("Management System");
            System.out.println("-----------------");
            System.out.println("1 - Event service");
            System.out.println("2 - Customer service");
            System.out.println("3 - Exit");

            String command = scanner.nextLine();
            switch(command){
                case "1":{
                    eventManagement(scanner);
                    break;
                }
                case "2": {
                    customerManagement(scanner);
                    break;
                }
                case "3": {
                    System.exit(0);
                }
            }
        }
    }

    public void eventManagement(Scanner scanner){
        System.out.println("What would you like to do?");
        System.out.println("1 - Create a new event");
        System.out.println("2 - Get an event");
        System.out.println("3 - Update an event");
        System.out.println("4 - Delete an event");
        System.out.println("5 - Get all events");
        System.out.println("6 - Delete all events");
        System.out.println("7 - Exit");

        String command = scanner.nextLine();

        switch(command){
            case "1":createEvent(scanner);
                break;
            case "2":readEvent();
                break;
            case "3":updateEvent(scanner);
                break;
            case "4":deleteEvent(scanner);
                break;
            case "5":readAllEvents();
                break;
            case "6":deleteAllEvents();
                break;
            case "7": System.exit(0);
        }
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

    public void createEvent(Scanner sc) {

        System.out.println("Creating a new event");

        System.out.println("Name:");
        String name = sc.nextLine();

        System.out.println("Location:");
        String location = sc.nextLine();

        System.out.println("Date (yyyy-MM-dd):");
        String date = sc.nextLine();

        System.out.println("Tickets:");
        int tickets = sc.nextInt();

//        System.out.println("Please enter the data of the event you would like to create");
 //       System.out.println("Pattern: name, location, date, tickets");
  //      String input = sc.nextLine();
   //     String[] data = input.split(", ");

        try {
            eventService.createEvent(name, location, LocalDateTime.parse(date+"T10:10:10"), tickets);
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    public void deleteEvent(Scanner sc) {
    System.out.println("Deleting a event");
    System.out.println("Please enter the id of the event you would like to delete");
    String input = sc.nextLine();
    eventService.deleteEvent(Integer.parseInt(input));
    }

    public void readEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Reading a event");
        System.out.println("Please enter the id of the event you would like to read");
        String input = sc.nextLine();
        eventService.readEvent(Integer.parseInt(input));
    }

    public void updateEvent(Scanner sc) {
        System.out.println("Updating a event");
        System.out.println("Please enter the data of the event you would like to update");
        System.out.println("Pattern:id, name, location, date, tickets");

        String input = sc.nextLine();
        String[] inputArray = input.split(", ");
        try {
            eventService.updateEvent(Integer.parseInt(inputArray[0]), inputArray[1], inputArray[2], LocalDateTime.parse(inputArray[3]), Integer.parseInt(inputArray[4]));
        }
        catch (NegativeNumberException | InvalidDateException e) {
            System.out.println(e.getMessage());
        }
    }

    public void readAllEvents() {
    System.out.println("Reading all the events");
    eventService.printAllEvents();
    }

    public void deleteAllEvents() {
    System.out.println("Deleting all the events");
    eventService.deleteAllEvents();
    }
}
