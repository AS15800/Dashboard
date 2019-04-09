/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLScript;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 *
 * @author as5373u
 */
public class EventList {
    
    HashMap<String, List<EventListener>> eventListeners;

    public EventList() {
        eventListeners = new HashMap<>();
    }

    public void addListener(String type, EventListener listener) {
        List<EventListener> dbl = eventListeners.get(type);
        if (dbl == null) {
            dbl = new ArrayList<>();
        }
        dbl.add(listener); // add the listener to the list
        eventListeners.put(type, dbl); // update the map
    }

    public void removeListener(String type, EventListener listener) {
        List<EventListener> dbl = eventListeners.get(type);
        if (dbl != null) {
            while (dbl.remove(listener));
        }
    }

    public List<EventListener> getListeners(String type) {
        return eventListeners.get(type);
    }
    
}
