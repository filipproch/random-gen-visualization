package app;

import graphics.TerminalDrawer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.*;

/**
 * Main class, the entry point
 * @author Filip Prochazka (fprochaz)
 */
public class Ppa1_SP_A15B0549P {

    private static final Scanner scanner = new Scanner(System.in);
    private static final Random random = new Random();

    /**
     * Main class constructor, called from main
     * @param args command line arguments
     */
    public Ppa1_SP_A15B0549P(String[] args) {
        DataWorker worker = new DataWorker();

        if(args.length > 0) {
            TerminalDrawer drawer = new TerminalDrawer();
            try {
                drawer.start();
            } catch (IOException e) {
                e.printStackTrace();
            }
            List<List<Integer>> seriesList = new ArrayList<>();

            switch (args[0]) {
                case "random":
                    for(int i = 0;i < random.nextInt(20)+1;i++) {
                        seriesList.add(PseudoGenerator.generate(random.nextInt(80) + 10));
                    }
                    break;
                case "all":
                    for (int i = 10; i <= 99; i++) {
                        seriesList.add(PseudoGenerator.generate(i));
                    }
                    break;
                default:
                    //parameters entered
                    for (String numString : args) {
                        int num = Integer.parseInt(numString);
                        ArrayList<Integer> integers = PseudoGenerator.generate(num);
                        worker.writeFormattedData(integers);
                        seriesList.add(integers);
                    }
                    break;
            }

            drawer.init(seriesList);
        } else {
            String file = scanner.nextLine();
            try {
                BufferedReader reader = new BufferedReader(new FileReader(file));
                String line;
                while((line = reader.readLine()) != null) {
                    int num = Integer.parseInt(line);
                    ArrayList<Integer> integers = PseudoGenerator.generate(num);
                    worker.printFormattedData(integers);
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

    /**
     * A help class used to format and print/store series of data
     */
    public static class DataWorker {

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

        /**
         * Formats the given list of integers in a specific way
         * @param data the list of data to format
         * @return list of data formatted into a {@link String}
         */
        private String formatData(ArrayList<Integer> data) {
            StringBuilder sb = new StringBuilder();
            sb.append(data.size()).append(" ").append(data.toString()).append("\n");
            sb.append(data.size()).append(" ").append(Arrays.toString(mSorter.sortList(data, UltimateSorter.SortType.SELECT))).append("\n");
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
                mWriter = null;
            }
        }

        public void release() {
            close();
            mSorter = null;
        }
    }

}