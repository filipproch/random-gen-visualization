package app;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class Utils {

    public static boolean arrayContains(int[] array, int o) {
        for (int o2 : array) {
            if (o2 == o) {
                return true;
            }
        }
        return false;
    }

}
