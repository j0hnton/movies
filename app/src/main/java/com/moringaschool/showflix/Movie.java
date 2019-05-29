package com.moringaschool.showflix;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Movie {
    public Movie(String name, String releaseDate, String overview, String image, String rate) {
        this.name = name;
        this.releaseDate = releaseDate;
        this.overview = overview;
        this.image = image;
        this.rate = rate;
    }

    String name;
    String releaseDate;
    String overview;
    String image;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getReleaseDate() {
        return releaseDate;
    }

    public void setReleaseDate(String releaseDate) {
        this.releaseDate = releaseDate;
    }

    public String getOverview() {
        return overview;
    }

    public void setOverview(String overview) {
        this.overview = overview;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public String getRate() {
        return rate;
    }

    public void setRate(String rate) {
        this.rate = rate;
    }

    String rate;

    public Movie() {}



}