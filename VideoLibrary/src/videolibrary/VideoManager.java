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
    
    public void deleteVideo(long id);

    public List<Video> getVideoByTitle(String title);
    
    public List<Video> getAllVideos();
    
    public Video getVideoById(long id);
    
    public List<Video> getVideoByYear(int year);
    
    public List<Video> getVideoByDirector(String name);
    
    public List<Video> getVideoByActor(String name);
    
    public List<Video> getVideoByGenre(Genre genre);
    
    public List<Video> getVideoByCountry(String country);
}
