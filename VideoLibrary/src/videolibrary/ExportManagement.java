/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.io.File;
import java.util.List;

/**
 *
 * @author Andrej
 */
public interface ExportManagement {
    
    public boolean exportToOdf(File file);

    public void setList(List<Video> allVideos);
}
