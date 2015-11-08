package app;

import java.util.logging.Logger;

/**
 * Help class for logging to console
 *
 * @author Filip Prochazka (jacktech24)
 */
public class Log {

    private static Logger LOGGER = Logger.getLogger("A15B0549P");

    /**
     *
     * @param msg
     */
    public static void info(String msg) {
        LOGGER.info(msg);
    }

    /**
     *
     * @param msg
     */
    public static void warn(String msg) {
        LOGGER.warning(msg);
    }

}
