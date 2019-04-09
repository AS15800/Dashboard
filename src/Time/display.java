/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Time;

import java.awt.Canvas;
import java.awt.Color;
import java.awt.Dimension;
import javax.swing.JFrame;

/**
 *
 * @author as5373u
 */
public class display {
    
    
    private String title;
    private int size;
    
    private JFrame frame;
    
    public static Canvas canvas;
    
    public display(String title, int size){
        this.title = title;
        this.size = size;
        
        createDisplay();
    }
    
    public void createDisplay(){
        
        frame = new JFrame(title);
        frame.setSize(size,size);
        frame.setResizable(false);
        frame.setVisible(true);
        frame.setLocation(0, 500);
        
        canvas = new Canvas();
        canvas.setPreferredSize(new Dimension(size, size));
        canvas.setBackground(Color.lightGray);
        
        
        frame.add(canvas);
        
        frame.pack();
    }
        
}
