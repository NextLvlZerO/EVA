package org.example;
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


    public String toString() {
        return ("Event mit: id:" + id
                + ", bezeichnung:" + bezeichnung
                + ", ort:" + ort
                + ", datum: " + datum
                + ", tickets: " + tickets);
    }
}

