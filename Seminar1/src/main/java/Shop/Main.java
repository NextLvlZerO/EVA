package Shop;

import Shop.Client.Client;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication  // <-- Wichtig: aktiviert die Spring-Konfiguration
public class Main {

    public static void main(String[] args) {
        // Spring Boot Anwendung starten
        SpringApplication.run(Main.class, args);

        // Eigene Logik ausführen (Shop.Client starten)
        try {
            Client client = new Client();
            client.run();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
