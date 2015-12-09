package app;

import org.junit.*;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.PrintStream;

import static org.junit.Assert.*;

/**
 * @author Filip Prochazka (jacktech24)
 */
public class DataWorkerTest {

    private static Ppa1_SP_A15B0549P.DataWorker mDataWorker;

    @BeforeClass
    public static void oneTimeSetUp() {
        mDataWorker = new Ppa1_SP_A15B0549P.DataWorker();
    }

    @AfterClass
    public static void oneTimeTearDown() {
        mDataWorker.release();
    }

    @Test
    public void testPrintFormattedData() throws Exception {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        mDataWorker.printFormattedData(PseudoGenerator.generate(67));
        System.setOut(null);
        assertEquals("---Vysledky---\n3 [67, 45, 21]\n3 [21, 45, 67]\n\n", outContent.toString());
    }

    @Test
    public void testWriteFormattedData() throws Exception {
        mDataWorker.writeFormattedData(PseudoGenerator.generate(67));
        BufferedReader reader = new BufferedReader(new FileReader("vystup.txt"));
        StringBuilder builder = new StringBuilder();
        String line;
        while ((line = reader.readLine()) != null) {
            builder.append(line).append("\n");
        }
        assertEquals("3 [67, 45, 21]\n3 [21, 45, 67]\n\n", builder.toString());
    }
}