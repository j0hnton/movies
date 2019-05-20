package com.moringaschool.showflix.adapters;


import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.R;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MoviesListAdapter extends RecyclerView.Adapter<MoviesListAdapter.MovieViewHolder> {
    private ArrayList<Movie> mMovies = new ArrayList<>();
    private Context mContext;

    public MoviesListAdapter(Context context, ArrayList<Movie> movie) {
        mContext = context;
        mMovies = movie;
    }

    @Override
    public MoviesListAdapter.MovieViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_display, parent, false);
        MovieViewHolder viewHolder = new MovieViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(MoviesListAdapter.MovieViewHolder holder, int position) {
        holder.bindMovie(mMovies.get(position));
    }

    @Override
    public int getItemCount() {
        return mMovies.size();
    }

    public class MovieViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.movieImageView) ImageView mMovieImageView;
        @BindView(R.id.movieNameTextView) TextView mNameTextView;
        @BindView(R.id.overviewTextView) TextView mCategoryTextView;
        @BindView(R.id.dateTextView) TextView mRatingTextView;
        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }

        public void bindMovie(Movie movie) {
            mNameTextView.setText(movie.getName());
            mCategoryTextView.setText(movie.getOverview());
            mRatingTextView.setText("Rating: " + movie.getPopularity());
        }
    }
}