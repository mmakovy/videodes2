/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.item.Item;
import org.basex.query.iter.Iter;
import org.xml.sax.SAXException;

/**
 * ------------------------------------------------------------------- AK SA NIC
 * NENAJDE TAK NAMIESTO VIDEA/LISTU VYHADZOVAT NULL
 * -------------------------------------------------------------------
 *
 * @author Andrej
 */
public class VideoManagerImpl implements VideoManager {

    private static Context context = new Context();
    XMLValidator xmlValidator = new XMLValidator();

    @Override
    public boolean addVideo(Video video) {

        if (video == null) {
            throw new IllegalArgumentException("video is null");
        }
        if (video.getId() > 0) {
            throw new IllegalArgumentException("video is already in DB");
         }
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {

            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null, result2 = null;
        try {
            result = (new XQuery(
                    "for $doc in collection('VideoDes2DB')"
                    + "return $doc").execute(context));

        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

         if (result.length() < 1) {
              video.setId(1);
          } else {
                     String query =
                "for $doc in collection('VideoDes2DB')"
                + "return $doc//string(@id)";
            try {
                video.setId(iterate(query) + 1);
            } catch (QueryException ex) {
                Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
            }
         }
     


        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        String xml = x.toXML(video);
        try {
            if (XMLValidator.checkXml(xml)) {
        
                try {
                    new Add("video", xml).execute(context);
                    

                } catch (BaseXException ex) {

                    Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
                }
            } else { 
                return false;
            }
        } catch (SAXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return true;
    }

    @Override
    public List<Video> getAllVideos() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void deleteVideo(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByTitle(String title) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Video getVideoById(long id) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByYear(int year) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByDirector(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByActor(String name) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByGenre(Genre genre) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Video> getVideoByCountry(String country) {
        throw new UnsupportedOperationException("Not supported yet.");
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
