package com.moringaschool.showflix;

import org.parceler.Parcel;

import java.util.ArrayList;

@Parcel
public class Movie {
    private String mName;
    private String mReleaseDate;
    private String mOverview;
    private  String mImage;
    private  String mRate;

    public Movie() {}

    public Movie(String name,
                String release_date, String overview, String poster_path, String popularity) {
        this.mName = name;
        this.mReleaseDate = release_date;
        this.mOverview =overview;
        this.mImage=poster_path;
this.mRate=popularity;
    }

    public String getName() {
        return mName;
    }



    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

  public String getImage(){
    return mImage;
    }
    public String getRate(){
        return mRate;
    }
}