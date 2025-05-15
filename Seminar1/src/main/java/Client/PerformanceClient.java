package Client;

import Main.TicketShop;
import Models.Customer;
import Models.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;

public class PerformanceClient {

    TicketShop ticketShop;

    public PerformanceClient(TicketShop ticketShop) {
        this.ticketShop = ticketShop;
    }

    public void stressTest() {

        System.out.println("Test");

        for (int i = 0; i < 100; i++) {
            try {
                ticketShop.getEventService().createEvent("test", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        for (int i = 0; i < 1000; i++) {
            try {
                ticketShop.getCustomerService().createCustomer("Oskar suxx", "foo@web.de", LocalDate.of(2002, 10, 10));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        List<Event> events1 = ticketShop.getEventService().getAllEvents();

        for (Customer c : ticketShop.getCustomerService().getAllCustomer()) {
            for (Event e : ticketShop.getEventService().getAllEvents()) {
                try {
                    ticketShop.getTicketService().createTicket(c.getId(),e.getId());
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }

            }

        }

        for (int i = 0; i < 100; i++) {
            try {
                ticketShop.getEventService().createEvent("test2", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),1000);
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }

        List<Event> events2 = ticketShop.getEventService().getAllEvents();
        events2.removeAll(events1);

        for (Customer c : ticketShop.getCustomerService().getAllCustomer()) {
            for (Event e : events2) {
                try {
                    ticketShop.getTicketService().createTicket(c.getId(),e.getId());
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }

            }

        }

        System.out.println("Finished test");

    }

}
