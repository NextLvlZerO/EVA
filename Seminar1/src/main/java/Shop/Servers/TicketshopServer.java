package Shop.Servers;

import Shop.Interfaces.LogServiceInterface;
import Shop.Interfaces.TicketShopInterface;
import Shop.Utility.TicketShop;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class TicketshopServer implements Runnable {

    private TicketShopInterface ticketShop;

    public TicketshopServer(TicketShop ticketshop){
        this.ticketShop = ticketshop;
    }

    /*public static void main(String[] args) {
        run(new TicketShop());
    }*/


    @Override
    public  void run() {
        TicketShop shop = new TicketShop();
        LogServiceInterface logger = shop.getLogService();
        ExecutorService executor = Executors.newFixedThreadPool(Runtime.getRuntime().availableProcessors());
        int port = 7654;

        try (ServerSocket serverSocket = new ServerSocket(port)) {
            System.out.println("Server started on port: " + port);
            logger.info("Server started on port: " + port);

            while (true) {
                try {
                    Socket clientSocket = serverSocket.accept();
                    executor.submit(() -> handleClient(clientSocket, shop));

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            executor.shutdown();
        }
    }


    private static Object handleClient(Socket clientSocket, TicketShop shop) {
        try (
                BufferedReader in = new BufferedReader(new InputStreamReader(clientSocket.getInputStream()));
                BufferedWriter out = new BufferedWriter(new OutputStreamWriter(clientSocket.getOutputStream()))) {

            System.out.println("Shop.Client connected: " + clientSocket.getInetAddress());

            String inputLine = in.readLine();
            System.out.println("Shop.Client Input: " + inputLine);

            try {
                shop.convertString(inputLine);
                out.write("OK\n");
            } catch (Exception e) {
                out.write("ERROR:500" + e.getMessage() + "\n");
            }

            out.flush();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
