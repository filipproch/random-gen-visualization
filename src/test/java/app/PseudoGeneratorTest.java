package app;

import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.Assert.*;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class PseudoGeneratorTest {

    @Test
    public void testGenerate() throws Exception {
        ArrayList<Integer> generate = PseudoGenerator.generate(67);
        Integer[] array = generate.toArray(new Integer[generate.size()]);
        assertArrayEquals("testing generator 67 ("+ Arrays.toString(array)+")", new Integer[]{67,45,21}, array);
        generate = PseudoGenerator.generate(10);
        array = generate.toArray(new Integer[generate.size()]);
        assertArrayEquals("testing generator 10 ("+ Arrays.toString(array)+")", new Integer[]{10,11,13,17,29,85,73,54,30,91,83,69,48,24,58,34,12,15,23,53}, array);
    }

}