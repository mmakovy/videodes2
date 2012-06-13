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
        
          String a = null; try { a = new
          org.basex.core.cmd.List().execute(context); } catch (BaseXException
          ex) { Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE,
          null, ex); } if (!a.contains("VideoDes2DB")) { try {
          BaseXDriver.createCollection(context);
         
          } catch (BaseXException ex) {
          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
          ex); } finally { try { BaseXDriver.closeCollection(context); } catch
          (BaseXException ex) {
          Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null,
          ex); } }
         
          }
         
        
        
        //*******************************************//
        //IMDB DOWNLOADER, STACI NAPISAT NAZOV FILMU//
        //*****************************************// 
          
        ImdbDownloaderImpl imdb = new ImdbDownloaderImpl();
        for (int i = 75000; i < 100000; i++) {
           System.err.println("tt0"+i);
            imdb.downloadViaID("tt00"+i);
        }
        
        //***********************//
        //RUCNE VYTVARANIE VIDEA//
        //*********************//        
        /*
        VideoManagerImpl manager = new VideoManagerImpl();
        Video video = new Video();
        video.setTitle("Titulok filmu22");
        video.setYear(2002);
        ArrayList<String> r1 = new ArrayList<String>();
        r1.add("prv2321a");
        r1.add("dru123he");
        r1.add("tret123i");
        video.setActors(r1);
        video.setCountries(r1);
        video.setDirectors(r1);
        ArrayList<Genre> r2 = new ArrayList<Genre>();
        r2.add(Genre.WAR);
        r2.add(Genre.WESTERN);
        video.setGenres(r2);
        video.setRating(5);
        manager.addVideo(video);
*/

        //********************//
        //VYHODNOCOVAC XQUERY//
        //******************//  
        //teraz vracia celu DB

        BaseXDriver.openCollection(context);
        //String result = null;
        //String result2 = null;
        //result = (new XQuery(
        //        "for $doc in collection('VideoDes2DB')"
        //        + "return $doc").execute(context));
        //System.out.println(result);

        

        //****************************//
        //PREVOD OBJEKTU VIDEO DO XML//
        //**************************// 
        //XStream x = new XStream(new DomDriver());
        //x.registerConverter(new VideoConverter());
        //x.omitField(Video.class, "id");
        //x.alias("video", Video.class);
        //String xml = x.toXML(video);
        //System.err.println(XMLValidator.checkXml(xml));
        //System.err.println(xml);
        
        //String[] videos = result.split("</video>");
        //videos[1] = videos[1] + "</video>";
        //System.out.println(videos[1]);
        //for (int i=0; i < videos.length; i++) {
        //    videos[i] = videos[i] + "</video>";
        //    Video tempVid = (Video)x.fromXML(videos[i]);
        //    System.out.println(tempVid.getTitle());
        //}
        //System.out.println(new InfoDB().info());
        //new DropDB("VideoDes2DB").execute(context);
        
        //String test = (new XQuery(
        //"for $video in (collection('VideoDes2DB')/video)" +
        //"return <doc path='{ base-uri($video) }'/>"
    //).execute(context));
        
        //System.out.println(test);
        //String abc[] = (new List().execute(context)).split("VideoDes2DB");
        //String def[] = abc[1].split(" ");
        //System.out.println("0:" +def[2]);
        
        
        //System.out.print(new List().execute(context));
        //System.out.print(new InfoDB().execute(context));
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
