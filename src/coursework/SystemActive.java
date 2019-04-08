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
    
    public static void SystemOff() {
        decrement.setEnabled(false);
        increment.setEnabled(false);
        speedCal.setEnabled(false);
        Speed.setEnabled(false);

        OnOff.setBackground(Color.green);
        temperatureDecrease();
    }

    //This methods is called when the dashboard is turned off.
    public static void SystemOn() {
        decrement.setEnabled(true);
        increment.setEnabled(true);
        speedCal.setEnabled(true);
        Speed.setEnabled(true);

        OnOff.setBackground(Color.red);
    }    
}
