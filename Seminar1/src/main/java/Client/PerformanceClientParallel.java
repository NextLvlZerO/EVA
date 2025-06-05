package Client;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Interfaces.TicketServiceInterface;
import Models.Ticket;
import Utility.TicketShop;
import Models.Customer;
import Models.Event;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.*;

public class PerformanceClientParallel {

    TicketShop ticketShop;
    EventServiceInterface eventService;
    CustomerServiceInterface customerService;
    TicketServiceInterface ticketService;
    LogServiceInterface logService;
    private final ExecutorService executor;

    public PerformanceClientParallel(TicketShop ticketShop) {
        this.ticketShop = ticketShop;
        this.eventService = ticketShop.getEventService();
        this.customerService = ticketShop.getCustomerService();
        this.ticketService = ticketShop.getTicketService();
        this.logService = ticketShop.getLogService();
        this.executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
    }

    public void stressTest() throws InterruptedException {

        final int startTime = (int) System.currentTimeMillis();

        CountDownLatch latchStep1 = new CountDownLatch(100);
        CountDownLatch latchStep2 = new CountDownLatch(1000);

        for (int i = 1; i <= 100; i++) {
            final int idx = i;
            executor.submit(() -> {
                try {
                    Event event = eventService.createEvent(
                            "foo " + idx,
                            "foo " + idx,
                            LocalDateTime.now().plusDays(idx),
                            100000
                    );
                    logService.info("Event created " + event);
                } catch (Exception e) {
                    logService.error(e.getMessage());
                } finally {
                    latchStep1.countDown();
                }
            });
        }

        for (int i = 0; i < 1000; i++) {
            executor.submit(() -> {
                try {
                    Customer c = customerService.createCustomer("Oskar suxx", "foo@web.de", LocalDate.of(2002, 10, 10));
                    logService.info("Customer created " + c);
                } catch (Exception E) {
                    logService.error(E.getMessage());
                } finally {
                    latchStep2.countDown();
                }
            });
        }

        latchStep1.await();
        latchStep2.await();

        List<Event> events = new ArrayList<>(eventService.getAllEvents());
        List<Customer> customers = new ArrayList<>(customerService.getAllCustomer());

        System.out.println(events);

        CountDownLatch latchStep3 = new CountDownLatch(events.size() * customers.size());

        for (int i = 0; i < events.size(); i++) {
            for (int j = 0; j < customers.size(); j++) {
                final Event currentEvent = events.get(i);
                final Customer currentCustomer = customers.get(j);
                System.out.println(currentEvent);
                System.out.println(currentCustomer);

                executor.submit(() -> {
                    try {
                        Ticket t = ticketService.createTicket(currentCustomer, currentEvent);
                        logService.info("Ticket created " + t);
                    } catch (Exception E) {
                        System.out.println("Latch3:");
                        logService.error(E.getMessage());
                    } finally {
                        latchStep3.countDown();
                    }
                });
            }
        }
        latchStep3.await();


        CountDownLatch latchStep4 = new CountDownLatch(100);
        for (int i = 101; i <= 200; i++) {
            final int idx = i;
            executor.submit(() -> {
                try {
                    Event e = eventService.createEvent(
                            "Event " + idx,
                            "Location " + idx,
                            LocalDateTime.now().plusDays(idx),
                            1000000
                    );
                    logService.info("Event created " + e);
                } catch (Exception e) {
                    logService.error(e.getMessage());
                } finally {
                    latchStep4.countDown();
                }
            });
        }
        latchStep4.await();

        List<Event> secondEvents = eventService.getAllEvents().subList(events.size(), eventService.getAllEvents().size());


        System.out.println("step 4 finished");


        CountDownLatch latchStep5 = new CountDownLatch(secondEvents.size() * customers.size() * 2);
        for (int i = 0; i < secondEvents.size(); i++) {
            for (int j = 0; j < customers.size(); j++) {

                final Event currentEvent = secondEvents.get(i);
                final Customer currentCustomer = customers.get(j);

                executor.submit(() -> {
                    try {
                        Ticket t1 = ticketService.createTicket(currentCustomer, currentEvent);
                        logService.info("Ticket created " + t1);
                        Ticket t2 = ticketService.createTicket(currentCustomer, currentEvent);
                        logService.info("Ticket created " + t2);
                    } catch (Exception ex) {
                        logService.error(ex.getMessage());
                    } finally {
                        latchStep5.countDown();
                        latchStep5.countDown();
                    }
                });
            }
        }
        latchStep5.await();

        int endTime = (int) System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");
        System.out.println("Finished test");
        logService.close();

        executor.shutdown();
        if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
            executor.shutdownNow();
        }
    }


}