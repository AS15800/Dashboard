/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package setup;

import Time.display;
import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics2D;
import java.awt.image.BufferStrategy;
import javax.swing.JFrame;

/**
 *
 * @author as5373u
 */
public class setUp implements Runnable{
    
    private String title;
    private int size;
    private display display;    
    private Thread thread;
    
    private BufferStrategy buffer;
    private Graphics2D g;
    
    public setUp(String title, int size){
        this.title = title;
        this.size = size;
    }
    
    public void init(){
        display = new display(title, size);
        
    }
    
    public void draw(){
        buffer = display.canvas.getBufferStrategy();
        if(buffer == null) {
            display.canvas.createBufferStrategy(3);
            return;
        }
        int center = size/2;
        g =(Graphics2D) buffer.getDrawGraphics();
        g.clearRect(0,0, size, size);
        
        //draw
        
        g.setColor(Color.black);
        g.fillOval(10, 10, size-20, size - 20);
        g.setColor(Color.white);
        g.fillOval(20, 20, size-40, size - 40);
        
        //Draw numbers
        int angleX, angleY;
        int radius;
        double position;
        
        double time = System.currentTimeMillis();
               
        for(int i = 1; i<=12; i++){
            position = i / 12.0 * Math.PI *2;
            radius = center - 50;
            
            angleX = (int)((center) + (Math.sin(position) * radius));
            angleY = (int)((center) - (Math.cos(position) * radius));
            g.setColor(Color.BLACK);
            g.setFont(new Font("arial", Font.BOLD, 20));
            String a = Integer.toString(i);
            g.drawString(a,angleX,angleY);
        }
        
        
        for(int i = 1; i<= 60; i++){
            position = i / 60.0 * Math.PI*2;
            radius = center -20;
            angleX = (int)((center) + (Math.sin(position) * radius));
            angleY = (int)((center) - (Math.cos(position) * radius));
            radius = center - 40;
            if(i == 15|| i == 30 || i == 45 || i== 60){
                radius = center - 50;
            }else {
                radius = center -30;
            }
            int angleW = (int)((center) + (Math.sin(position) * radius));
            int angleZ = (int)((center) - (Math.cos(position) * radius));
            
            g.drawLine(angleW, angleZ,angleX, angleY);
        }
        
        //Hour hand
        radius = center - 120;
        
        time = System.currentTimeMillis()/(60.0 * 12*60*1000.0)* Math.PI*2;
        angleX = (int)((center) + (Math.sin(time)* radius));
        angleY = (int)((center) - (Math.cos(time)* radius));
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(10));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));
        
        //Minute hand
        radius = center - 90;
        
        time = System.currentTimeMillis()/(60.0 * 60*1000.0)* Math.PI*2;
        angleX = (int)((center) + (Math.sin(time)* radius));
        angleY = (int)((center) - (Math.cos(time)* radius));
        g.setColor(Color.black);
        g.setStroke(new BasicStroke(5));
        g.drawLine(center, center, angleX, angleY);
        g.setStroke(new BasicStroke(0));
        
        //Second hand
        radius = center - 40;
        
        time = System.currentTimeMillis()/(60.0 * 1000.0)* Math.PI*2;
        angleX = (int)((center) + (Math.sin(time)* radius));
        angleY = (int)((center) - (Math.cos(time)* radius));
        g.setColor(Color.red);
        g.drawLine(center, center, angleX, angleY);
        
        //Draw center
        g.setColor(Color.red);
        g.fillOval(center - 10, center - 10, 20, 20);
        
        //end
        
       buffer.show();
       g.dispose();
    }
    
    public synchronized void start(){
        thread = new Thread(this);
        
        thread.start();
    }
    
    public synchronized void stop(){
        try{
            thread.join();
        } catch (InterruptedException ex){
            System.out.println("Error: " + ex);
        }  
    }
    
    public void run(){
        init();
        
        while (true){
            draw();
        }
    }
    
}
