package Models;

import java.time.LocalDate;

public class Ticket {
    private int id;
    private LocalDate date;
    private Customer customer;
    private Event event;

    public Ticket(int id, LocalDate date, Customer customer, Event event) {
        this.id = id;
        this.date = date;
        this.customer = customer;
        this.event = event;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public LocalDate getDate() {
        return date;
    }

    public void setDate(LocalDate date) {
        this.date = date;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    @Override
    public String toString() {
        return ("------------\nTicket mit:\nid:" + id
                + "\ndate: " + date
                + "\ncustomer: " + customer
                + "\nevent: " + event);
    }
}
