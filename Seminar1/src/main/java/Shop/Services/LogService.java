package Shop.Services;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;


public class LogService implements Shop.Interfaces.LogServiceInterface {

    private final String logFilePath;
    private PrintWriter writer;

    public enum Level {
        INFO, WARN, ERROR
    }


    public LogService() {

        this.logFilePath = "Seminar1/src/main/java/Shop.Logs/logs.txt";
        File logFile = new File(this.logFilePath);



        try {
            writer = new PrintWriter(new FileWriter(logFile, true));
        } catch (IOException e) {
            System.err.println("Konnte Log-Datei nicht öffnen: " + e.getMessage());
            writer = null;
        }

    }

    @Override
    public void log(Level level, String message) {
        String timeStamp = LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
        String formattedMessage = String.format("[%s] [%s] %s;", timeStamp, level, message);


        // Optional in Datei schreiben
        if (writer != null) {
            writer.println(formattedMessage);
            writer.flush();
        } else {
            System.err.println("no writer");
        }
    }

    @Override
    public void info(String message) {
        log(Level.INFO, message);
    }

    @Override
    public void warn(String message) {
        log(Level.WARN, message);
    }

    @Override
    public void error(String message) {
        log(Level.ERROR, message);
    }

    @Override
    public void close() {
        if (writer != null) {
            writer.close();
        }
    }



}
