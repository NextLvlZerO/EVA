package Servers;

import java.io.*;
import java.net.Socket;

public class ClientServer {

    public static void main(String[] args) {
        String host = "localhost";
        int port = 7654;

        try (Socket socket = new Socket(host, port);
             BufferedWriter out = new BufferedWriter(new OutputStreamWriter(socket.getOutputStream()));
             BufferedReader in = new BufferedReader(new InputStreamReader(socket.getInputStream()))) {

            String message = "EVENT,CREATE,Foo,MoreFoo,2025-08-12T19:30,400";

            out.write(message + "\n");
            out.flush();

            String response = in.readLine();
            System.out.println("Response: " + response);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
