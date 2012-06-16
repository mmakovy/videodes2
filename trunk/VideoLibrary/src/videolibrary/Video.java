/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package videolibrary;

import java.util.List;
import java.util.Objects;

/**
 * Represents object Video
 *
 * @author Andrej
 */
public class Video {

    private int id;
    private String title;
    private List<String> directors;
    private List<String> actors;
    private int year;
    private List<Genre> genres;
    private List<String> countries;
    private int rating;

    public int getRating() {
        return rating;
    }

    public void setRating(int rating) {
        this.rating = rating;
    }

    public List<String> getActors() {
        return actors;
    }

    public void setActors(List<String> actors) {
        this.actors = actors;
    }

    public List<String> getCountries() {
        return countries;
    }

    public void setCountries(List<String> countries) {
        this.countries = countries;
    }

    public List<String> getDirectors() {
        return directors;
    }

    public void setDirectors(List<String> directors) {
        this.directors = directors;
    }

    public List<Genre> getGenres() {
        return genres;
    }

    public void setGenres(List<Genre> genres) {
        this.genres = genres;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Video other = (Video) obj;
        if (!Objects.equals(this.title, other.title)) {
            return false;
        }
        if (!Objects.equals(this.directors, other.directors)) {
            return false;
        }
        if (!Objects.equals(this.actors, other.actors)) {
            return false;
        }
        if (this.year != other.year) {
            return false;
        }
        if (!Objects.equals(this.genres, other.genres)) {
            return false;
        }
        if (!Objects.equals(this.countries, other.countries)) {
            return false;
        }
        if (this.rating != other.rating) {
            return false;
        }
        return true;
    }


    @Override
    public int hashCode() {
        int hash = 5;
        hash = 83 * hash + Objects.hashCode(this.title);
        hash = 83 * hash + Objects.hashCode(this.directors);
        hash = 83 * hash + Objects.hashCode(this.actors);
        hash = 83 * hash + this.year;
        hash = 83 * hash + Objects.hashCode(this.genres);
        hash = 83 * hash + Objects.hashCode(this.countries);
        hash = 83 * hash + this.rating;
        return hash;
    }


}
