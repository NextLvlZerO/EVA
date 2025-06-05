package Client;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Interfaces.TicketServiceInterface;
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
               } catch (Exception E) {
                   E.printStackTrace();
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
                      ticketService.createTicket(currentCustomer, currentEvent);
                   } catch (Exception E) {
                       System.out.println("Latch3:");
                       E.printStackTrace();
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
                   eventService.createEvent(
                           "Event " + idx,
                           "Location " + idx,
                           LocalDateTime.now().plusDays(idx),
                           1000000
                   );
               } catch (Exception e) {
                   e.printStackTrace();
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
                       ticketService.createTicket(currentCustomer, currentEvent);
                       ticketService.createTicket(currentCustomer, currentEvent);
                   } catch (Exception ex) {
                       ex.printStackTrace();
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

       executor.shutdown();
       if (!executor.awaitTermination(30, TimeUnit.SECONDS)) {
           executor.shutdownNow();
       }
   }





    public void oldTest() {
        int startTime = (int) System.currentTimeMillis();
        System.out.println("Test");

        List<Thread> threads = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                try {
                    eventService.createEvent("test", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),2000);
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            threads.add(t);
            t.start();
        }

        for (Thread t : threads) {
            try{
                t.join();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        int temp = 0;
        for (Event event : eventService.getAllEvents()) {
            temp ++;
        }
        System.out.println(temp);

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

        List<Event> events2 = Collections.synchronizedList(new ArrayList<>());

        List<Thread> threads2 = new ArrayList<>();
        for (int i = 0; i < 100; i++) {
            Thread t = new Thread(() -> {
                try {
                    events2.add(eventService.createEvent("test", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),2000));
                }
                catch (Exception e) {
                    System.out.println(e.getMessage());
                }
            });
            threads2.add(t);
            t.start();
        }

        for (Thread t : threads2) {
            try{
                t.join();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }

        /*for (int i = 0; i < 100; i++) {
            try {
                events2.add(eventService.createEvent("test2", "fooo", LocalDateTime.of(LocalDate.of(2026,10,10), LocalTime.of(10,10)),4000));
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }*/

/*        for (Customer c : customerService.getAllCustomer()) {
            for (Event e : events2) {
                try {
                    ticketService.createTicket(c.getId(),e.getId());
                    ticketService.createTicket(c.getId(),e.getId());
                } catch (Exception ee) {
                    System.out.println(ee.getMessage());
                }

            }
        }*/

        List<Thread> threads3 = new ArrayList<>();
        for (Event e : events2) {
            for (Customer c : customerService.getAllCustomer()) {

               final Event currentEvent = e;
               final Customer currentCustomer = c;

               Thread t = new Thread(() -> {
                   try {
                       System.out.println(currentEvent.getId());
                       System.out.println(currentCustomer.getId());
                      ticketService.createTicket(currentEvent.getId(), currentCustomer.getId());
                   }
                   catch (Exception E){
                       System.out.println(E.getMessage());
                   }
               });
               threads3.add(t);
               t.start();

            }
        }

        for (Thread t : threads3) {
            try{
                t.join();
            }
            catch (Exception e){
                System.out.println(e.getMessage());
            }
        }


        int endTime = (int) System.currentTimeMillis();
        System.out.println("Time: " + (endTime - startTime) + "ms");

        System.out.println("Finished test");


    }

}
