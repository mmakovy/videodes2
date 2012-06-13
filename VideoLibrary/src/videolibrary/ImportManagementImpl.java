
package videolibrary;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipInputStream;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.w3c.dom.DOMException;
import org.w3c.dom.Document;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

/**
 *
 * @author Matus Makovy
 */
public class ImportManagementImpl implements ImportManagement {
    private List<Video> videos = null;
    
    final static Logger log = LoggerFactory.getLogger(ImportManagementImpl.class);
    
    public List<Video> getVideos() {
        return Collections.unmodifiableList(videos);
    }
    
    @Override
    public void importFromOdf(File file) {
        readXML(openXML(unzipper(file)));
    }

    @Override
    public File unzipper(File file) {

        if (file == null) {
            log.error("Error unzipping ODS, file is null");
            throw new IllegalArgumentException("File is null");
        }

        ZipInputStream in = null;
        OutputStream out = null;
                
        try {
             
            in = new ZipInputStream(new FileInputStream(file));
            
            ZipEntry entry = in.getNextEntry();
            while (!(entry.getName().equals("content.xml"))) {
                entry = in.getNextEntry();
            }

            String outFilename = file.getName() + "_content.xml";
            out = new FileOutputStream(outFilename);

            
            byte[] buf = new byte[1024];
            int len;
            while ((len = in.read(buf)) > 0) {
                out.write(buf, 0, len);
            }

            
            
            return new File(file.getName() + "_content.xml");


        } catch (FileNotFoundException ex) {
            log.error("Error when unzipping ODS",ex);
            System.err.println("File not found");
            return null;
        } catch (IOException ex) {
            log.error("Error when unzipping ODS",ex);
            System.err.println("IO error");
            return null;
        } finally {
            if (in != null){
                try{
                    in.close();
                }catch(IOException ex){
                    log.error("Error when closing stream",ex);
                    System.err.println("Error when closing inputstream");    
                }
            }
            
            if (out != null){
                try{
                    out.close();
                }catch(IOException ex){
                    log.error("Error when closing stream",ex);
                    System.err.println("Error when closing outputstream");    
                }
            }
        }
    }

    @Override
    public Document openXML(File file) {

        if (file == null) {
            log.error("Error opening XML, file is null");
            throw new IllegalArgumentException("File is null");
        }

        DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
        try {
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document doc = builder.parse(file);
            return doc;
        } catch (ParserConfigurationException ex) {
            log.error("Error when opening XML",ex);
            System.err.println("Parser Configuration fault. Error when opening XML");
            return null;
        } catch (SAXException ex) {
            log.error("Error when opening XML",ex);
            System.err.println("SAX Exeption. Error when opening XML");
            return null;
        } catch (IOException ex) {
            log.error("Error when opening XML",ex);
            System.err.println("IO Exception. Error when opening XML");
            return null;
        }


    }

    @Override
    public void readXML(Document doc) {
        videos = new ArrayList<Video>();
        if (doc == null) {
            log.error("Variable doc is null - reading XML");
            throw new IllegalArgumentException("Variabile DOC is null");
        }

        int titleColumnNumber = 300;
        int actorsColumnNumber = 300;
        int directorsColumnNumber = 300;
        int genresColumnNumber = 300;
        int countryColumnNumber = 300;
        int yearColumnNumber = 300;
        int ratingColumnNumber = 300;

        NodeList rows = doc.getElementsByTagName("table:table-row");
        Node row1 = rows.item(0);
        NodeList cells1 = row1.getChildNodes();
        for (int k = 0; k < cells1.getLength(); k++) {
            switch (cells1.item(k).getFirstChild().getTextContent().toLowerCase()) {
                case "title":
                    titleColumnNumber = k;
                    break;
                case "actors":
                case "actor":
                    actorsColumnNumber = k;
                    break;
                case "director":
                case "directors":
                    directorsColumnNumber = k;
                    break;
                case "year":
                    yearColumnNumber = k;
                    break;
                case "rating":
                    ratingColumnNumber = k;
                    break;
                case "genre":
                case "genres":
                    genresColumnNumber = k;
                    break;
                case "countries":
                case "country":
                    countryColumnNumber = k;
                    break;

            }
        }

        for (int m = 1; m < rows.getLength() - 2; m++) {

            NodeList cells = rows.item(m).getChildNodes();

            if (cells.item(titleColumnNumber).getFirstChild() == null) {
                log.error("WRONG DATA in certain line in ODF document");
                System.err.println("WRONG DATA" + m);
            } else {
                Video video = new Video();

                /**
                 * Setting TITLE
                 */
                video.setTitle(cells.item(titleColumnNumber).getFirstChild().getTextContent());

                /**
                 * Seting ACTORS
                 */
                if (actorsColumnNumber <= 256) {
                    String actors = cells.item(actorsColumnNumber).getFirstChild().getTextContent();
                    String[] actorsSplited = actors.split(", ");
                    video.setActors(Arrays.asList(actorsSplited));
                }

                /**
                 * Setting DIRECTORS
                 */
                if (directorsColumnNumber <= 256) {
                    String directors = cells.item(directorsColumnNumber).getFirstChild().getTextContent();
                    String[] directorsSplited = directors.split(", ");
                    video.setDirectors(Arrays.asList(directorsSplited));
                }

                /**
                 * Setting YEAR
                 */
                if (yearColumnNumber <= 256) {
                    int year = 9999;
                    try {
                        year = Integer.parseInt(cells.item(yearColumnNumber).getFirstChild().getTextContent());
                    } catch (DOMException | NumberFormatException ex) {
                        log.error("Year is not a number - reading XML");
                        System.err.println("Year is not a number");
                    }
                    if (year < 2100) {
                        video.setYear(year);
                    }
                }

                
                /**
                 * Setting RATING
                 */
                if (ratingColumnNumber <= 256) {
                    int rating = 15;
                    try {
                        rating = Integer.parseInt(cells.item(ratingColumnNumber).getFirstChild().getTextContent());
                    } catch (DOMException | NumberFormatException ex) {
                        log.error("Rating is not a number - reading XML");
                        System.err.println("Rating is not a number");
                    }
                    if (rating < 11) {
                        video.setRating(rating);
                    }
                }

                /**
                 * Setting GENRES
                 */
                if (genresColumnNumber <= 256) {
                    String genres = cells.item(genresColumnNumber).getFirstChild().getTextContent();
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
                            case "WESTERN":
                                genresList.add(Genre.WESTERN);
                                break;
                            default :
                                log.error("Unknown genre - reading XML");
                                System.err.println("Unknown genre");
                        }
                    }
                    video.setGenres(genresList);



                }

                /**
                 * Setting COUNTRIES
                 */
                if (countryColumnNumber <= 256) {
                    String countries = cells.item(countryColumnNumber).getFirstChild().getTextContent();
                    String[] countriesSplited = countries.split(", ");
                    video.setCountries(Arrays.asList(countriesSplited));
                }
                videos.add(video);
            }
        }
        System.err.println("SIze of videos = " + videos.size());
    }
}
