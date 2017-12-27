package com.example.crack_jack.movielistingapp;

/**
 * Created by CRACK-JACK on 07-12-2017.
 */

public class MovieModel {

    String movieName, releaseDate, ImageURI, rating;

    public MovieModel() {

    }

    public MovieModel(String movieName, String releaseDate, String ImageURI, String rating) {

        this.movieName = movieName;
        this.releaseDate = releaseDate;
        this.ImageURI = ImageURI;
        this.rating = rating;

    }
}
