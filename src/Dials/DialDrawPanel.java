/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dials;

import java.awt.BasicStroke;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.geom.Ellipse2D;
import java.awt.geom.Line2D;
import java.awt.geom.Point2D;
import javax.swing.JPanel;

/**
 *
 * @author as5373u
 */
class DialDrawPanel extends JPanel {

    private int value;

    private int radius;
    private double handLength;
    private int dialMaxValue;

    public static final float Dial_Max_Degrees = 180;

    public static final float Dial_Start_Degrees = 0;

    public DialDrawPanel() {
        this(100, 100, 10);
    }

    public DialDrawPanel(int radius, int dialMaxValue, int value) {
        this.setPreferredSize(new Dimension(2 * (radius + 20), 2 * (radius)));
        this.radius = radius;
        this.dialMaxValue = dialMaxValue;
        this.value = value;
        handLength = 1 * radius;

    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintChildren(g);
        Graphics2D g2 = (Graphics2D) g;
        Graphics2D g3 = (Graphics2D) g;
        g2.setRenderingHint(RenderingHints.KEY_ANTIALIASING, RenderingHints.VALUE_ANTIALIAS_ON);
        g2.setStroke(new BasicStroke(3, BasicStroke.CAP_ROUND, 0));

        // draw centre of the dial as a small circle of fixed size
        Ellipse2D circle = new Ellipse2D.Double((radius) - 5, (radius) - 5, 10, 10);
        g2.fill(circle);

        dialLines(g2, Math.toRadians(Dial_Max_Degrees));
        dialLines(g2, Math.toRadians(Dial_Max_Degrees + Dial_Start_Degrees));

        double angle = Math.toRadians(180 - ((value + (value * 0.1)) * (Dial_Max_Degrees / dialMaxValue)));
        drawHand(g2, angle, handLength);

        dialValue(g3, Math.toRadians(-20));
        dialValue(g3, Math.toRadians(00));
        dialValue(g3, Math.toRadians(20));
        dialValue(g3, Math.toRadians(40));
        dialValue(g3, Math.toRadians(60));
        dialValue(g3, Math.toRadians(80));
        dialValue(g3, Math.toRadians(100));
        dialValue(g3, Math.toRadians(120));
        dialValue(g3, Math.toRadians(140));
        dialValue(g3, Math.toRadians(160));
    }

    public void dialLines(Graphics2D g2, double angle) {
        Point2D outerEnd = new Point2D.Double((radius) + radius * Math.cos(angle),
                (radius) - radius * Math.sin(angle));
        // calculate endpoint of line closest to centre of dial
        Point2D innerEnd = new Point2D.Double((radius) + ((radius) * .8) * Math.cos(angle),
                (radius) - ((radius) * .8) * Math.sin(angle));
        // draw the line
        g2.draw(new Line2D.Double(outerEnd, innerEnd));
    }

    public void drawHand(Graphics2D g2, double angle, double handLength) {
        Point2D end = new Point2D.Double((radius) + handLength * Math.cos(angle),
                (radius) - handLength * Math.sin(angle));
        // calculate the centre 
        Point2D center = new Point2D.Double(radius, radius);
        //     Draw the line
        g2.draw(new Line2D.Double(center, end));
    }

    public void setValue(int value) {
        // don't let the value go over the maximum for the dial.  But what about the minimum?
        this.value = value > dialMaxValue ? dialMaxValue : value;
        repaint(); // causes paintComponent() to be called
    }

    public void dialValue(Graphics2D g3, double value) {
        Point2D outerEnd = new Point2D.Double((radius) + radius * Math.cos(value),
                (radius) - radius * Math.sin(value));
        // calculate endpoint of line closest to centre of dial
        Point2D innerEnd = new Point2D.Double((radius) + ((radius) * .8) * Math.cos(value),
                (radius) - ((radius) * .8) * Math.sin(value));
        // draw the line
        g3.draw(new Line2D.Double(outerEnd, innerEnd));
        g3.drawString("0", 30, 105);
        g3.drawString("10", 30, 80);
        g3.drawString("20", 40, 60);
        g3.drawString("30", 60, 45);
        g3.drawString("40", 82, 35);
        g3.drawString("50", 102, 35);
        g3.drawString("60", 130, 45);
        g3.drawString("70", 150, 60);
        g3.drawString("80", 160, 80);
        g3.drawString("90", 165, 105);
        g3.drawString("100", 152, 130);

    }
}
