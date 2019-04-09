/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package XMLScript;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.XMLReader;
import org.xml.sax.helpers.DefaultHandler;

/**
 *
 * @author as5373u
 */
public class GenerateXML extends DefaultHandler{
    
    public final static String EVENT_TAG = "dashboard_event";

    public final static String TYPE_TAG = "type";

    public final static String VALUE_TAG = "value";

    public final static String DELAY_TAG = "delay";

    public final static int delayUnits = 100;

    private dashboardEvent currentEvent = null;
    private String currentTag = "";

    private final EventList dashBoardListeners;

    private final XMLReader xmlReader;

    public GenerateXML() throws Exception {
        dashBoardListeners = new EventList();

        SAXParserFactory spf = SAXParserFactory.newInstance();
        spf.setNamespaceAware(true);
        SAXParser saxParser = spf.newSAXParser();
        xmlReader = saxParser.getXMLReader();
    }

    public void processScriptFile(String filename) throws IOException, SAXException {
        // register the current object to receive callbacks when elements are encountered in the XML file
        xmlReader.setContentHandler(this);
        // Start the parsing process.  As the file is processed methods in the startElement(), endElement() and
        // characters() methods in the current object will be called to handle the content of the XML file.
        xmlReader.parse(convertToFileURL(filename));
    }

    @Override
    public void startElement(String namespaceURI,
            String localName,
            String qName,
            Attributes atts)
            throws SAXException {

        currentTag = localName;

        if (currentTag.equals(EVENT_TAG)) {
            currentEvent = new dashboardEvent();
        }
    }

    @Override
    public void characters(char ch[], int start, int length)
            throws SAXException {
        String val = new String(ch, start, length).trim();

        if (val.length() < 1) { 
            return;
        }

        switch (currentTag) {
            case TYPE_TAG:
                currentEvent.setType(val);
                break;
            case VALUE_TAG:
                currentEvent.setValue(val);
                break;
            case DELAY_TAG:
                pause(Integer.parseInt(val));
                break;
        }
    
    }
        
    @Override
    public void endElement(String uri, String localName, String qName)
            throws SAXException {

        if (localName.equals(EVENT_TAG)) {
            List<EventListener> listeners = dashBoardListeners.getListeners(currentEvent.getType());
            if (listeners != null) {
                for (EventListener dbel : listeners) {
                    dbel.processDashBoardEvent(this, currentEvent);
                }
            }
            currentEvent = null;
        }
        currentTag = "";
    }
    
    private void pause(int delay) {
        try {
            Thread.sleep(delay * delayUnits);
        } catch (InterruptedException ex) {
            Logger.getLogger(GenerateXML.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void registerDashBoardEventListener(String type, EventListener dbel) {
        dashBoardListeners.addListener(type, dbel);
    }

    public void removeDashBoardEventListener(String type, EventListener dbel) {
        dashBoardListeners.removeListener(type, dbel);
    }

    private static String convertToFileURL(String filename) {
        String path = new File(filename).getAbsolutePath();
        if (File.separatorChar != '/') {
            path = path.replace(File.separatorChar, '/');
        }

        if (!path.startsWith("/")) {
            path = "/" + path;
        }
        return "file:" + path;
    }

    
    private static void usage() {
        System.err.println("Usage:  DashboardEventGeneratorFromXML <file.xml>");
        System.err.println("       -usage or -help = this message");
        System.exit(1);
    }

    public static void main(String[] args) throws Exception {
        String filename = null;

        // get the filename if persent
        for (int i = 0; i < args.length; i++) {
            filename = args[i];
            if (i != args.length - 1) {
                usage();
            }
        }

        if (filename == null) {
            usage();
        }
        
        // Create an instance of DashboardEventGeneratorFromXML and test it
        GenerateXML me = new GenerateXML();
        EventListener dbel = new EventListener() {
            @Override
            public void processDashBoardEvent(Object originator, dashboardEvent dbe) {
                System.out.println("***** " + dbe);
            }
        };
        me.registerDashBoardEventListener("speed", dbel);
        me.processScriptFile(filename);
    }
    
}
