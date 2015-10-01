package cz.jacktech.ppa1.seminar;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Created by fprochaz on 24.9.2015.
 * @author Filip Procházka (fprochaz)
 */
public class Ppa1_SP_A15B0549P {

    public Ppa1_SP_A15B0549P(String[] args) {
        PseudoGenerator gen = new PseudoGenerator();
        DataWorker worker = new DataWorker();

        if(args.length > 0) {
            //parameters entered
            for(String numString : args) {
                int num = Integer.parseInt(numString);
                gen.generate(num);
                worker.printFormattedData(gen.getResults());
            }
        } else {

        }
    }

    public static void main(String[] args) {
        new Ppa1_SP_A15B0549P(args);
    }

    private static class DataWorker {

        private FileWriter mWriter;
        private UltimateSorter mSorter = new UltimateSorter();
        private Scanner mScanner;

        public DataWorker() {}

        public ArrayList<Integer> readData() {
            if(mScanner == null) {
                mScanner = new Scanner(System.in);
            }
            String file = mScanner.nextLine();
            ArrayList<Integer>
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));

            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        public void printFormattedData(ArrayList<Integer> data) {
            System.out.print(formatData(data));
        }

        public void writeFormattedData(ArrayList<Integer> data) {
            try {
                if(mWriter == null) {
                    mWriter = new FileWriter("vystup.txt", false);
                }
                mWriter.append(formatData(data));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String formatData(ArrayList<Integer> data) {
            StringBuilder sb = new StringBuilder();
            sb.append(data.size()).append(" ").append(data.toString());
            sb.append(data.size()).append(" ").append(Arrays.toString(mSorter.sortList(data)));
            sb.append("\n");
            return sb.toString();
        }

    }

    private static class UltimateSorter {

        private int[] mWorkArray;

        public UltimateSorter() {}

        public int[] sortList(List<Integer> unsortedList) {
            mWorkArray = new int[unsortedList.size()];
            for(int i = 0;i < mWorkArray.length;i++) {
                mWorkArray[i] = unsortedList.get(i);
            }
            sortSelectSort();
            return mWorkArray;
        }

        private void sortSelectSort() {
            for(int i = 0;i < mWorkArray.length;i++) {
                for(int j = i+1;j < mWorkArray.length;j++) {
                    if(mWorkArray[i] > mWorkArray[j]) {
                        int cache = mWorkArray[i];
                        mWorkArray[i] = mWorkArray[j];
                        mWorkArray[j] = cache;
                    }
                }
            }
        }

    }

    private static class PseudoGenerator {

        private ArrayList<Integer> pseudoNumbers;
        private int startNumber;

        public PseudoGenerator(){}

        public void generate(int startNumber) {
            pseudoNumbers = new ArrayList<>();
            this.startNumber = startNumber;
            runGenerator();
        }

        private void runGenerator() {
            int lastNumber = -1;
            do {
                int power;
                if(lastNumber == -1) {
                    power = (int) Math.pow(startNumber, 2);
                } else {
                    power = (int) Math.pow(lastNumber, 2);
                }
                lastNumber = extractTwoDigits(power);
            } while(!pseudoNumbers.contains(lastNumber));
        }

        private int extractTwoDigits(int number) {
            return Integer.parseInt(String.valueOf(number).substring(0, 2));
        }

        public ArrayList<Integer> getResults() {
            return pseudoNumbers;
        }

    }

}