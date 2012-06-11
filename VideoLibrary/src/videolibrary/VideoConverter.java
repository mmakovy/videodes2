/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import com.thoughtworks.xstream.converters.Converter;
import com.thoughtworks.xstream.converters.MarshallingContext;
import com.thoughtworks.xstream.converters.UnmarshallingContext;
import com.thoughtworks.xstream.io.HierarchicalStreamReader;
import com.thoughtworks.xstream.io.HierarchicalStreamWriter;
import java.util.ArrayList;

/**
 *
 * @author AjkoST
 */
public class VideoConverter implements Converter {

    @Override
    public boolean canConvert(Class clazz) {
        return clazz.equals(Video.class);
    }

    @Override
    public void marshal(Object value, HierarchicalStreamWriter writer,
            MarshallingContext context) {
        Video video = (Video) value;
        writer.addAttribute("id", Integer.toString(video.getId()));
        writer.startNode("title");
        writer.setValue(video.getTitle());
        writer.endNode();
        writer.startNode("directors");

        for (String director : video.getDirectors()) {
            writer.startNode("director");
            writer.setValue(director);
            writer.endNode();
        }

        writer.endNode();
        writer.startNode("actors");

        for (String actor : video.getActors()) {
            writer.startNode("actor");
            writer.setValue(actor);
            writer.endNode();
        }
        writer.endNode();

        writer.startNode("year");
        writer.setValue(Integer.toString(video.getYear()));
        writer.endNode();

        writer.startNode("countries");
        for (String country : video.getCountries()) {
            writer.startNode("country");
            writer.setValue(country);
            writer.endNode();
        }
        writer.endNode();
        writer.startNode("genres");
        for (Genre genre : video.getGenres()) {
            writer.startNode("genre");
            writer.setValue(genre.toString());
            writer.endNode();
        }
        writer.endNode();

        writer.startNode("rating");
        writer.setValue(Integer.toString(video.getRating()));
        writer.endNode();


    }

    @Override
    public Object unmarshal(HierarchicalStreamReader reader,
            UnmarshallingContext context) {
        Video video = new Video();
        video.setId(Integer.parseInt(reader.getAttribute("id")));

        reader.moveDown();
        video.setTitle(reader.getValue());
        reader.moveUp();

        reader.moveDown();
        ArrayList<String> directors = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            directors.add(reader.getValue());
            reader.moveUp();
        }
        video.setDirectors(directors);
        reader.moveUp();

        reader.moveDown();
        ArrayList<String> actors = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            actors.add(reader.getValue());
            reader.moveUp();
        }
        video.setActors(actors);
        reader.moveUp();

        reader.moveDown();
        video.setYear(Integer.parseInt(reader.getValue()));
        reader.moveUp();

        reader.moveDown();
        ArrayList<String> countries = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            countries.add(reader.getValue());
            reader.moveUp();
        }
        video.setCountries(countries);
        reader.moveUp();


        reader.moveDown();
        ArrayList<Genre> genres = new ArrayList<>();
        while (reader.hasMoreChildren()) {
            reader.moveDown();
            switch (reader.getValue()) {
                case "ACTION":
                    genres.add(Genre.ACTION);
                    break;
                case "ADVENTURE":
                    genres.add(Genre.ADVENTURE);
                    break;
                case "ANIMATION":
                    genres.add(Genre.ANIMATION);
                    break;
                case "BIOGRAPHY":
                    genres.add(Genre.BIOGRAPHY);
                    break;
                case "COMEDY":
                    genres.add(Genre.COMEDY);
                    break;
                case "CRIME":
                    genres.add(Genre.CRIME);
                    break;
                case "DOCUMENTARY":
                    genres.add(Genre.DOCUMENTARY);
                    break;
                case "DRAMA":
                    genres.add(Genre.DRAMA);
                    break;
                case "FAMILY":
                    genres.add(Genre.FAMILY);
                    break;
                case "FANTASY":
                    genres.add(Genre.FANTASY);
                    break;
                case "HISTORY":
                    genres.add(Genre.HISTORY);
                    break;
                case "HORROR":
                    genres.add(Genre.HORROR);
                    break;
                case "MUSIC":
                    genres.add(Genre.MUSIC);
                    break;
                case "MUSICAL":
                    genres.add(Genre.MUSICAL);
                    break;
                case "MYSTERY":
                    genres.add(Genre.MYSTERY);
                    break;
                case "ROMANCE":
                    genres.add(Genre.ROMANCE);
                    break;
                case "SCIENCE_FICTION":
                    genres.add(Genre.SCIENCE_FICTION);
                    break;
                case "SPORT":
                    genres.add(Genre.SPORT);
                    break;
                case "THRILLER":
                    genres.add(Genre.THRILLER);
                    break;
                case "WAR":
                    genres.add(Genre.WAR);
                    break;
                case "WESTERN":
                    genres.add(Genre.WESTERN);
                    break;
                default:
                    genres.add(Genre.ACTION);
                    break;
            }

            reader.moveUp();
        }
        video.setGenres(genres);
        reader.moveUp();

        reader.moveDown();
        video.setRating(Integer.parseInt(reader.getValue()));
        reader.moveUp();

        return video;
    }
}
