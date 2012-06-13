/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import org.w3c.dom.Document;

/**
 *
 * @author Matus Makovy
 */
public interface ImdbDownloader {
    
    public boolean download(String title);
    public boolean downloadViaID(String id); //just for testing purposes
    public Document openXML(String title);
    public boolean readOnlineXML(Document doc);

    public Video getVideo();
    
}
