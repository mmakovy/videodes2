/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.io.File;
import java.util.List;
import org.odftoolkit.odfdom.doc.OdfSpreadsheetDocument;
import org.odftoolkit.odfdom.doc.table.OdfTable;
import org.w3c.dom.Element;
import org.odftoolkit.odfdom.doc.OdfDocument.*;
import org.odftoolkit.odfdom.pkg.OdfPackage;

/**
 *
 * @author Samo
 */
public class ExportManagementImpl implements ExportManagement{
    
    @Override
    public void exportToOdf(File file) {
        VideoManager manager = new VideoManagerImpl();
        List<Video> videoList = manager.getAllVideos();
        //String filenam = "abc";
        try{
            OdfSpreadsheetDocument odf = OdfSpreadsheetDocument.newSpreadsheetDocument();
            //OdfSpreadsheetDocument odf = OdfSpreadsheetDocument.loadDocument("DemoTemplate.ods");
            
        int j=0;
        for (Video video : videoList){j++;}
        
        String[][] data = new String[j+1][7];
        data[0][0] = "Title";
        data[0][1] = "Director(s)";
        data[0][2] = "Actor(s)";
        data[0][3] = "Year";
        data[0][4] = "Countries";
        data[0][5] = "Genres";
        data[0][6] = "Rating";
        int i = 0;
        for (Video video : videoList){
                        
            data[i+1][0] = video.getTitle();
            
            List<String> directorsList = video.getDirectors();
            String directors = "";
            int k=0;
            for (String director : directorsList){
                if (k==0) {directors = director;
                } else {
                directors = directors + ", " + director;
                }
                k++;
            }
            data[i+1][1] = directors;
            k=0;
            List<String> actorsList = video.getActors();
            String actors = "";
            for (String actor : actorsList){
                if (k==0){actors=actor;
                    } else {
                    actors = actors + "," + actor;
                    }
            k++;
            }
            data[i+1][2] = actors;
            
            data[i+1][3] = Integer.toString(video.getYear());
            
            k=0;
            List<String> countriesList = video.getCountries();
            String countries = "";
            for (String country : countriesList){
                if (k==0){
                countries=country;
                } else {
                    countries = countries + ", " + country;
                }
                k++;
            }
            data[i+1][4] = countries;
            
            k=0;
            List<Genre> genresList = video.getGenres();
            String genres = "";
            for (Genre genre : genresList){
                if (k==0){genres=genre.toString();
                } else {
                    genres = genres + ", " + genre.toString();
                }
                k++;
            }
            data[i+1][5] = genres;
            
            data[i+1][6] = Integer.toString(video.getRating());
            i++;
        }
        
        odf.getTableByName("Sheet1").remove();
        OdfTable videoTable = OdfTable.newTable(odf, null, null, data);
        videoTable.setTableName("Videos");
        videoTable.getCellByPosition(0, 0).setStringValue("Title");
        
        odf.save(file);
        }  catch (Exception e) { e.printStackTrace(); }            
    }

}