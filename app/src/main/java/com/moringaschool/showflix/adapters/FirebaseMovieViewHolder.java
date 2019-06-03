package com.moringaschool.showflix.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.moringaschool.showflix.Constants;
import com.moringaschool.showflix.MoiveDisplay;
import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.R;
import com.moringaschool.showflix.SavedMovieListActivity;
import com.moringaschool.showflix.ui.MovieDetailActivity;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;

public class FirebaseMovieViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

    View mView;
    Context mContext;
    public ImageView mMovieImageView;
    public FirebaseMovieViewHolder(View itemView) {
        super(itemView);
        mView = itemView;
        mContext = itemView.getContext();
        itemView.setOnClickListener(this);
    }

    public void bindMovie(Movie movie) {
        mMovieImageView = (ImageView) mView.findViewById(R.id.movieImageView);
        TextView nameTextView =  mView.findViewById(R.id.movieNameTextView);
        nameTextView.setText(movie.getName());
        ImageView movieImageView = mView.findViewById(R.id.movieImageView);
        Picasso.get().load("https://image.tmdb.org/t/p/w500"+movie.getImage()).into(movieImageView);

        TextView rateTextView = mView.findViewById(R.id.rateTextVIew);
        rateTextView.setText("Rating: " + movie.getRate());

        TextView overviewTextView =  mView.findViewById(R.id.overviewTextView);

        overviewTextView.setText( movie.getOverview());

        Picasso.get().load(movie.getImage()).into(mMovieImageView);
    }

    @Override
    public void onClick(View view) {
        final ArrayList<Movie> movieArrayList = new ArrayList<>();
        DatabaseReference ref = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIE);
        ref.addListenerForSingleValueEvent(new ValueEventListener() {

            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    movieArrayList.add(snapshot.getValue(Movie.class));
                }

                int itemPosition = getLayoutPosition();

                Intent intent = new Intent(mContext, MovieDetailActivity.class);
                intent.putExtra("position", itemPosition + "");
                intent.putExtra("movies", Parcels.wrap(movieArrayList));

                mContext.startActivity(intent);
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }
}
