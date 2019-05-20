package com.moringaschool.showflix;

public class Movie {
    private String mName;
    private String mPopularity;
    private String mReleaseDate;
    private String mOverview;



    public Movie(String name,String popularity,
                String release_date, String overview) {
        this.mName = name;
        this.mPopularity = popularity;
        this.mReleaseDate = release_date;
        this.mOverview =overview;

    }

    public String getName() {
        return mName;
    }

    public String getPopularity() {
        return mPopularity;
    }


    public String getReleaseDate() {
        return mReleaseDate;
    }

    public String getOverview() {
        return mOverview;
    }

}