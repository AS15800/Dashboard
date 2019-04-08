/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Time;

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

        instance.jTextField1.setText("4");
        instance.jTextField2.setText("4");

        pause();
        instance.jButton1ActionPerformed_proxy(null);
        pause();
        assertEquals(instance.jTextField3.getText(), "16");
    }
    
    private void pause() {
        try {
            Thread.sleep(1000);
        } catch (InterruptedException ex) {
            Logger.getLogger(TimeKeepingTest.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
}
