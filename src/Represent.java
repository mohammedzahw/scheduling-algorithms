import java.awt.Color;
import java.awt.FontMetrics;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.util.ArrayList;
import java.util.List;

import javax.swing.JPanel;

public class Represent extends JPanel {
    public int limit;
    public List<Integer> cord = new ArrayList<>();
    int marg = 50;

    protected void paintComponent(Graphics grf) {

        super.paintComponent(grf);
        Graphics2D graph = (Graphics2D) grf;
        graph.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);

        int width = getWidth();
        int height = getHeight();

        // Draw horizontal axis
        graph.draw(new Line2D.Double(marg, marg, width - marg, marg));

        // Draw vertical axis
        graph.draw(new Line2D.Double(marg, marg, marg, height - marg));

        double x = (double) (width - 2 * marg) / (limit);

        graph.setPaint(Color.darkGray);

        // Draw points and numbers on the horizontal axis
        FontMetrics metrics = graph.getFontMetrics();
        for (int i = 0; i <= limit; i++) {
            double x1 = marg + i * x;
            String number = String.valueOf(i); // Numbering starts at 0
            graph.drawString(number, (float) x1 - metrics.stringWidth(number) / 2, marg + metrics.getHeight() - 20);
        }

        double x0 = marg, y0 = marg;
        for (int i = 0; i < cord.size(); i++) {
            double y1 = marg + i * 20;
            double x1 = marg + cord.get(i) * x;
            graph.fill(new Ellipse2D.Double(x1 - 2, y1 - 2, 4, 4));
            graph.draw(new Line2D.Double(x0, y0, x1, y1)); // Draw a line between the two points
            x0 = x1;
            y0 = y1;
        }

    }

}
