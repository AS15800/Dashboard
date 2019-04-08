/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import static coursework.Coursework.*;
import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author as5373u
 */
public class SystemActive extends Temperature{
    
    public void SystemOff() {
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
    
    public void OnOffBtn() {
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
    
}
