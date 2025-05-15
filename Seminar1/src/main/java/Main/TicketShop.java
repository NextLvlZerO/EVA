package Main;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.TicketServiceInterface;
import Services.CustomerService;
import Services.EventService;
import Services.TicketService;

public class TicketShop {


    private final EventService eventService;
    private final CustomerService customerService;
    private final TicketService ticketService;

    public TicketShop() {
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
