package Main;

import Client.Client;
import Utility.TicketShop;

public class Main {

    public static void main(String[] args){
        try{
            Client client = new Client();
            client.run();
        }
        catch(Exception e){
            e.printStackTrace();
        }
    }
}
