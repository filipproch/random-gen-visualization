package graphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;
import com.googlecode.lanterna.screen.TerminalScreen;
import com.googlecode.lanterna.terminal.DefaultTerminalFactory;
import com.googlecode.lanterna.terminal.Terminal;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacktech24 on 30.10.15.
 */
//https://github.com/jline/jline2/blob/master/src/test/java/jline/example/Example.java
public class TerminalDrawer {

    private int page = 0;

    private ArrayList<Chart> charts = new ArrayList<>();

    private Terminal terminal;
    private TerminalScreen screen;
    private TextGraphics graphics;

    private boolean started = false;

    private TerminalSize graphStart;
    private TerminalSize graphEnd;

    public TerminalDrawer(){
        initTerminal();
    }

    private void initTerminal() {
        try {
            terminal = new DefaultTerminalFactory().createTerminal();
            screen = new TerminalScreen(terminal);
            graphics = screen.newTextGraphics();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void start() throws IOException {
        if(!started) {
            screen.startScreen();
            screen.clear();

            graphStart = new TerminalSize(0, 0);
            graphEnd = new TerminalSize(terminal.getTerminalSize().getColumns(), terminal.getTerminalSize().getRows()-2);

            started = true;
        } else {
            throw new RuntimeException("You have already started Terminal screen");
        }
    }

    public void init(List<List<Integer>> listSeries) {
        if(!started) {
            throw new RuntimeException("You must start Terminal screen first");
        }

        for(List<Integer> serie: listSeries) {
            charts.add(new Chart(serie, graphStart, graphEnd));
        }
    }

    public void stop() throws IOException {
        screen.stopScreen();
    }

    public void addChart(Chart chart) {
        charts.add(chart);
    }

    /*public void test() {
        try {
            graphics.drawRectangle(
                    new TerminalPosition(3, 3), new TerminalSize(10, 10), '█');
            screen.refresh();

            screen.readInput();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }*/

}