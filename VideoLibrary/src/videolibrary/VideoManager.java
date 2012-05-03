/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.util.List;

/**
 *
 * @author Andrej
 */
public interface VideoManager {

    public void addVideo(Video video);

    public void searchVideoByTitle(String title);
    
    public List<Video> getAllVideos();

    public void exportToOdf(String fileName);

    public void importFromOdf(String fileName);
}
