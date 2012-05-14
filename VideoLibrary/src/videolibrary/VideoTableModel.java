/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.util.ArrayList;
import java.util.List;
import javax.swing.table.AbstractTableModel;

/**
 *
 * @author Andrej
 */
public class VideoTableModel extends AbstractTableModel {
 
    private List<Video> videos = new ArrayList<Video>();
 
    @Override
    public int getRowCount() {
        return videos.size();
    }
 
    @Override
    public int getColumnCount() {
        return 7;
    }
 
    @Override
    public Object getValueAt(int rowIndex, int columnIndex) {
        Video video = videos.get(rowIndex);
        switch (columnIndex) {
            case 0:
                return video.getTitle();
            case 1:
                return video.getDirectors();
            case 2:
                return video.getActors();
            case 3:
                return video.getYear();
            case 4:
                return video.getCountries();
            case 5:
                return video.getGenres();
            case 6:
                return video.getRating();
            default:
                throw new IllegalArgumentException("columnIndex");
        }
    }
    
    @Override
public String getColumnName(int columnIndex) {
    switch (columnIndex) {
        case 0:
            return "Title";
        case 1:
            return "Director(s)";
        case 2:
            return "Actor(s)";
        case 3:
            return "Year";
        case 4:
            return "Countries";
        case 5:
            return "Genre(s)";
        case 6:
            return "Rating";  
        default:
            throw new IllegalArgumentException("columnIndex");
    }
}

    
    public void addVideo(Video video) {
         videos.add(video);
         int lastRow = videos.size() - 1;
         fireTableRowsInserted(lastRow, lastRow);
    }
    
    public void removeVideo(int i) {
        videos.remove(i);
        int lastRow = videos.size() + 1;
    }
    
    public void removeAll() {
        int size = videos.size();
        for (int i=size-1; i>=0; i--) {
            videos.remove(i);
        }
        if (size != 0) fireTableRowsDeleted(0, size-1);
    }
}