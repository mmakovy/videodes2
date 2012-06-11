/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import org.basex.core.*;
import org.basex.core.cmd.*;
import org.basex.query.*;
import org.basex.query.item.Item;
import org.basex.query.iter.Iter;
import org.xml.sax.SAXException;

/**
 *
 * @author AjkoST
 */
public class DBTest {

    private static Context context = new Context();

    public static void main(final String[] args) throws BaseXException, QueryException, IOException, SAXException {
        /*
         * String a = null; try { a = new
         * org.basex.core.cmd.List().execute(context); } catch (BaseXException
         * ex) { Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE,
         * null, ex); } if (!a.contains("VideoDes2DB")) { try {
         * BaseXDriver.createCollection(context);
         *
         * } catch (BaseXException ex) {
         * Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
         * ex); } finally { try { BaseXDriver.closeCollection(context); } catch
         * (BaseXException ex) {
         * Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
         * ex); } }
         *
         * }
         */
        
        
        //*******************************************//
        //IMDB DOWNLOADER, STACI NAPISAT NAZOV FILMU//
        //*****************************************//
        ImdbDownloaderImpl imdb = new ImdbDownloaderImpl();
        imdb.download("Lord of the rings");

        
        //***********************//
        //RUCNE VYTVARANIE VIDEA//
        //*********************//        
        VideoManagerImpl manager = new VideoManagerImpl();
        Video video = new Video();
        video.setTitle("Titulok filmu");
        video.setYear(2002);
        ArrayList<String> r1 = new ArrayList<String>();
        r1.add("prva");
        r1.add("druhe");
        r1.add("treti");
        video.setActors(r1);
        video.setCountries(r1);
        video.setDirectors(r1);
        ArrayList<Genre> r2 = new ArrayList<Genre>();
        r2.add(Genre.WAR);
        r2.add(Genre.WESTERN);
        video.setGenres(r2);
        video.setRating(5);
       // manager.addVideo(video);



        //********************//
        //VYHODNOCOVAC XQUERY//
        //******************//  
        //teraz vracia celu DB

        BaseXDriver.openCollection(context);
        String result = null;
        String result2 = null;
        result = (new XQuery(
                "for $doc in collection('VideoDes2DB')"
                + "return $doc").execute(context));
        System.out.println(result);



        //****************************//
        //PREVOD OBJEKTU VIDEO DO XML//
        //**************************// 
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.omitField(Video.class, "id");
        x.alias("video", Video.class);
        String xml = x.toXML(video);
        //System.err.println(XMLValidator.checkXml(xml));



        // new DropDB("VideoDes2DB").execute(context);
        BaseXDriver.closeCollection(context);
        context.close();



    }

    static void query(final String query) throws BaseXException {
        System.out.println(new XQuery(query).execute(context));
    }

    static int iterate(final String query) throws QueryException {
        int max = 0;
        QueryProcessor proc = new QueryProcessor(query, context);
        Iter iter = proc.iter();
        for (Item item; (item = iter.next()) != null;) {
            if (item.toJava().toString().isEmpty()) {
                continue;
            } else {
                if (Integer.parseInt(item.toJava().toString()) > max) {
                    max = Integer.parseInt(item.toJava().toString());
                }
            }

        }

        proc.close();
        return max;
    }
}
