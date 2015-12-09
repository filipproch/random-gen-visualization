package app;

import java.util.ArrayList;

/**
 * Generator of series of pseudo-random numbers, requires a starting number
 *
 * @author Filip Prochazka (jacktech24)
 */
public class PseudoGenerator {

    private PseudoGenerator() {
    }

    /**
     * Generates series of pseudo-random numbers
     *
     * @param startNumber A number required as seed to start generation from
     */
    public static ArrayList<Integer> generate(int startNumber) {
        ArrayList<Integer> pseudoNumbers = new ArrayList<>();
        runGenerator(startNumber, pseudoNumbers);
        return pseudoNumbers;
    }

    private static void runGenerator(int startNumber, ArrayList<Integer> pseudoNumbers) {
        int lastNumber = startNumber;
        do {
            pseudoNumbers.add(lastNumber);
            int power = (int) Math.pow(lastNumber, 2);
            lastNumber = extractTwoDigits(power) + 1;
        } while (!pseudoNumbers.contains(lastNumber));
    }

    private static int extractTwoDigits(int number) {
        if (number > 9) {
            return Integer.parseInt(String.valueOf(number).substring(0, 2));
        } else {
            throw new IllegalArgumentException("number must be > 9 (is " + number + ")");
        }
    }

}
