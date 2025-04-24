package org.example;
import java.time.LocalDateTime;
import java.util.Scanner;

public class Main {

    public static void main(String[] args){

        try{
            EventClient eventClient = new EventClient();
            eventClient.run();
        }
        catch(Exception e){
           System.out.println("Error:" + e.getMessage());
        }
    }
}
