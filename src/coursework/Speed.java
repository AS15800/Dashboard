/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import static coursework.Coursework.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.logging.Level;
import java.util.logging.Logger;

import XMLScript.*;

/**
 *
 * @author as5373u
 */
public class Speed extends SystemActive{
    
    public static final String XML_SCRIPT = "dashboard_script.xml";
    
    public Speed(){
    scriptBtn.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                new Thread() {
                    public void run() {
                        runXMLScript();
                    }
                }.start();
            }
        });
    
    
}
    
    private void runXMLScript() {
        try {
            GenerateXML dbegXML = new GenerateXML();

            // Register for speed events from the XML script file
            EventListener dbelSpeed = new EventListener() {
                @Override
                public void processDashBoardEvent(Object originator, dashboardEvent dbe) {
                    speedDial2.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("speed", dbelSpeed);

            // Register for petrol events from the XML script file
            EventListener dbelPetril = new EventListener() {
                @Override
                public void processDashBoardEvent(Object originator, dashboardEvent dbe) {
//                    petrolDial.setValue(Integer.parseInt(dbe.getValue()));
//                    petrolBar.setValue(Integer.parseInt(dbe.getValue()));
                }
            };
            dbegXML.registerDashBoardEventListener("petrol", dbelPetril);

            // Process the script file - it willgenerate events as it runs
            dbegXML.processScriptFile(XML_SCRIPT);

        } catch (Exception ex) {
            Logger.getLogger(Speed.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void pressure() {
        pressure = speed * 0.8;
        if (pressure >= 70) {
            pressure = 70;
        }else if(speed <= 0 ){
            pressure = 0;
        }

        speedDial3.repaint();
        speedDial3.setValue((int) pressure);
    }
    
    public void DecreaseSpeed() {

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
    
    public void IncreaseSpeed() {
        increment.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
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
    public void SpeedControl() {
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
