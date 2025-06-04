package Services;

import Models.Customer;
import Models.Event;
import Models.Ticket;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class TicketService implements Interfaces.TicketServiceInterface{

    IDService idService;
    List<Ticket> tickets;
    CustomerService customerService;
    EventService eventService;

   public TicketService(CustomerService customerService, EventService eventService) {
       idService = new IDService();
       tickets = new ArrayList<>();
       this.customerService = customerService;
       this.eventService = eventService;

       try{
           idService.addId();
       }
       catch (Exception e){
           System.out.println(e.getMessage());
       }
   }

   @Override
   public void createTicket(int customerId, int eventId) throws Exception {

       Customer customer = customerService.getCustomer(customerId);
       Event event = eventService.getEvent(eventId);

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

    @Override
    public void createTicket(Customer customer,Event event) throws Exception {

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

   @Override
   public boolean validateTicket(int ticketId, int customerId, int eventId) {
       if (getTicket(ticketId) == null) {
           return false;
       } else if (customerService.getCustomer(customerId) == null) {
           return false;
       } else if (eventService.getEvent(eventId) == null) {
           return false;
       } else if (getTicket(ticketId).getCustomer().getId() != customerId) {
           return false;
       } else return getTicket(ticketId).getEvent().getId() == eventId;
   }

   @Override
   public Ticket getTicket(int id){
       for (Ticket ticket : tickets) {
           if (ticket.getId() == id) {
               return ticket;
           }
       }
       return null;
   }

   @Override
   public List<Ticket> getAllTickets(){
       return tickets;
   }

   @Override
   public void deleteTicket(int id) throws Exception {
       Ticket tempTicket = null;
       for (Ticket ticket : tickets){
           if (ticket.getId() == id){
               tempTicket = ticket;
               break;
           }
       }
       if (tempTicket == null) throw new IllegalArgumentException("Ticket not found");

       Event event = tempTicket.getEvent();
       event.setTickets(event.getTickets() + 1);
       event.deleteTicket(tempTicket);

       Customer customer = tempTicket.getCustomer();
       customer.deleteTicket(tempTicket);

       tickets.remove(tempTicket);
   }

   @Override
   public void deleteAllTickets(){
       for (Ticket ticket : tickets) {
           tickets.remove(ticket);
       }
   }

}
