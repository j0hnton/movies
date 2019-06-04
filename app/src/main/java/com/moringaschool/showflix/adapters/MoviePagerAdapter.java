package com.moringaschool.showflix.adapters;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;


import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.ui.MovieDetailFragment;

import java.util.ArrayList;

public class MoviePagerAdapter extends FragmentPagerAdapter {
    private ArrayList<Movie> mMovie;

    public MoviePagerAdapter(FragmentManager fm, ArrayList<Movie> movies) {
        super(fm);
        mMovie =movies;
    }

    @Override
    public Fragment getItem(int position) {
        return  MovieDetailFragment.newInstance(mMovie.get(position));
    }

    @Override
    public int getCount() {
        return mMovie.size();
    }

    @Override
    public CharSequence getPageTitle(int position) {
        return mMovie.get(position).getName();
    }
}


