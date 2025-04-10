import java.time.LocalDateTime;

public class Event {

    String bezeichnung;
    String ort;
    LocalDateTime datum;
    int tickets;

    public Event(String bezeichnung, String ort, LocalDateTime datum, int tickets){
        this.bezeichnung = bezeichnung;
        this.ort = ort;
        this.datum = datum;
        this.tickets = tickets;
    }
    public static void main(String[] args){

    }
}
