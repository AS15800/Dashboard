/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import static coursework.Coursework.OnOff;
import static coursework.Coursework.pressure;
import static coursework.Coursework.speed;
import static coursework.Coursework.speedDial2;
import static coursework.Coursework.speedDial3;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 *
 * @author as5373u
 */
public class EngineStatus implements Text {

    @Override
    public void engine() {

//        public void OnOffBtn() {
        SystemActive.SystemOff();
        OnOff.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (OnOff.getText() == "Turn on") {

                    SystemActive.SystemOn();
                    OnOff.setText("Turn off");
                    Coursework.engineStatus.setText("Engine starting");
                    try {
                        Thread.sleep(500);
                    } catch (InterruptedException ex) {
                        System.out.println("Problem: " + ex);
                    }

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
                            SystemActive.SystemOff();
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
//    }
    }

}
