import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    List<Event> events = new ArrayList<>();

    public void createEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets){
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

    public void updateEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets){
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
        for (Event event : events){
            if (event.id == id){
                events.remove(event);
            }
        }
    }
}
