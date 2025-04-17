package org.example;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Scanner;

public class EventClient {

    EventService eventService;

public EventClient() {
    eventService = new EventService();
}

    public void createEvent() {
        Scanner sc = new Scanner(System.in);

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


        sc.close();
    }

    public void deleteEvent() {
    Scanner sc = new Scanner(System.in);
    System.out.println("Deleting a event");
    System.out.println("Please enter the id of the event you would like to delete");
    String input = sc.nextLine();
    eventService.deleteEvent(Integer.parseInt(input));
    }

    public void readEvent() {}

    public void updateEvent() {}

    public void readAllEvents() {
    System.out.println("Reading all the events");
    eventService.getAllEvents();
    }

    public void deleteAllEvents() {
    System.out.println("Deleting all the events");
    eventService.deleteAllEvents();
    }




}
