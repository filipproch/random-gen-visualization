package app;

import java.util.logging.Logger;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class Log {

    private static Logger LOGGER = Logger.getLogger("A15B0549P");

    public static void info(String msg) {
        LOGGER.info(msg);
    }

    public static void warn(String msg) {
        LOGGER.warning(msg);
    }

}
