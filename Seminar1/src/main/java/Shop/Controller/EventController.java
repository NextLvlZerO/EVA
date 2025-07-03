package Shop.Controller;

import Shop.Models.Event;
import Shop.Services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EventController {

    EventService eventService;

    @Autowired
    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @GetMapping("/getAllEvents")
    public ResponseEntity<List<Event>> getAllEvents() {
        List<Event> events = new ArrayList<>();
        LocalDateTime now = LocalDateTime.now().plusYears(1);
        try{
            for (int i = 0; i < 5; i++){
                events.add(eventService.createEvent("test", "foo", now, 1000));
            }
        }
        catch(Exception E) {E.printStackTrace();}
        return ResponseEntity.ok().body(events);
    }

}
