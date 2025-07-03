package Shop.Interfaces;

import Shop.Services.LogService;

public interface LogServiceInterface {
    void log(LogService.Level level, String message);

    void info(String message);

    void warn(String message);

    void error(String message);

    void close();
}
