/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Time;

import java.awt.event.ActionEvent;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author as5373u
 */
public class TimeKeepingTest {
    private TimeKeeping instance;
    
    public TimeKeepingTest() {
    }
    
    @BeforeClass
    public static void setUpClass() {
    }
    
    @AfterClass
    public static void tearDownClass() {
    }
    
    @Before
    public void setUp() {
        instance = new TimeKeeping();
        // just for fun let's see the form
        instance.setVisible(true);
    }
    
    @After
    public void tearDown() {
    }

    /**
     * Test of main method, of class TimeKeeping.
     */
    @Test
    public void testMain() {
        System.out.println("jButton1ActionPerformed_proxy1");

        instance.jTextField1.setText("100");

        pause();
        instance.jButton1ActionPerformed_proxy(null);
        pause();
        assertEquals(instance.jTextField2.getText(), "80.0");
    }
    
    @Test
    public void testMain1() {
        System.out.println("jButton1ActionPerformed_proxy2");

        instance.jTextField1.setText("1");

        pause();
        instance.jButton1ActionPerformed_proxy(null);
        pause();
        assertEquals(instance.jTextField2.getText(), "0.8");
    }
    
    @Test
    public void testMain2() {
        System.out.println("jButton1ActionPerformed_proxy3");

        instance.jTextField1.setText("-50");

        pause();
        instance.jButton1ActionPerformed_proxy(null);
        pause();
        assertEquals(instance.jTextField2.getText(), "-40.0");
    }
    
    @Test(expected = NumberFormatException.class)
    public void testMain3() {
        System.out.println("jButton1ActionPerformed_proxy4");

        instance.jTextField1.setText("x");

        pause();
        instance.jButton1ActionPerformed_proxy(null);
        pause();
    }
    
    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimeKeepingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
