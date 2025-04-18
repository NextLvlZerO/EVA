package org.example;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    List<Event> events;
    IDService idservice;

    public EventService() {
        events = new ArrayList<>();
        idservice = new IDService();
    }

    public void createEvent(String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException {
        if (tickets < 0 && LocalDateTime.now().isAfter(datum))
            throw new NegativeNumberException("ticket must be positive");
        int id = idservice.addId();

        Event currentEvent = new Event(id, bezeichnung, ort, datum, tickets);
        events.add(currentEvent);
    }

    public void readEvent(int id) {
        for (Event event : events) {
            if (event.id == id) {
                System.out.println(event);
                return;
            }
        }
    }

    public void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException {
        if (tickets < 0) throw new NegativeNumberException("ticket must be positive");
        for (Event event : events) {
            if (event.id == id) {
                event.bezeichnung = bezeichnung;
                event.ort = ort;
                event.datum = datum;
                event.tickets = tickets;
                return;
            }
        }
    }

    public void deleteEvent(int id) {
        Event tempEvent = null;
        for (Event event : events) {
            if (event.id == id) {
                tempEvent = event;
            }
        }
        if (tempEvent != null) {
            int current_id = tempEvent.id;
            idservice.removeId(current_id);
            events.remove(tempEvent);
        }
    }

    public void printAllEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    public void deleteAllEvents() {
        while (!events.isEmpty()) {
            events.remove(0);
        }
    }
}

