package Utility;

import Interfaces.*;
import Models.Customer;
import Models.Event;
import Models.Ticket;
import Services.CustomerService;
import Services.EventService;
import Services.LogService;
import Services.TicketService;

import java.time.LocalDate;
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

    public Object convertString(String input) throws Exception {
        String[] tokens = input.split(",");
        tokens = Arrays.stream(tokens).map(String::trim).toArray(String[]::new);
        String sType = tokens[0].toUpperCase();
        String method = tokens[1];
        String[] params = Arrays.copyOfRange(tokens, 2, tokens.length);
        type eType = type.valueOf(sType);
        try {

            switch (eType) {
                case EVENT:
                    return convertStringEvent(method, params);
                case CUSTOMER:
                    return convertStringCustomer(method, params);
                case TICKET:
                    return convertStringTicket(method, params);
                default:
                    return null;

            }
        }
        catch(Exception e){
                throw new Exception(e.getMessage());
            }
        }

        private Ticket convertStringTicket (String sMethod, String[]params){
            method eMethod = method.valueOf(sMethod.toUpperCase());
            switch (eMethod) {
                case CREATE:
                    try {
                        if (params.length != 2) {
                            throw new IllegalArgumentException("Wrong number of parameters");
                        }
                        int customerId = Integer.parseInt(params[0]);
                        int ticketId = Integer.parseInt(params[1]);
                        return ticketService.createTicket(customerId, ticketId);
                    } catch (Exception e) {
                        logService.error(e.getMessage());
                        throw new IllegalArgumentException("Error creating ticket: " + e.getMessage());
                    }
                case UPDATE:
                    return null;
                case DELETE:
                    return null;
                default:
                    throw new IllegalArgumentException("Wrong method");

            }

        }

        private Customer convertStringCustomer (String sMethod, String[]params){
            method eMethod = method.valueOf(sMethod.toUpperCase());
            switch (eMethod) {
                case CREATE:
                    try {
                        if (params.length != 3) {
                            throw new IllegalArgumentException("Wrong number of parameters");
                        }
                        String username = params[0];
                        String email = params[1];
                        LocalDate birthday = LocalDate.parse(params[2]);
                        return customerService.createCustomer(username, email, birthday);
                    } catch (Exception e) {
                        logService.error(e.getMessage());
                        throw new IllegalArgumentException("Error creating customer: " + e.getMessage());
                    }
                case UPDATE:
                    return null;
                case DELETE:
                    return null;
                default:
                    throw new IllegalArgumentException("Wrong method");
            }
        }


        private Event convertStringEvent (String sMethod, String[]params){
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
                        return eventService.createEvent(bezeichnung, ort, date, tickets);
                    } catch (Exception e) {
                        logService.error(e.getMessage());
                        throw new IllegalArgumentException("Error creating event: " + e.getMessage());
                    }
                case UPDATE:
                    return null;
                case DELETE:
                    return null;
                default:
                    throw new IllegalArgumentException("Wrong method");
            }
        }

    }