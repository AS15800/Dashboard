/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dials;

import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.GradientPaint;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Line2D;
import java.awt.geom.Rectangle2D;
import javax.swing.JPanel;

/**
 *
 * @author as5373u
 */
public class BarDrawPanel extends JPanel {

    private int value;

    private int barWidth;
    private int barHeight;

    private int padding;
    private float maxValue;

    public BarDrawPanel() {
        this(200, 20, 8, 100, 0);
    }

    public BarDrawPanel(int width, int height, int padding, int maxValue, int value) {
        setPreferredSize(new Dimension(width + (2), height + (2)));

        this.barWidth = width;
        this.barHeight = height;
        this.maxValue = maxValue;
        this.padding = padding;
        this.value = value;
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
                RenderingHints.VALUE_ANTIALIAS_ON);

        Rectangle2D barx = new Rectangle2D.Double(padding, padding, barWidth, barHeight);
        GradientPaint redtoyellow = new GradientPaint(0 + (float) barx.getWidth() * 0.1F, 0, Color.RED, (float) barx.getWidth() * 0.3F, 0, Color.YELLOW);
        g2.setPaint(redtoyellow);
        g2.fill(barx);

        // draw the value indicator to show the current value
        g2.setStroke(new BasicStroke(barWidth / 40, BasicStroke.CAP_SQUARE, 0));
        g2.setPaint(Color.GRAY);
        Line2D valueIndicator = new Line2D.Double(padding + (barWidth * value / maxValue), padding / 2F, padding + (barWidth * value / maxValue), barHeight + (padding * 1.5F));
        g2.draw(valueIndicator);
    }

    public void setValue(int value) {
        // don't let the value go over the maximum for the bar.  But what about the minimum?
        this.value = value > maxValue ? (int) maxValue : value;
        repaint(); // causes paintComponent() to be called
    }
}
