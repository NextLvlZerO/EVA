package org.example;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    List<Event> events = new ArrayList<>();

    public void createEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException{
        if (tickets < 0 && LocalDateTime.now().isAfter(datum) ) throw new NegativeNumberException("ticket must be positive");
        Event currentEvent = new Event(id, bezeichnung, ort, datum, tickets);
        events.add(currentEvent);
    }

    public void readEvent(int id){
        for (Event event : events){
            if (event.id == id){
                System.out.println(event);
                return;
            }
        }
    }

    public void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException{
        if (tickets < 0 ) throw new NegativeNumberException("ticket must be positive");
        for (Event event: events) {
            if (event.id == id){
                event.bezeichnung = bezeichnung;
                event.ort = ort;
                event.datum = datum;
                event.tickets = tickets;
                return;
            }
        }
    }

    public void deleteEvent(int id){
        Event tempEvent = null;
        for (Event event : events){
            if (event.id == id){
                tempEvent = event;
            }
        }
        if (tempEvent != null){
            events.remove(tempEvent);
        }
    }
}

