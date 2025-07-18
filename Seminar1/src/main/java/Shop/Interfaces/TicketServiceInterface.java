package Shop.Interfaces;

import Shop.Models.Customer;
import Shop.Models.Event;
import Shop.Models.Ticket;

import java.util.List;

public interface TicketServiceInterface {
    Ticket createTicket(int customerId, int eventId) throws Exception;

    Ticket createTicket(Customer customer, Event event) throws Exception;

    boolean validateTicket(int ticketId, int customerId, int eventId);

    Ticket getTicket(int id);

    List<Ticket> getAllTickets();

    void deleteTicket(int id) throws Exception;

    void deleteAllTickets();
}
