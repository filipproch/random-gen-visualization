package app;

import java.util.List;

/**
 * A help class for sorting series of numbers (integers), all sorts are written in a way that going step by step is possible.
 * @author Filip Prochazka (jacktech24)
 */
public class UltimateSorter {

    public UltimateSorter() {}

    /**
     *
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

    /**
     * Bubble Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Bubble_sort">Wikipedia</a>
     */
    public static class BubbleSort extends SortingAlgorithm {

        int i = 0;
        boolean pass = true;

        @Override
        public boolean step() {
            if(!super.step()) {
                return false;
            }
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
            return true;
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, i+1};
        }

    }

    /**
     * Selection Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Selection_sort">Wikipedia</a>
     */
    public static class SelectSort extends SortingAlgorithm {

        int i = 0, j = 1, target = 0;

        @Override
        public boolean step() {
            if(!super.step()) {
                return false;
            }
            if (array[target] > array[j]) {
                target = j;
            }
            j++;
            if (j >= array.length) {
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
            return true;
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {i, j, target};
        }

    }

    /**
     * Insertion Sort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Insertion_sort">Wikipedia</a>
     */
    public static class InsertSort extends SortingAlgorithm {

        private boolean moving = false;
        private boolean main = true;
        private int r = 0, j = 1, s = -1, num = -1;

        @Override
        public boolean step() {
            if(!super.step()) {
                return false;
            }
            if(!main) {
                if(moving) {
                    if(s >= r+1) {
                        array[s+1] = array[s];
                        s--;
                    } else {
                        moving = false;
                        main = true;
                        array[r+1] = num;
                        j++;
                    }
                } else {
                    if (r >= 0 && array[r] > array[j]) {
                        r--;
                    } else {
                        s = j-1;
                        moving = true;
                    }
                }
            } else {
                if(array[j-1] > array[j]) {
                    r = j-1;
                    num = array[j];
                    main = false;
                } else {
                    j++;
                }
            }
            if(j >= array.length) {
                sorted = true;
            }
            return true;
        }

        @Override
        public int[] getHighlighted() {
            return new int[] {r, j, s};
        }

    }

    /**
     * Heapsort implementation, definition on
     * <a href="https://en.wikipedia.org/wiki/Heapsort">Wikipedia</a>
     */
    public static class Heapsort extends SortingAlgorithm {

        @Override
        public boolean step() {
            //todo
            return true;
        }

        @Override
        public int[] getHighlighted() {
            return new int[0];
        }

    }

    public enum SortType {
        SELECT(SelectSort.class), BUBBLE(BubbleSort.class), INSERT(InsertSort.class);

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

        public boolean step() {
            if (array.length <= 1) {
                sorted = true;
                return false;
            }
            return !sorted;
        }
        public abstract int[] getHighlighted();

        public void passArray(int[] array) {
            this.array = array;
        }

        public int[] getArray() {
            return array;
        }
    }

}

