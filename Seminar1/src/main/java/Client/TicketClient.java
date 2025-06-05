package Client;

import Interfaces.CustomerServiceInterface;
import Interfaces.EventServiceInterface;
import Interfaces.LogServiceInterface;
import Interfaces.TicketServiceInterface;
import Models.Ticket;
import Services.CustomerService;
import Services.EventService;
import Services.TicketService;

import java.util.List;
import java.util.Scanner;

public class TicketClient {

    TicketServiceInterface ticketService;
    CustomerServiceInterface customerService;
    EventServiceInterface eventService;
    LogServiceInterface logService;

    public TicketClient(EventServiceInterface eventService, CustomerServiceInterface customerService, TicketServiceInterface ticketService, LogServiceInterface logService) {
        this.logService = logService;
        this.ticketService = ticketService;
        this.customerService = customerService;
        this.eventService = eventService;
    }

    public void ticketManagement(Scanner scanner) {

        while(true){
            System.out.println("-----------------");
            System.out.println("What would you like to do?");
            System.out.println("-----------------");
            System.out.println("1 - Create a new ticket");
            System.out.println("2 - Get a ticket");
            System.out.println("3 - Delete a ticket");
            System.out.println("4 - Get all tickets");
            System.out.println("5 - Delete all tickets");
            System.out.println("6 - Close");

            String command = scanner.nextLine();

            switch (command) {
                case "1":
                    createTicket(scanner);
                    break;
                case "2":
                    readTicket(scanner);
                    break;
                case "3":
                    deleteTicket(scanner);
                    break;
                case "4":
                    readAllTickets();
                    break;
                case "5":
                    deleteAllTickets();
                    break;
                case "6":
                    return;
            }
        }
    }



    private void createTicket(Scanner scanner) {
        System.out.println("Creating an new ticket");

        System.out.println("Customer id:");
        int customerId = scanner.nextInt();

        System.out.println("Event id");
        int eventId = scanner.nextInt();


        try {
            ticketService.createTicket(customerId, eventId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readTicket(Scanner scanner) {
        System.out.println("Reading a ticket");
        System.out.println("Ticket id:");
        int ticketId = scanner.nextInt();

        Ticket ticket = ticketService.getTicket(ticketId);
        if (ticket != null) {
            System.out.println(ticket.toString());
        } else {
            System.out.println("Ticket not found");
        }
    }

    private void deleteTicket(Scanner scanner) {
        System.out.println("Deleting a ticket");
        System.out.println("Ticket id:");
        int ticketId = scanner.nextInt();
        try {
            ticketService.deleteTicket(ticketId);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
    }

    private void readAllTickets() {
        System.out.println("Reading all tickets:");
        List<Ticket> tickets = ticketService.getAllTickets();
        for (Ticket ticket : tickets) {
            System.out.println(ticket.toString());
        }
    }

    private void deleteAllTickets() {
        ticketService.deleteAllTickets();
        System.out.println("All tickets deleted successfully");
    }


}
