package Services;

import Main.Customer;
import Main.Event;
import Main.Ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService {

    IDService idService;
    List<Ticket> tickets;
    CustomerService customerService;
    EventService eventService;

   public TicketService(CustomerService customerService, EventService eventService) {
       idService = new IDService();
       tickets = new ArrayList<>();
       this.customerService = customerService;
       this.eventService = eventService;
   }

   public void createTicket(int customerId, int eventId) {

       Customer customer = customerService.readCustomer(customerId);
       Event event = eventService.readEvent(eventId);

       if (customer == null || event == null) throw new IllegalArgumentException("Customer or Event not found");

       LocalDate date = LocalDate.now();
       if (event.getTickets() <= 0){
           throw new IllegalArgumentException("No tickets left");
       }
       event.setTickets(event.getTickets() - 1);
       int id = idService.addId();

       Ticket ticket = new Ticket(id, date, customer, event);
       event.addTicket(ticket);
       customer.addTicket(ticket);
   }

   public Ticket readTicket(int id){
       for (Ticket ticket : tickets) {
           if (ticket.getId() == id) {
               System.out.println(ticket);
               return ticket;
           }
       }
       return null;
   }

   public void deleteTicket(int id){
       Ticket tempTicket = null;
       for (Ticket ticket : tickets){
           if (ticket.getId() == id){
               tempTicket = ticket;
               break;
           }
       }
       if (tempTicket == null) return;

       Event event = tempTicket.getEvent();
       event.setTickets(event.getTickets() + 1);
       event.deleteTicket(tempTicket);

       Customer customer = tempTicket.getCustomer();
       customer.deleteTicket(tempTicket);

       tickets.remove(tempTicket);
   }

}
