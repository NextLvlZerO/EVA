package org.example;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){
        Event valorantEvent = new Event(12, "valorant","Berlin", LocalDateTime.of(2025, 4, 16, 14, 30, 0),23);

        EventService eventService = new EventService();
        try {
            eventService.createEvent("Event1", "London", LocalDateTime.of(2025, 4, 16, 14, 30, 0),23);
            eventService.createEvent("Event2", "London", LocalDateTime.of(2025, 4, 16, 14, 30, 0),23);
            eventService.printAllEvents();
        }
        catch (NegativeNumberException e){
           System.out.println(e.getMessage());
        }


        EventClient eventClient = new EventClient();
        Scanner scanner = new Scanner(System.in);

        outer: while(true){


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
                case "1":eventClient.createEvent(scanner);
                break;
                case "2":eventClient.readEvent();
                break;
                case "3":eventClient.updateEvent();
                break;
                case "4":eventClient.deleteEvent(scanner);
                break;
                case "5":eventClient.readAllEvents();
                break;
                case "6":eventClient.deleteAllEvents();
                break;
                case "7": break outer;
            }

        }


    }
}
