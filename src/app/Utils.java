package app;

/**
 * Utility class with methods that are helpful in the context of the application
 * @author Filip Prochazka (jacktech24)
 */
public class Utils {

    /**
     *
     * @param array
     * @param o
     * @return
     */
    public static boolean arrayContains(int[] array, int o) {
        for (int o2 : array) {
            if (o2 == o) {
                return true;
            }
        }
        return false;
    }

}
