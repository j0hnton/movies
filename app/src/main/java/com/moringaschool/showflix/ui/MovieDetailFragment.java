package com.moringaschool.showflix.ui;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.moringaschool.showflix.Constants;
import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.R;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;
import butterknife.BindView;
import butterknife.ButterKnife;

import static android.widget.Toast.LENGTH_SHORT;

public class MovieDetailFragment extends Fragment implements  View.OnClickListener {
    @BindView(R.id.wishlist) ImageView mImageView;
    @BindView(R.id.movieImageView) ImageView mMovieImageView;
    @BindView(R.id.movieNameTextView) TextView mNameTextView;
    @BindView(R.id.overviewTextView) TextView mCategoryTextView;



      private Movie mMovie;
public  MovieDetailFragment(){};
    public static MovieDetailFragment newInstance(Movie movie) {
        MovieDetailFragment movieDetailFragment = new MovieDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("movie", Parcels.wrap(movie));
        movieDetailFragment.setArguments(args);
        return movieDetailFragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mMovie = Parcels.unwrap(getArguments().getParcelable("movie"));

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_movie_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load("https://image.tmdb.org/t/p/w500"+mMovie.getImage()).into(mMovieImageView);
        System.out.println("https://api.themoviedb.org"+mMovie.getImage());
        mNameTextView.setText(mMovie.getName());
        mImageView.setOnClickListener(this);
        mCategoryTextView.setText(mMovie.getOverview());

        return view;
    }

    @Override
    public void onClick(View v) {
        if (v == mImageView) {
                  }
        if (v == mImageView) {
            DatabaseReference movieref = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_MOVIE);
            movieref.push().setValue(mMovie);
            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }

    }
}
