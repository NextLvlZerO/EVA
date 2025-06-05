package Services;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

public class LogService {

    public enum Type {
        A("Type A"),
        B("Type B"),
        C("Type C");

        private final String label;

        Type(String label) {
            this.label = label;
        }

        public String getLabel() {
            return label;
        }
    }

    private final IDService idService;
    public List<String> logs;

    public LogService() {
        this.idService = new IDService();
        this.logs = new ArrayList<>();
    }

    public String addLog(long eventId, String name) {
        LocalDateTime time = LocalDateTime.now();
        long currentId = idService.addId();
         String resultLog = time + "|" + eventId + "|" + name;
         logs.add(resultLog);
         return resultLog;
    }

}
