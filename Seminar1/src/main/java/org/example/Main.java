package org.example;
import java.time.LocalDateTime;

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
    }
}
