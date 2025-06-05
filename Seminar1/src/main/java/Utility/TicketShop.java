package Utility;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Interfaces.TicketServiceInterface;
import Services.CustomerService;
import Services.EventService;
import Services.LogService;
import Services.TicketService;

public class TicketShop {


    private final EventService eventService;
    private final CustomerService customerService;
    private final TicketService ticketService;
    private final LogService logService;

    public TicketShop() {
        this.eventService = new EventService();
        this.customerService = new CustomerService();
        this.ticketService = new TicketService(customerService, eventService);
        this.logService = new LogService();

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

    public LogServiceInterface getLogService() { return logService; }

}
