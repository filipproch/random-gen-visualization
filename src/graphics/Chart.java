package graphics;

import com.googlecode.lanterna.TerminalPosition;
import com.googlecode.lanterna.TerminalSize;
import com.googlecode.lanterna.graphics.TextGraphics;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by jacktech24 on 30.10.15.
 */
public class Chart {

    private static final int MAX_COLLUMN_WIDTH = 10;
    private static final int MIN_COLLUMN_WIDTH = 1;

    private static final int COLLUMN_SPACE_WIDTH = 1;

    private final TerminalPosition start;
    private final TerminalPosition end;

    private ArrayList<Integer> bars = new ArrayList<>();
    private ArrayList<Float> cachedBars = new ArrayList<>();

    private boolean scroolable = false;
    private int barWidth;
    private int maxVal;
    private int barHeight;

    public Chart(List<Integer> series, TerminalPosition start, TerminalPosition end) {
        this.start = start;
        this.end = end;

        bars.addAll(series);

        calculate();
    }

    private void calculate() {
        int max = bars.get(0);
        for(int i = 0;i < bars.size();i++) {
            if(bars.get(i) > max) {
                max = bars.get(i);
            }
        }
        maxVal = max;

        TerminalSize drawingSpace =
                new TerminalSize(
                        Math.abs(start.getColumns()-end.getColumns()),
                        Math.abs(start.getRows()-end.getRows()));
        barWidth = (int) Math.floor(drawingSpace.getColumns()/(float)bars.size());
        barWidth = barWidth < MIN_COLLUMN_WIDTH ? MIN_COLLUMN_WIDTH : barWidth;

        barHeight = drawingSpace.getRows();

        float coeficient = barHeight/(float)maxVal;

        cachedBars.clear();
        for(int i = 0;i < bars.size();i++) {
            cachedBars.add(recalculateHeight(bars.get(i), coeficient));
        }
    }

    private Float recalculateHeight(Integer height, float coeficient) {
        return height * coeficient;
    }

    public void render(TextGraphics graphics) {
        graphics.drawRectangle(, end, '-');
        graphics.drawLine(0, 10, end.getColumns(), 10, '-');
        graphics.drawLine(0, 15, end.getColumns(), 15, '-');
    }

}
