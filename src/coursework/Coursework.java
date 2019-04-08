/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.Color;
import javax.swing.BoxLayout;

import Dials.Dials;
import Dials.Bar;
import Time.TimeKeeping;
import java.awt.GridLayout;
import setup.setUp;

/**
 *
 * @author as5373u
 */

/*
This class is the View.
It only contains the UI and all the calculation are on another class.

*/
public class Coursework{

    private final TimeKeeping time = new TimeKeeping();
    private final Speed model = new Speed();
    EngineStatus myEngine = new EngineStatus();
    

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Coursework cw = new Coursework();
        
        setUp set = new setUp("Clock", 350);
        set.start();
    }

    public static Dials speedDial2;
    public static Dials speedDial3;

    public static Bar temperature;

    private final JPanel mainPanel;
    private final JPanel firstLine;
    public static JPanel TopDials;
    private final JPanel secondDials;

    public static JTextField Speed = new JTextField();
    public static JButton decrement = new JButton("Decrease by 10");
    public static JButton increment = new JButton("Increase by 10");
    public static JButton speedCal = new JButton("Calculate");
    public static JButton OnOff = new JButton("Turn on");
    
    public static JButton scriptBtn = new JButton("Run script");

    public static JTextField engineStatus = new JTextField();
    
    private final JTextField welcome = new JTextField();
    private final JTextField toStart = new JTextField();

    public static double speed;
    public static double pressure;
    public static double temperatureCount;

    public Coursework() {
        
        myEngine.engine();
        //This is creating and setting up a new JFrame.
        JFrame dashboard = new JFrame("This is the coursework");
        dashboard.setSize(1180, 900);
        dashboard.setLocation(350, 0);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        Singleton x = Singleton.getInstance();
        Singleton y = Singleton.getInstance();
        
        time.setVisible(true);

        Speed.setBounds(50, 300, 200, 30);
        dashboard.add(Speed);
        mainPanel = new JPanel();

        //This is the first panel
        firstLine = new JPanel(new GridLayout(2,2));
        firstLine.setVisible(true);
        mainPanel.add(firstLine);
        
        welcome.setVisible(true);
        welcome.setSize(200, 200);
        toStart.setLocation(0, 0);
        firstLine.add(welcome);
        
        //This is the on/off button
        OnOff.setVisible(true);
        OnOff.setBounds(600, 100, 100, 30);
        OnOff.setBackground(Color.green);
        firstLine.add(OnOff);
        model.OnOffBtn();
        
        toStart.setVisible(true);
        toStart.setSize(200, 200);
        toStart.setLocation(0, 50);
        firstLine.add(toStart);
        
        welcome.setText(x.welcome + "Welcome to our train dashboard");
        toStart.setText(x.welcome + "To start, click the Turn on button");

        engineStatus.setVisible(true);
        engineStatus.setSize(100, 100);
        firstLine.add(engineStatus);

        //This is the text for calcul
        speedCal.setVisible(true);
        speedCal.setBounds(50, 350, 200, 30);
        dashboard.add(speedCal);

        increment.setVisible(true);

        increment.setBounds(50, 400, 200, 30);
        dashboard.add(increment);

        decrement.setVisible(true);

        decrement.setBounds(50, 450, 200, 30);
        dashboard.add(decrement);
        
        scriptBtn.setVisible(true);
        scriptBtn.setBounds(400,450, 200, 30);
        dashboard.add(scriptBtn);
        
        model.DecreaseSpeed();
        model.IncreaseSpeed();
        model.SpeedControl();

        mainPanel.setSize(200, 200);
        mainPanel.setVisible(true);
        dashboard.add(mainPanel);
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.PAGE_AXIS));

        TopDials = new JPanel();
        TopDials.setSize(200, 200);
        TopDials.setVisible(true);
        TopDials.setBackground(Color.gray);
        mainPanel.add(TopDials);

        secondDials = new JPanel();
        secondDials.setSize(100, 100);
        secondDials.setVisible(true);
        secondDials.setBackground(Color.darkGray);
        mainPanel.add(secondDials);

        //Creating a new dial for speed
        speedDial2 = new Dials();
        speedDial2.setVisible(true);
        TopDials.add(speedDial2);
        //speedDial2.setValue(speed);
        speedDial2.setLabel("Speed");

        speedDial3 = new Dials();
        speedDial3.setVisible(true);
        TopDials.add(speedDial3);
        speedDial3.setValue(80);
        speedDial3.setLabel("Pressure");

        speed = 0;
        speedDial2.repaint();
        speedDial2.setValue((int) speed);

        pressure = 0;
        speedDial3.repaint();
        speedDial3.setValue((int) pressure);

        temperature = new Bar();
        temperature.setVisible(true);
        secondDials.add(temperature);
        temperature.setLabel("Temperature");

        dashboard.setVisible(true);
    }
}
