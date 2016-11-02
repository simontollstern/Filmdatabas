/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package filmdatabas;

import javafx.beans.property.FloatProperty;
import javafx.beans.property.SimpleFloatProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 *
 * @author Simon Tollstern
 */
public class Movie {

    private final SimpleStringProperty title;
    private final SimpleStringProperty genre;
    private final SimpleStringProperty director;
    private final SimpleFloatProperty rating;
    private final SimpleFloatProperty imdb_rating;

    public Movie(String title, String genre, String director, float rating, float imdb_rating) {
        this.title = new SimpleStringProperty(title);
        this.genre = new SimpleStringProperty(genre);
        this.director = new SimpleStringProperty(director);
        this.rating = new SimpleFloatProperty(rating);
        this.imdb_rating = new SimpleFloatProperty(imdb_rating);
    }
    
    //GETTERS
    public String getTitle() {
        return title.get();
    }

    public String getGenre() {
        return genre.get();
    }

    public String getDirector() {
        return director.get();
    }

    public float getRating() {
        return rating.get();
    }

    public float getImdb_rating() {
        return imdb_rating.get();
    }
    
    //SETTERS
    public void setTitle(String value) {
        title.set(value);
    }

    public void setGenre(String value) {
        genre.set(value);
    }

    public void setDirector(String value) {
        director.set(value);
    }
    
    public void setRating(float value) {
        rating.set(value);
    }
    
    public void setimdb_rating(float value) {
        imdb_rating.set(value);
    }
    
    //STRING PROPERTIES
    public StringProperty titleProperty() {
        return title;
    }

    public StringProperty genreProperty() {
        return genre;
    }

    public StringProperty directorProperty() {
        return director;
    }
    
    public FloatProperty ratingProperty() {
        return rating;
    }

    public FloatProperty imdb_ratingProperty() {
        return imdb_rating;
    } 
}
