
package videolibrary;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.*;
import org.xml.sax.SAXException;

/**
 *
 * @author Matus Makovy
 */
public class ImdbDownloaderImpl implements ImdbDownloader {
    
    final static Logger log = LoggerFactory.getLogger(ImdbDownloaderImpl.class);

    @Override
    public void download(String title) {
        readOnlineXML(openXML(title));
    }

    /**
     * Loading XML from IMDB API
     */
    @Override
    public Document openXML(String title) {

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            /**
             * Replacing white space with string %20
             */
            String titleEdited = title.replace(" ", "%20");
            Document doc = builder.parse(new URL("http://www.imdbapi.com/?t=" + titleEdited + "&r=XML").openStream());
            return doc;
        } catch (ParserConfigurationException ex) {
            log.error("Error when opening XML", ex);
            System.err.println("Parser Configuration fault. Error when opening XML");
            return null;
        } catch (SAXException ex) {
            log.error("Error when opening XML", ex);
            System.err.println("SAX Exeption. Error when opening XML");
            return null;
        } catch (IOException ex) {
            log.error("Error when opening XML", ex);
            System.err.println("IO Exception. Error when opening XML");
            return null;
        }


    }

    /**
     * Reading XML doc
     * @param doc 
     */
    @Override
    public void readOnlineXML(Document doc) {
        
        VideoManager manager = new VideoManagerImpl();

        if (doc == null) {
            log.error("Doc is null - reading XML");
            throw new IllegalArgumentException("doc is null");
        }
        
        NodeList root = doc.getElementsByTagName("root");
        Node rootTag = root.item(0);
        Element rootElement = (Element) rootTag;
        
        if (rootElement.getAttribute("response") == "False" ){
            log.error("Movie wasn't found.");
            System.err.println("The movie wasn't found");
        }

        NodeList movieTags = doc.getElementsByTagName("movie");
        Node movieTag = movieTags.item(0);
        Element movieTagElement = (Element) movieTag;

        Video video = new Video();

        /**
         * Setting TITLE
         */
        String title = movieTagElement.getAttribute("title");
        video.setTitle(title);

        /**
         * Setting YEAR
         */
        String year = movieTagElement.getAttribute("year");
        Integer yearInt = 9999;
        try {
            yearInt = Integer.parseInt(year);
        } catch (Exception ex) {
            log.error("Error when reading XML", ex);
            System.err.println("year is not a number");
        }
        if (yearInt < 2100) {
            video.setYear(yearInt);
        }

        /**
         * Setting (IMDB)RATING
         */
        String rating = movieTagElement.getAttribute("imdbRating");
        Integer ratingInt = 15;
        try {
            ratingInt = Integer.parseInt(rating.substring(0, 1));
        } catch (Exception ex) {
            log.error("Error when reading XML", ex);
            System.err.println("rating is not a number");
        }
        if (ratingInt < 11) {
            video.setRating(ratingInt);
        }

        /**
         * Setting ACTORS
         */
        String actors = movieTagElement.getAttribute("actors");
        String[] actorsSplited = actors.split(", ");
        video.setActors(Arrays.asList(actorsSplited));

        /**
         * Setting DIRECTORS
         */
        String director = movieTagElement.getAttribute("director");
        List<String> directors = new ArrayList();
        directors.add(director);
        video.setDirectors(directors);

        /**
         * Setting GENRES
         */
        String genres = movieTagElement.getAttribute("genre");

        String[] genresSplited = genres.split(", ");

        List<Genre> genresList = new ArrayList();
        for (int i = 0; i < genresSplited.length; i++) {
            switch (genresSplited[i].toLowerCase()) {
                case "action":
                    genresList.add(Genre.ACTION);
                    break;
                case "adventure":
                    genresList.add(Genre.ADVENTURE);
                    break;
                case "animation":
                    genresList.add(Genre.ANIMATION);
                    break;
                case "biography":
                    genresList.add(Genre.BIOGRAPHY);
                    break;
                case "comedy":
                    genresList.add(Genre.COMEDY);
                    break;
                case "crime":
                    genresList.add(Genre.CRIME);
                    break;
                case "doc":
                case "document":
                case "documentary":
                    genresList.add(Genre.DOCUMENTARY);
                    break;
                case "drama":
                    genresList.add(Genre.DRAMA);
                    break;
                case "family":
                    genresList.add(Genre.FAMILY);
                    break;
                case "fantasy":
                    genresList.add(Genre.FANTASY);
                    break;
                case "history":
                    genresList.add(Genre.HISTORY);
                    break;
                case "horror":
                    genresList.add(Genre.HORROR);
                    break;
                case "music":
                    genresList.add(Genre.MUSIC);
                    break;
                case "musical":
                    genresList.add(Genre.MUSICAL);
                    break;
                case "mystery":
                    genresList.add(Genre.MYSTERY);
                    break;
                case "romance":
                    genresList.add(Genre.ROMANCE);
                    break;
                case "sci-fi":
                    genresList.add(Genre.SCIENCE_FICTION);
                    break;
                case "sport":
                    genresList.add(Genre.SPORT);
                    break;
                case "thriller":
                    genresList.add(Genre.THRILLER);
                    break;
                case "war":
                    genresList.add(Genre.WAR);
                    break;
                case "western":
                    genresList.add(Genre.WESTERN);
                    break;
                default:
                    log.error("Unknown genre - reading XML");
                    System.err.println("Unknown genre");

            }

        }
        video.setGenres(genresList);
        
        /**
         * Setting COUNTRIES
         */
        List<String> countries= new ArrayList();
        countries.add("Unknown (IMDB API)");
        video.setCountries(countries);
        
        /**
         * Add video to database
         */

        manager.addVideo(video);
    }
}
