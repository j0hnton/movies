package com.moringaschool.showflix.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.moringaschool.showflix.Movie;
import com.moringaschool.showflix.R;
import com.moringaschool.showflix.SavedMovieListActivity;
import com.moringaschool.showflix.ui.MovieDetailActivity;
import com.moringaschool.showflix.util.ItemTouchHelperAdapter;
import com.moringaschool.showflix.util.OnStartDragListener;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.Collections;

import butterknife.Optional;

public class FirebaseMovieListAdapter extends FirebaseRecyclerAdapter<Movie, FirebaseMovieViewHolder> implements ItemTouchHelperAdapter {
    private DatabaseReference mRef;
    private OnStartDragListener mOnStartDragListener;
    private Context mContext;

    public FirebaseMovieListAdapter(FirebaseRecyclerOptions<Movie> options,
                                         DatabaseReference ref,
                                         OnStartDragListener onStartDragListener,
                                         Context context){
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;
    }

@Optional
    protected void onBindViewHolder(final FirebaseMovieViewHolder viewHolder, Movie model, int position) {
        viewHolder.bindMovie(model);
        viewHolder.mMovieImageView.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                if (event.getActionMasked() == MotionEvent.ACTION_DOWN) {
                    mOnStartDragListener.onStartDrag(viewHolder);
                }
                return false;
            }
        });
    }


    @Override
    protected void onBindViewHolder(@NonNull FirebaseMovieViewHolder firebaseRestaurantViewHolder, int position, @NonNull Movie movie) {
        firebaseRestaurantViewHolder.bindMovie(movie);
    }

    @NonNull
    @Override
    public FirebaseMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_drag, parent, false);
        return new FirebaseMovieViewHolder(view);
    }

    @Override
    public boolean onItemMove(int fromPosition, int toPosition){
        return false;
    }

    @Override
    public void onItemDismiss(int position){

    }
}