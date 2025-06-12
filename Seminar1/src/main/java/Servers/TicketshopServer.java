package Servers;

import Interfaces.LogServiceInterface;
import Utility.TicketShop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;

public class TicketshopServer {

    public static void main(String[] args) {
        TicketShop shop = new TicketShop();
        LogServiceInterface logger = shop.getLogService();
        int port = 7654;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            logger.info("Server started on port: " + port);

            while (true) {
                try (Socket clientSocket = serverSocket.accept();
                    BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                    BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

                    System.out.println("Client connected: " + clientSocket.getInetAddress());

                    String inputLine = in.readLine();
                    System.out.println("Client Input: " + inputLine);

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
