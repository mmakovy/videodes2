/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.DomDriver;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.basex.core.BaseXException;
import org.basex.core.Context;
import org.basex.core.cmd.Add;
import org.basex.core.cmd.Delete;
import org.basex.core.cmd.XQuery;
import org.basex.query.QueryException;
import org.basex.query.QueryProcessor;
import org.basex.query.item.Item;
import org.basex.query.iter.Iter;
import org.xml.sax.SAXException;

/**
 * @author Andrej
 */
public class VideoManagerImpl implements VideoManager {

    private static Context context = new Context();
    XMLValidator xmlValidator = new XMLValidator();

    public boolean emptyDB() {
        String test = null;
        try {
            BaseXDriver.openCollection(context);
            test = (new XQuery(
                    "for $video in (collection('VideoDes2DB')/video[1])"
                    + "return <doc path='{ base-uri($video) }'/>").execute(context));
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        context.close();
        return test.equals("");
    }

    public static List removeDuplicates(List list) {
        for (int i = 0; i < list.size(); i++) {
                for (int j = 0; j < list.size(); j++) {
                    if (i == j) {
                    } else if (list.get(j).equals(list.get(i))) {
                        list.remove(j);
                    }
                }
        }
        return Collections.unmodifiableList(list);
    }
    
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
        int id = 0;
        if (result.length() < 1) {
            id = 1;
            video.setId(id);
        } else {
            String query =
                    "for $doc in collection('VideoDes2DB')"
                    + "return $doc//string(@id)";
            try {
                id = iterate(query) + 1;
                video.setId(id);
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
                    new Add(id + ".xml", xml).execute(context);
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
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();

        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String result = null;
        try {
            result = (new XQuery(
                    "for $doc in collection('VideoDes2DB')"
                    + "return $doc").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(temp);
    }

    @Override
    public void deleteVideo(int id) {
        if (id <= 0) {
            throw new IllegalArgumentException("id lower than 1");
        }

        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            new Delete(id + ".xml").execute(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
    }

    @Override
    public List<Video> getVideoByTitle(String title) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        String lowerTitle = title.toLowerCase();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String result = null;
        try {
            result = (new XQuery(
                    "for $doc in collection('VideoDes2DB')"
                    + "let $lower_title := lower-case($doc//video/title)"
                    + "where contains($lower_title,'"
                    + lowerTitle
                    + "')"
                    + "return $doc").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(removeDuplicates(temp));
    }

    @Override
    public Video getVideoById(int id) {
        if (emptyDB()) {
            return null;
        }
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String result = null;
        try {
            result = (new XQuery(
                    "for $doc in collection('VideoDes2DB')"
                    + "where $doc//video/@id='"
                    + id
                    + "'"
                    + "return $doc").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        if (result.equals("")) {
            return null;
        }

        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);



        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return (Video) x.fromXML(result);
    }

    @Override
    public List<Video> getVideoByYear(int year) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null;
        try {
            result = (new XQuery(
                    "for $doc in collection('VideoDes2DB')"
                    + "where $doc//video/year='"
                    + year
                    + "'"
                    + "return $doc").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(temp);
    }

    @Override
    public List<Video> getVideoByDirector(String name) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        String lowerName = name.toLowerCase();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        String result = null;

        try {
            result = (new XQuery("for $video in (collection('VideoDes2DB')//video) return (for $director in $video/directors/director let $lower_name := lower-case($director) where contains($lower_name,'" + lowerName + "') return $video)").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(removeDuplicates(temp));
    }

    @Override
    public List<Video> getVideoByActor(String name) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        String lowerName = name.toLowerCase();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null;

        try {
            result = (new XQuery("for $video in (collection('VideoDes2DB')//video) return (for $actor in $video/actors/actor let $lower_name := lower-case($actor) where contains($lower_name,'" + lowerName + "') return $video)").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(removeDuplicates(temp));
    }

    @Override
    public List<Video> getVideoByGenre(Genre genre) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null;

        try {
            result = (new XQuery("for $video in (collection('VideoDes2DB')//video) return (for $genre in $video/genres/genre where $genre = '" + genre + "' return $video)").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(temp);
    }

    @Override
    public List<Video> getVideoByCountry(String country) {
        if (emptyDB()) {
            return null;
        }
        List<Video> temp = new ArrayList<Video>();
        String lowerName = country.toLowerCase();
        try {
            BaseXDriver.openCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        String result = null;

        try {
            result = (new XQuery("for $video in (collection('VideoDes2DB')//video) return (for $country in $video/countries/country let $lower_name := lower-case($country) where contains($lower_name,'" + lowerName + "') return $video)").execute(context));
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }
        if (result.equals("")) {
            return null;
        }
        String[] videos = result.split("</video>");
        XStream x = new XStream(new DomDriver());
        x.registerConverter(new VideoConverter());
        x.alias("video", Video.class);
        for (int i = 0; i < videos.length; i++) {
            videos[i] = videos[i] + "</video>";
            Video tempVid = (Video) x.fromXML(videos[i]);
            temp.add(tempVid);
        }

        try {
            BaseXDriver.closeCollection(context);
        } catch (BaseXException ex) {
            Logger.getLogger(VideoManagerImpl.class.getName()).log(Level.SEVERE, null, ex);
        }

        context.close();
        return Collections.unmodifiableList(removeDuplicates(temp));
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
