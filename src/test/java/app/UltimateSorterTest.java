package app;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import static org.junit.Assert.*;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class UltimateSorterTest {

    private int[] mExpectedArray = new int[] {
            10,11,12,13,15,17,23,24,29,30,34,48,53,54,58,69,73,83,85,91
    };

    @Test
    public void testSortList() throws Exception {
        ArrayList<Integer> listToSort = PseudoGenerator.generate(10);
        assertArrayEquals("testing Bubble sort", mExpectedArray,
                UltimateSorter.sortList(listToSort, UltimateSorter.SortType.BUBBLE));
        assertArrayEquals("testing Insert sort", mExpectedArray,
                UltimateSorter.sortList(listToSort, UltimateSorter.SortType.INSERT));
        assertArrayEquals("testing Select sort", mExpectedArray,
                UltimateSorter.sortList(listToSort, UltimateSorter.SortType.SELECT));
    }

    @Test
    public void testSortArray() throws Exception {
        ArrayList<Integer> listToSort = PseudoGenerator.generate(10);
        int[] array = new int[listToSort.size()];
        for(int i = 0;i<array.length;i++) {
            array[i] = listToSort.get(i);
        }
        assertArrayEquals("testing Bubble sort", mExpectedArray,
                UltimateSorter.sortArray(array, UltimateSorter.SortType.BUBBLE));
        assertArrayEquals("testing Insert sort", mExpectedArray,
                UltimateSorter.sortArray(array, UltimateSorter.SortType.INSERT));
        assertArrayEquals("testing Select sort", mExpectedArray,
                UltimateSorter.sortArray(array, UltimateSorter.SortType.SELECT));
    }

    @Test
    public void testListSorter() throws Exception {

    }

    @Test
    public void testArraySorter() throws Exception {

    }

    @Test
    public void testListToArray() throws Exception {

    }
}