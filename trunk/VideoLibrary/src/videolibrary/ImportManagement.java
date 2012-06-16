/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.io.File;
import java.util.List;
import org.w3c.dom.Document;

/**
 *
 * @author Matus Makovy
 */
public interface ImportManagement {
    public boolean getError();
    public List<Video> getVideos();
    public void importFromOdf(File file);
    public File unzipper(File file);
    public Document openXML(File file);
    public void readXML(Document doc) throws IllegalArgumentException;
   
}
