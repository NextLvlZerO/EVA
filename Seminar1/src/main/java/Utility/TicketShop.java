package Utility;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Interfaces.TicketServiceInterface;
import Services.CustomerService;
import Services.EventService;
import Services.LogService;
import Services.TicketService;

import java.time.LocalDateTime;
import java.util.Arrays;

public class TicketShop {


    private final EventService eventService;
    private final CustomerService customerService;
    private final TicketService ticketService;
    private final LogService logService;

    public enum type {
        EVENT, CUSTOMER, TICKET
    }

    public enum method {
        CREATE, UPDATE, DELETE
    }

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

    public void convertString(String input) {
        String[] tokens = input.split(",");
        tokens = Arrays.stream(tokens).map(String::trim).toArray(String[]::new);
        String sType = tokens[0].toUpperCase();
        String method = tokens[1];
        String[] params = Arrays.copyOfRange(tokens, 2, tokens.length);
        type eType = type.valueOf(sType);
        switch (eType) {
            case EVENT:
                cónvertStringEvent(method, params);
                break;
            case CUSTOMER:
                break;
            case TICKET:
                convertStringTicket(method, params);
                break;
            default:

        }
    }

    private void convertStringTicket(String sMethod, String[] params) {
        method eMethod = method.valueOf(sMethod.toUpperCase());
        switch (eMethod) {
            case CREATE:
                try {
                    if (params.length != 2) {
                        throw new IllegalArgumentException("Wrong number of parameters");
                    }
                    int customerId = Integer.parseInt(params[0]);
                    int ticketId = Integer.parseInt(params[1]);
                    ticketService.createTicket(customerId, ticketId);
                } catch (Exception e) {
                    logService.error(e.getMessage());
                }
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
            default:
                throw new IllegalArgumentException("Wrong method");
        }
    }


    private void cónvertStringEvent(String sMethod, String[] params) {
        method eMethod = method.valueOf(sMethod.toUpperCase());
        switch (eMethod) {
            case CREATE:
                try {
                    if (params.length != 4) {
                        throw new IllegalArgumentException("Wrong number of parameters");
                    }
                    String bezeichnung = params[0];
                    String ort = params[1];
                    LocalDateTime date = LocalDateTime.parse(params[2]);
                    int tickets = Integer.parseInt(params[3]);
                    eventService.createEvent(bezeichnung, ort, date, tickets);
                } catch (Exception e) {
                    logService.error(e.getMessage());
                }
                break;
            case UPDATE:
                break;
            case DELETE:
                break;
            default:
                throw new IllegalArgumentException("Wrong method");
        }
    }
}
