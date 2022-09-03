package framework;

import org.apache.log4j.Logger;

public class Log {
    static final Logger logger = Logger.getLogger(Log.class);

    public static void info(String message) {
        logger.info(message);
    }
}
