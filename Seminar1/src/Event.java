import java.time.LocalDateTime;

public class Event {

    int id;
    String bezeichnung;
    String ort;
    LocalDateTime datum;
    int tickets;

    public Event(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets){
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.ort = ort;
        this.datum = datum;
        this.tickets = tickets;
    }
    public static void main(String[] args){

    }
}
