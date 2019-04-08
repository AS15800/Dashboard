/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLScript;

/**
 *
 * @author as5373u
 */
public class dashboardEvent {
    
    private String type; // type of event e.g "speed"
    private String value; // value of the event e.g. "30"

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
    @Override
    public String toString() {
        return "type:" + type + " value:" + value;
        
    }
    
}
