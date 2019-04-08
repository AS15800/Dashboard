/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package coursework;

/**
 *
 * @author as5373u
 */
public class Singleton {
    
    private static Singleton singleInstance = null;
    
    public String welcome;
    
    private Singleton(){
        welcome = "From the Admin: ";
    }
    
    public static Singleton getInstance(){
        if(singleInstance == null){
            singleInstance = new Singleton();
        }
        return singleInstance;
    }
    
}
