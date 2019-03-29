/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Dials;

import java.awt.BorderLayout;
import java.awt.Dimension;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.BevelBorder;

/**
 *
 * @author as5373u
 */
public class Bar extends JPanel {

    private BarDrawPanel bar;
    private JLabel lblTitle;
    private JPanel space = new JPanel();

    public Bar() {
        setLayout(new BorderLayout());

        // set the style of the border
        setBorder(new BevelBorder(BevelBorder.LOWERED));

        // position the label above the dial
        lblTitle = new JLabel();
        lblTitle.setHorizontalAlignment(JLabel.CENTER);
        add(lblTitle, BorderLayout.NORTH);
        bar = new BarDrawPanel();
        add(bar, BorderLayout.CENTER);
        add(space, BorderLayout.WEST);
        space.setPreferredSize(new Dimension(40, 50));
    }

    public void setValue(int value) {
        bar.setValue(value);
    }

    /**
     *
     * @param label - label to appear above the dial
     */
    public void setLabel(String label) {
        lblTitle.setText(label);
    }

}
