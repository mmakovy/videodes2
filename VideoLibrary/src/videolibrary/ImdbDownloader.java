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
    
    public void download(String title);
    public Document openXML(String title);
    public boolean readOnlineXML(Document doc);
    
}
