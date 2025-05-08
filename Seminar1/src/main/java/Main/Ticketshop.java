package Main;

import Client.CustomerClient;
import Client.EventClient;
import Client.TicketClient;
import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.TicketServiceInterface;
import Services.CustomerService;
import Services.EventService;
import Services.TicketService;

public class Ticketshop {


    private final EventService eventService;
    private final CustomerService customerService;
    private final TicketService ticketService;

    public Ticketshop() {
        this.eventService = new EventService();
        this.customerService = new CustomerService();
        this.ticketService = new TicketService(customerService, eventService);

    }

    public TicketServiceInterface getTicketService() {
        return ticketService;
    }

    public CustomerServiceInterface getCustomerService() {
        return customerService;
    }

    public EventServiceInterface getEventService() {
        return eventService;
    }

}
