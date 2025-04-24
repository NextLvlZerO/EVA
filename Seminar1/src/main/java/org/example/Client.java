package org.example;

import java.time.LocalDateTime;
import java.util.Scanner;

public class Client {
    private EventService eventService;

    public Client() {
        eventService = new EventService();
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        outer: while(true){

            System.out.println("Management System");
            System.out.println("--------------------");
            System.out.println("1 - Event service");
            System.out.println("2 - Customer service");
            System.out.println("3 - Exit");

            String command = scanner.nextLine();
            switch(command){
                case "1":{
                    eventManagement(scanner);
                }
                case "2": {
                    customerManagement(scanner);
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
        System.out.println("4 - Delete a event");
        System.out.println("5 - Get all events");
        System.out.println("6 - Delete all events");
        System.out.println("7 - Exit");

        String command = scanner.nextLine();

        switch(command){
            case "1":createEvent(scanner);
                break;
            case "2":readEvent();
                break;
            case "3":updateEvent();
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
        System.out.println("TODO");
    }

    public void createEvent(Scanner sc) {

        System.out.println("Creating a new event");
        System.out.println("Please enter the data of the event you would like to create");
        System.out.println("Pattern: name, location, date, tickets");
        String input = sc.nextLine();
        String[] data = input.split(", ");

        try {
            eventService.createEvent(data[0], data[1], LocalDateTime.parse(data[2]), Integer.parseInt(data[3]));
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

    public void updateEvent() {
        Scanner sc = new Scanner(System.in);
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
