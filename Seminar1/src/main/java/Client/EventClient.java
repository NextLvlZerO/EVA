package Client;

import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Models.Event;
import Services.EventService;
import Exceptions.InvalidDateException;
import Exceptions.NegativeNumberException;

import java.time.LocalDateTime;
import java.util.Scanner;

public class EventClient {

    EventServiceInterface eventService;
    LogServiceInterface logService;

    public EventClient(EventServiceInterface eventService, LogServiceInterface logService) {
        this.eventService = eventService;
        this.logService = logService;
    }

    public void eventManagement(Scanner scanner) {
        while (true) {
            System.out.println("-----------------");
            System.out.println("What would you like to do?");
            System.out.println("-----------------");
            System.out.println("1 - Create a new event");
            System.out.println("2 - Get an event");
            System.out.println("3 - Update an event");
            System.out.println("4 - Delete a event");
            System.out.println("5 - Get all events");
            System.out.println("6 - Delete all events");
            System.out.println("7 - Close");

            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    createEvent(scanner);
                    break;
                case "2":
                    readEvent();
                    break;
                case "3":
                    updateEvent(scanner);
                    break;
                case "4":
                    deleteEvent(scanner);
                    break;
                case "5":
                    readAllEvents();
                    break;
                case "6":
                    deleteAllEvents();
                    break;
                case "7":
                    return;
            }
        }
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
            System.out.println(e.getMessage());
        }

    }

    public void deleteEvent(Scanner sc) {
        System.out.println("Deleting a event");
        System.out.println("Please enter the id of the event you would like to delete");
        String input = sc.nextLine();
        try {
            eventService.deleteEvent(Integer.parseInt(input));
            System.out.println("Event deleted");
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }

    }

    public void readEvent() {
        Scanner sc = new Scanner(System.in);
        System.out.println("Reading a event");
        System.out.println("Please enter the id of the event you would like to read");
        String input = sc.nextLine();
        Event event = eventService.getEvent(Integer.parseInt(input));
        if (event == null) {
            System.out.println("Event not found");
        } else {
            System.out.println(event);
        }
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
