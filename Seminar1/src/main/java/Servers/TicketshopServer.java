package Servers;

import Utility.TicketShop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketshopServer {


    public static void main(String[] args) {
        TicketShop shop = new TicketShop();
        int port = 7654;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("TicketShop Server gestartet auf Port " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    System.out.println("Client verbunden: " + clientSocket.getInetAddress());

                    String inputLine = in.readLine();
                    System.out.println("Empfangen: " + inputLine);

                    try{
                        shop.convertString(inputLine);
                        out.write("OK\n");
                    }
                    catch(Exception e){
                        out.write("ERROR:500" + e.getMessage() + "\n");
                    }

                    out.flush();
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }



}
