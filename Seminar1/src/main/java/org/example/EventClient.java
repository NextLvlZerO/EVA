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
        String eingabe = sc.nextLine();
        String[] data = eingabe.split(", ");

        try {
            eventService.createEvent(data[0], data[1], LocalDateTime.parse(data[2]), Integer.parseInt(data[3]));
        } catch (Exception e) {
            e.printStackTrace();
        }


        sc.close();
    }

    public void deleteEvent() {}

    public void readEvent() {}

    public void updateEvent() {}

    public void readAllEvents() {}

    public void deleteAllEvents() {}




}
