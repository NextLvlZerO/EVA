package Services;

import Models.Event;
import Exceptions.InvalidDateException;
import Exceptions.NegativeNumberException;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService implements Interfaces.EventServiceInterface{
    private List<Event> events;
    private IDService idservice;

    public EventService() {
        events = new ArrayList<>();
        idservice = new IDService();
    }

    @Override
    public void createEvent(String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException {
        if (tickets < 0) {
            throw new NegativeNumberException("ticket amount must be positive");
        }
        if (LocalDateTime.now().isAfter(datum)) {
            throw new InvalidDateException("ticket must be in future");
        }
        int id = idservice.addId();

        Event currentEvent = new Event(id, bezeichnung, ort, datum, tickets);
        events.add(currentEvent);
        System.out.println("Event successfully created, id: " + id);
    }

    @Override
    public Event getEvent(int id) {
        for (Event event : events) {
            if (event.getId() == id) {
                return event;
            }
        }
        return null;
    }

    @Override
    public void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException {
        if (tickets < 0) throw new NegativeNumberException("ticket amount must be positive");
        if (LocalDateTime.now().isAfter(datum)) {
            throw new InvalidDateException("ticket must be in future");
        }

        for (Event event : events) {
            if (event.getId() == id) {
                event.setBezeichnung(bezeichnung);
                event.setOrt(ort);
                event.setDatum(datum);
                event.setTickets(tickets);
                return;
            }
        }
    }

    @Override
    public void deleteEvent(int id) throws IllegalArgumentException {
        Event tempEvent = null;
        for (Event event : events) {
            if (event.getId() == id) {
                tempEvent = event;
            }
        }
        if (tempEvent == null) throw new IllegalArgumentException("Event with id " + id + " not found");

        int current_id = tempEvent.getId();
        idservice.removeId(current_id);
        events.remove(tempEvent);
    }

    @Override
    public void printAllEvents() {
        for (Event event : events) {
            System.out.println(event);
        }
    }

    @Override
    public void deleteAllEvents() {
        while (!events.isEmpty()) {
            events.remove(0);
        }
    }
}

