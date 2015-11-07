package app;

import java.util.List;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class UltimateSorter {

    public UltimateSorter() {}

    /**
     *
     * @author Filip Prochazka
     * @param unsortedList the list of data to sort
     * @return sorted list ${unsortedList}, transformed to array
     */
    public static int[] sortList(List<Integer> unsortedList, SortType sortType) {
        return sortArray(listToArray(unsortedList), sortType);
    }

    public static int[] sortArray(int[] unsortedArray, SortType sortType) {
        SortingAlgorithm algorithm = sortType.get();
        algorithm.passArray(unsortedArray);
        do {
            algorithm.step();
        } while (!algorithm.sorted);
        return algorithm.getArray();
    }

    public static SortingAlgorithm listSorter(List<Integer> unsortedList, SortType sortType) {
        return arraySorter(listToArray(unsortedList), sortType);
    }

    public static SortingAlgorithm arraySorter(int[] unsortedArray, SortType sortType) {
        SortingAlgorithm algorithm = sortType.get();
        algorithm.passArray(unsortedArray);
        return algorithm;
    }

    public static int[] listToArray(List<Integer> list) {
        int[] mWorkArray = new int[list.size()];
        for(int i = 0;i < mWorkArray.length;i++) {
            mWorkArray[i] = list.get(i);
        }
        return mWorkArray;
    }

    public static class BubbleSort extends SortingAlgorithm {

        int i = 0;
        boolean pass = true;

        @Override
        public void step() {
            if(array[i] > array[i+1]) {
                int p = array[i];
                array[i] = array[i+1];
                array[i+1] = p;
                pass = false;
            }
            if(++i >= array.length-1) {
                sorted = pass;
                pass = true;
                i = 0;
            }
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, i+1};
        }

    }

    public static class SelectSort extends SortingAlgorithm {

        int i = 0, j = 1, target = 0;

        @Override
        public void step() {
            if (array[target] > array[j]) {
                target = j;
            }
            j++;
            if (j >= array.length-1) {
                int p = array[i];
                array[i] = array[target];
                array[target] = p;
                i++;
                target = i;
                j = i+1;

                if(i == array.length-1) {
                    sorted = true;
                }
            }
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, j, target};
        }

    }

    public static class InsertSort extends SortingAlgorithm {

        @Override
        public void step() {

        }

        @Override
        public int[] getHighlighted() {
            return new int[0];
        }

    }

    public static class QuickSort extends SortingAlgorithm {

        @Override
        public void step() {

        }

        @Override
        public int[] getHighlighted() {
            return new int[0];
        }

    }

    public enum SortType {
        SELECT(SelectSort.class), BUBBLE(BubbleSort.class),
        INSERT(InsertSort.class), QUICK(QuickSort.class);

        private Class<? extends SortingAlgorithm> sortClass;

        SortType(Class<? extends SortingAlgorithm> sortClass) {
            this.sortClass = sortClass;
        }

        public SortingAlgorithm get() {
            try {
                return sortClass.newInstance();
            } catch (InstantiationException | IllegalAccessException e) {
                throw new RuntimeException();
            }
        }
    }

    public static abstract class SortingAlgorithm {

        protected int[] array;
        protected boolean sorted = false;

        public SortingAlgorithm(){}

        public abstract void step();
        public abstract int[] getHighlighted();

        public boolean sorted() {
            return sorted;
        }

        public void passArray(int[] array) {
            this.array = array;
        }

        public int[] getArray() {
            return array;
        }
    }

}

