import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class EventService {
    List<Event> events = new ArrayList<>();

    public void createEvent(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets){
        Event currentEvent = new Event(id, bezeichnung, ort, datum, tickets);
        events.add(currentEvent);
    }
}
