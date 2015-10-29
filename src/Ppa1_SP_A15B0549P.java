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

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main class constructor, call from main
     * @author Filip Procházka
     * @param args command line arguments
     */
    public Ppa1_SP_A15B0549P(String[] args) {
        PseudoGenerator gen = new PseudoGenerator();
        DataWorker worker = new DataWorker();

        if(args.length > 0) {
            //parameters entered
            for(String numString : args) {
                int num = Integer.parseInt(numString);
                gen.generate(num);
                worker.writeFormattedData(gen.getResults());
            }
        } else {
            if(scanner == null) {
                scanner = new Scanner(System.in);
            }
            String file = scanner.nextLine();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while((line = reader.readLine()) != null) {
                    int num = Integer.parseInt(line);
                    gen.generate(num);
                    worker.printFormattedData(gen.getResults());
                }
                reader.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        worker.close();
    }

    public static void main(String[] args) {
        new Ppa1_SP_A15B0549P(args);
    }

    private static class DataWorker {

        private FileWriter mWriter;
        private UltimateSorter mSorter = new UltimateSorter();
        private boolean mPrintedResultsString = false;
        private boolean mRecreatedFile = false;

        public DataWorker() {}

        /**
         * Prints out given data in required format
         * <pre>
         *     ${data.size()} [data.get(0), ..., data.get(data.size()-1)] //unsorted
         *     ${data.size()} [...] //sorted data
         * </pre>
         * @author Filip Procházka
         * @param data the list of data to print
         */
        public void printFormattedData(ArrayList<Integer> data) {
            if(!mPrintedResultsString) {
                System.out.println("---Vysledky---");
                mPrintedResultsString = true;
            }
            System.out.print(formatData(data));
        }

        /**
         * Writes given data to file in required format
         * @author Filip Procházka
         * @param data the list of data to write to file
         */
        public void writeFormattedData(ArrayList<Integer> data) {
            try {
                if(mWriter == null) {
                    mWriter = new FileWriter("vystup.txt", mRecreatedFile);
                }
                mWriter.append(formatData(data));
                if(!mRecreatedFile) {
                    mRecreatedFile = true;
                }
                mWriter.flush();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        private String formatData(ArrayList<Integer> data) {
            StringBuilder sb = new StringBuilder();
            sb.append(data.size()).append(" ").append(data.toString()).append("\n");
            sb.append(data.size()).append(" ").append(Arrays.toString(mSorter.sortList(data))).append("\n");
            sb.append("\n");
            return sb.toString();
        }

        public void close() {
            if(mWriter != null) {
                try {
                    mWriter.flush();
                    mWriter.close();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        }

    }

    private static class UltimateSorter {

        private int[] mWorkArray;

        public UltimateSorter() {}

        /**
         *
         * @author Filip Procházka
         * @param unsortedList the list of data to sort
         * @return sorted list ${unsortedList}, transformed to array
         */
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

    private static class TerminalDrawer {

        //https://github.com/jline/jline2/blob/master/src/test/java/jline/example/Example.java

        public TerminalDrawer(){}

        public void test() {

        }

    }

}