package com.moringaschool.showflix.adapters;


import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.R;
import com.moringaschool.showflix.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item, parent, false);
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

    public class MovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.movieImageView) ImageView mMovieImageView;
        @BindView(R.id.movieNameTextView) TextView mNameTextView;
        @BindView(R.id.overviewTextView) TextView mCategoryTextView;
        @BindView(R.id.rateTextVIew) TextView mRatingTextView;
        @BindView(R.id.release) TextView mRelease;


        private Context mContext;

        public MovieViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, MovieDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            mContext.startActivity(intent);


        }

        public void bindMovie(Movie movie) {
            Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getImage()).into(mMovieImageView);
            System.out.println("https://api.themoviedb.org"+movie.getImage());
            mNameTextView.setText(movie.getName());
            mRatingTextView.setText("Rating"+":"+" "+movie.getRate());
            mRelease.setText("Release Date:"+" "+movie.getReleaseDate());
            mCategoryTextView.setText(movie.getOverview());

        }
    }
}