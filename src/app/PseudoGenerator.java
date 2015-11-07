package app;

import java.util.ArrayList;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class PseudoGenerator {

    private ArrayList<Integer> pseudoNumbers;
    private int startNumber;

    public PseudoGenerator(){}

    public void generate(int startNumber) {
        pseudoNumbers = new ArrayList<>();
        this.startNumber = startNumber;
        runGenerator();
    }

    private void runGenerator() {
        int lastNumber = startNumber;
        do {
            pseudoNumbers.add(lastNumber);
            int power = (int) Math.pow(lastNumber, 2);
            lastNumber = extractTwoDigits(power)+1;
        } while(!pseudoNumbers.contains(lastNumber));
    }

    private int extractTwoDigits(int number) {
        return Integer.parseInt(String.valueOf(number).substring(0, 2));
    }

    public ArrayList<Integer> getResults() {
        return pseudoNumbers;
    }

}
