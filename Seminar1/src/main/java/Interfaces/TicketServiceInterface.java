package Interfaces;

import Models.Ticket;

import java.util.List;

public interface TicketServiceInterface {
    void createTicket(int customerId, int eventId) throws Exception;

    boolean validateTicket(int ticketId, int customerId, int eventId);

    Ticket getTicket(int id);

    List<Ticket> getAllTickets();

    void deleteTicket(int id) throws Exception;

    void deleteAllTickets();
}
