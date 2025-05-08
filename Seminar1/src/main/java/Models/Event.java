package Models;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class Event {
    private int id;
    private String bezeichnung;
    private String ort;
    private LocalDateTime datum;
    private int tickets;
    private List<Ticket> ticketList = new ArrayList<>();

    public Event(int id, String bezeichnung, String ort, LocalDateTime datum, int tickets){
        this.id = id;
        this.bezeichnung = bezeichnung;
        this.ort = ort;
        this.datum = datum;
        this.tickets = tickets;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getBezeichnung() {
        return bezeichnung;
    }

    public void setBezeichnung(String bezeichnung) {
        this.bezeichnung = bezeichnung;
    }

    public String getOrt() {
        return ort;
    }

    public void setOrt(String ort) {
        this.ort = ort;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public void setDatum(LocalDateTime datum) {
        this.datum = datum;
    }

    public int getTickets() {
        return tickets;
    }

    public void setTickets(int tickets) {
        this.tickets = tickets;
    }

    @Override
    public String toString() {
        return ("------------\nEvent mit:\nid:" + id
                + "\nbezeichnung:" + bezeichnung
                + "\nort:" + ort
                + "\ndatum: " + datum
                + "\ntickets: " + tickets);
    }

    public List<Ticket> getTicketList() {
        return ticketList;
    }

    public void addTicket(Ticket ticket){
        this.ticketList.add(ticket);
    }

    public void deleteTicket(Ticket ticket){
        this.ticketList.remove(ticket);
    }
}

