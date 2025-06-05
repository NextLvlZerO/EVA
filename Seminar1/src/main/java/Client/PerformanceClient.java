package Client;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.TicketServiceInterface;
import Utility.TicketShop;
import Models.Customer;
import Models.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class PerformanceClient {

    TicketShop ticketShop;
    EventServiceInterface eventService;
    CustomerServiceInterface customerService;
    TicketServiceInterface ticketService;

    public PerformanceClient(TicketShop ticketShop) {
        this.ticketShop = ticketShop;
        this.eventService = ticketShop.getEventService();
        this.customerService = ticketShop.getCustomerService();
        this.ticketService = ticketShop.getTicketService();
    }

    public void stressTest() {
        int startTime = (int) System.currentTimeMillis();
        System.out.println("Test");

        for (int i = 0; i < 100; i++) {
            try {
                eventService.createEvent("test", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),2000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 1000; i++) {
            try {
                customerService.createCustomer("Oskar suxx", "foo@web.de", LocalDate.of(2002, 10, 10));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (Customer c : customerService.getAllCustomer()) {
            for (Event e : eventService.getAllEvents()) {
                try {
                    ticketService.createTicket(c.getId(),e.getId());
                    System.out.println("created Ticket");
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }

            }

        }

        List<Event> events2 = new ArrayList<>();

        for (int i = 0; i < 100; i++) {
            try {
                events2.add(eventService.createEvent("test2", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),4000));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (Customer c : customerService.getAllCustomer()) {
            for (Event e : events2) {
                try {
                    ticketService.createTicket(c.getId(),e.getId());
                    ticketService.createTicket(c.getId(),e.getId());
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }

            }
        }

        int endTime = (int) System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        System.out.println("Finished test");


    }

}
