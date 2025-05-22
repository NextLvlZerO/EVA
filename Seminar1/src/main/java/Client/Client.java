package Client;

import Main.TicketShop;

import java.util.Scanner;

public class Client {

    private final TicketShop ticketshop;
    private final EventClient eventClient;
    private final CustomerClient customerClient;
    private final TicketClient ticketClient;
    private final PerformanceClient performanceClient;
    private final PerformanceClientParallel performanceClientParallel;


    public Client() {
        this.ticketshop = new TicketShop();
        this.eventClient = new EventClient(ticketshop.getEventService());
        this.customerClient = new CustomerClient(ticketshop.getCustomerService());
        this.ticketClient = new TicketClient(ticketshop.getEventService(), ticketshop.getCustomerService(), ticketshop.getTicketService());
        this.performanceClient = new PerformanceClient(ticketshop);
        this.performanceClientParallel = new PerformanceClientParallel(ticketshop);
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        outer: while(true){

            System.out.println("-----------------");
            System.out.println("Management System");
            System.out.println("-----------------");
            System.out.println("1 - Event service");
            System.out.println("2 - Customer service");
            System.out.println("3 - Ticket service");
            System.out.println("4 - Performance service");
            System.out.println("5 - Performance service parallel");
            System.out.println("6 - Exit");

            String command = scanner.nextLine();
            switch(command){
                case "1":{
                    eventClient.eventManagement(scanner);
                    break;
                }
                case "2": {
                    customerClient.customerManagement(scanner);
                    break;
                }
                case "3": {
                    ticketClient.ticketManagement(scanner);
                    break;
                }
                case "4": {
                    performanceClient.stressTest();
                    break;
                }
                case "5": {
                    try{
                        performanceClientParallel.stressTest();
                    }
                    catch (Exception E) {
                        E.printStackTrace();
                    }
                    finally{
                        break;
                    }
                }

                case "6": {
                    System.exit(0);
                }
            }
        }
    }


}
