/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextField;


import java.awt.Color;
import java.util.Timer;
import java.util.TimerTask;
import javax.swing.BoxLayout;

import Dials.Dials;
import Dials.Bar;
import Game.GameLogic;
import Time.TimeKeeping;

/**
 *
 * @author as5373u
 */
public class Coursework {

    private final TimeKeeping time = new TimeKeeping();
    private final GameLogic game = new GameLogic();

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        Coursework cw = new Coursework();
    }

    private final Dials speedDial2;
    private final Dials speedDial3;

    private final Bar temperature;

    private final JPanel mainPanel;
    private final JPanel firstLine;
    private final JPanel TopDials;
    private final JPanel secondDials;

    JTextField Speed = new JTextField();
    JButton decrement = new JButton("Decrease by 10");
    JButton increment = new JButton("Increase by 10");
    JButton speedCal = new JButton("Calculate");
    JButton OnOff = new JButton("Turn on");

    JTextField engineStatus = new JTextField();

    private double speed;
    private double pressure;
    private double temperatureCount;

    public Coursework() {
        //This is creating and setting up a new JFrame.
        JFrame dashboard = new JFrame("This is the coursework");
        dashboard.setSize(1180, 900);
        dashboard.setLocation(350, 0);
        dashboard.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        
        time.setVisible(true);
        game.setVisible(true);

        Speed.setBounds(50, 250, 200, 30);
        dashboard.add(Speed);
        mainPanel = new JPanel();

        //This is the first panel
        firstLine = new JPanel();
        firstLine.setVisible(true);
        mainPanel.add(firstLine);

        //This is the on/off button
        OnOff.setVisible(true);
        OnOff.setBounds(600, 100, 100, 30);
        OnOff.setBackground(Color.green);
        firstLine.add(OnOff);
        OnOffBtn();

        engineStatus.setVisible(true);
        engineStatus.setSize(100, 100);
        firstLine.add(engineStatus);

        //This is the text for calcul
        speedCal.setVisible(true);
        speedCal.setBounds(50, 300, 200, 30);
        dashboard.add(speedCal);

        increment.setVisible(true);

        increment.setBounds(50, 400, 200, 30);
        dashboard.add(increment);

        decrement.setVisible(true);

        decrement.setBounds(50, 450, 200, 30);
        dashboard.add(decrement);

        DecreaseSpeed();
        IncreaseSpeed();
        SpeedControl();

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

    //This is the On/Off button which simulate the engine turning and off.
    //Turning off will diable all the controls so the user can't do anything in the dashboard.
    private void OnOffBtn() {
        SystemOff();
        OnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OnOff.getText() == "Turn on") {

                    SystemOn();
                    OnOff.setText("Turn off");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.out.println("Problem: " + ex);
                    }

                    new Thread() {
                        @Override
                        public void run() {
                            engineStatus.setText("Engine Starting");
                        }
                    }.start();

                } else if (OnOff.getText() == "Turn off") {

                    new Thread() {
                        @Override
                        public void run() {
                            speed = 0;
                            speedDial2.repaint();
                            speedDial2.setValue((int) speed);
                            pressure = 0;
                            speedDial3.repaint();
                            speedDial3.setValue((int) pressure);
                            SystemOff();
                            OnOff.setText("Turn on");
                            try {
                                Thread.sleep(500);
                            } catch (InterruptedException ex) {
                                System.out.println("Problem: " + ex);
                            }

                        }
                    }.start();

                }
            }
        });
    }

    //This method will be called when the user turns off the dashboard.
    private void SystemOff() {
        decrement.setEnabled(false);
        increment.setEnabled(false);
        speedCal.setEnabled(false);
        Speed.setEnabled(false);

        OnOff.setBackground(Color.green);
        temperatureDecrease();
    }

    //This methods is called when the dashboard is turned off.
    private void SystemOn() {
        decrement.setEnabled(true);
        increment.setEnabled(true);
        speedCal.setEnabled(true);
        Speed.setEnabled(true);

        OnOff.setBackground(Color.red);
    }

    //This is allows the user to decrease the speed of the train.
    //It is set to decrease by 10 mph for every click
    private void DecreaseSpeed() {

        decrement.addActionListener((ActionEvent e) -> {
            //Reduces the speed and re-paint the hand dial while deleting the
            //previously drawn hand dial
            speed = speed - 10;
            speedDial2.repaint();
            TopDials.validate();
            speedDial2.setValue((int) speed);
            increment.setEnabled(true);
            while (speed < 1) {

                decrement.setEnabled(false);
                speedDial2.setValue(0);
                speedDial2.repaint();
                break;
            }

            if (speed <= 0) {
                Speed.setText("Train is stationed");
                temperatureDecrease();
                return;
            } else if (speed >= 1) {
                Speed.setText("");
            }

            if (speed <= -1) {
                speed = 0;
            }
            pressure();
            temperature();
        });
    }

    private void temperatureDecrease() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                temperatureCount -= 0.5;

                temperature.repaint();
                temperature.setValue((int) temperatureCount);

                if (temperatureCount <= 0) {
                    temperatureCount = 0;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);

                }
            }
        };
        timer.schedule(task, 0, 500);
    }

    private void temperature() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                if (speed > 1) {
                    temperatureCount += 0.05;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);
                } else if (speed > 30 && temperatureCount > 10) {
                    temperatureCount += 0.1;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);
                } else if (speed > 50 && temperatureCount > 20) {
                    temperatureCount += 0.5;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);
                } else if (speed > 90 && temperatureCount > 30) {
                    temperatureCount += 1;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);
                }

                if (temperatureCount >= 90) {
                    temperatureCount = 90;
                }
            }
        };
        timer.schedule(task, 0, 100);
    }

    private void pressure() {
        pressure = speed * 0.8;
        if (pressure >= 70) {
            pressure = 70;
        }

        speedDial3.repaint();
        speedDial3.setValue((int) pressure);
    }

    //Work similar to the decrease speed except increases the speed of the train by 10.
    private void IncreaseSpeed() {
        increment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                long time = System.currentTimeMillis();
                speed = speed + 10;
                speedDial2.repaint();
                TopDials.validate();
                speedDial2.setValue((int) speed);
                decrement.setEnabled(true);
                while (speed > 99) {
                    increment.setEnabled(false);
                    speedDial2.setValue(100);
                    speedDial2.repaint();
                    break;
                }
                if (speed >= 100) {
                    Speed.setText("Maximum speed reached");
                } else if (speed <= 99) {
                    Speed.setText("");
                }
                if (speed >= 101) {
                    speed = 0;
                }
                pressure = speed * 0.8;
                if (pressure >= 70 && pressure <= 90) {
                    pressure = 70;
                }
                speedDial3.repaint();
                speedDial3.setValue((int) pressure);

                if (speed == 100) {
                    try {
                        //Thread.sleep(1000);
                        pressure = 100;
                    } catch (Exception ex) {
                        System.out.println("Something went wrong" + ex);
                    }
                }

                temperature();
            }
        });
    }

    //This allows the user to input the desired speed.
    private void SpeedControl() {
        speedCal.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                speed = Integer.parseInt(Speed.getText());
                speedDial2.repaint();
                TopDials.validate();
                speedDial2.setValue((int) speed);
                if (speed >= 0 && speed <= 100) {
                    Speed.setText("");
                } else {
                    speedDial2.setValue(0);
                    Speed.setText("Invalid input");
                }
                pressure = speed * 0.8;
                if (pressure >= 70) {
                    pressure = 70;
                }
                speedDial3.repaint();
                speedDial3.setValue((int) pressure);
            }
        });
    }
}
