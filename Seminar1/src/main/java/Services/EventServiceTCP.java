package Services;

import Exceptions.InvalidDateException;
import Exceptions.NegativeNumberException;
import Models.Event;
import Servers.ClientServer;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class EventServiceTCP implements Interfaces.EventServiceInterface{

    private Map<Integer, Event> events;
    private IDServiceParallel idserviceParallel;

    public EventServiceTCP() {
        events = new ConcurrentHashMap<>();
        idserviceParallel = new IDServiceParallel();
        try {
            idserviceParallel.addId();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }


    @Override
    public Event createEvent(String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException {
        if (tickets < 0) {
            throw new NegativeNumberException("ticket amount must be positive");
        }
        if (LocalDateTime.now().isAfter(datum)) {
            throw new InvalidDateException("ticket must be in future");
        }

        try {
            int id = idserviceParallel.addId();
        }catch (Exception e){
            System.out.println(e.getMessage());
        }
        String message = "EVENT,CREATE," + bezeichnung + "," + ort + "," + datum.toString() + "," + tickets;
        System.out.println(message);
        ClientServer clientServer = new ClientServer(message);
        clientServer.sendMessage(message);

        return null;
    }

    @Override
    public Event getEvent(int id) {
        return events.getOrDefault(id, null);
    }

    @Override
    public void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets) throws NegativeNumberException, InvalidDateException {
        if (tickets < 0) throw new NegativeNumberException("ticket amount must be positive");
        if (LocalDateTime.now().isAfter(datum)) {
            throw new InvalidDateException("ticket must be in future");
        }

        Event currentEvent = events.getOrDefault(id, null);
        if (currentEvent == null) return;

        currentEvent.setBezeichnung(bezeichnung);
        currentEvent.setOrt(ort);
        currentEvent.setDatum(datum);
        currentEvent.setTickets(tickets);
    }

    @Override
    public void deleteEvent(int id) throws IllegalArgumentException {

        Event tempEvent = events.remove(id);

        if (tempEvent == null) throw new IllegalArgumentException("Event with id " + id + " not found");

        int current_id = tempEvent.getId();
        idserviceParallel.removeId(current_id);
        events.remove(tempEvent);
    }

    @Override
    public void printAllEvents() {
        for (Map.Entry<Integer, Event> event : events.entrySet()) {
            System.out.println(event.getValue());
        }
    }

    @Override
    public List<Event> getAllEvents() {
        List<Event> eventList = new ArrayList<>();
        for (Map.Entry<Integer, Event> event : events.entrySet()){
            eventList.add(event.getValue());
        }
        return eventList;
    }

    @Override
    public void deleteAllEvents() {
        events.clear();
    }
}

