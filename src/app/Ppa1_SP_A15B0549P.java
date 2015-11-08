package app;

import graphics.TerminalDrawer;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

/**
 * @author Filip Prochazka (fprochaz)
 */
public class Ppa1_SP_A15B0549P {

    private static Scanner scanner = new Scanner(System.in);

    /**
     * Main class constructor, call from main
     * @author Filip Prochazka
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

            //parameters entered
            for(String numString : args) {
                int num = Integer.parseInt(numString);
                ArrayList<Integer> integers = PseudoGenerator.generate(num);
                worker.writeFormattedData(integers);
                seriesList.add(integers);
            }

            drawer.init(seriesList);
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
         * @author Filip Prochazka
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
         * @author Filip Prochazka
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
            }
        }

    }

}