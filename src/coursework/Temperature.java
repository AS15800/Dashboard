/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

import static coursework.Coursework.*;
import java.util.Timer;
import java.util.TimerTask;

/**
 *
 * @author as5373u
 */
public class Temperature {
    
    public void temperature() {
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
    
    public static void temperatureDecrease() {
        Timer timer = new Timer();
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                try{
                temperatureCount -= 0.5;
                
                temperature.repaint();
                temperature.setValue((int) temperatureCount);

                if (temperatureCount <= 0) {
                    temperatureCount = 0;

                    temperature.repaint();
                    temperature.setValue((int) temperatureCount);

                }
                } catch (Exception e){
                }
            }
        };
        timer.schedule(task, 0, 500);
    }
    
}
