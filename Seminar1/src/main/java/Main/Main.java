package Main;

import Client.Client;

public class Main {

    public static void main(String[] args){
        try{
            Client eventClient = new Client();
            eventClient.run();
        }
        catch(Exception e){
           System.out.println("Error:" + e.getMessage());
        }
    }
}
