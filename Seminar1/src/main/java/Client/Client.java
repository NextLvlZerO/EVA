package Client;

import java.util.Scanner;

public class Client {
    private EventClient eventClient;
    private CustomerClient customerClient;

    public Client() {
        this.eventClient = new EventClient();
        this.customerClient = new CustomerClient();
    }

    public void run(){
        Scanner scanner = new Scanner(System.in);
        outer: while(true){

            System.out.println("-----------------");
            System.out.println("Management System");
            System.out.println("-----------------");
            System.out.println("1 - Event service");
            System.out.println("2 - Customer service");
            System.out.println("3 - Exit");

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
                    System.exit(0);
                }
            }
        }
    }


}
