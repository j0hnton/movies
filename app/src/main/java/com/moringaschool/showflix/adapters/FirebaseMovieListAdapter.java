package com.moringaschool.showflix.adapters;

import android.content.Context;
import android.content.Intent;
import android.support.annotation.NonNull;
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
    private ChildEventListener mChildEventListener;
    private ArrayList<Movie> mMovies = new ArrayList<>();

    public FirebaseMovieListAdapter(FirebaseRecyclerOptions<Movie> options,
                                    Query ref,
                                    SavedMovieListActivity  onStartDragListener, Context context) {
        super(options);
        mRef = ref.getRef();
        mOnStartDragListener = onStartDragListener;
        mContext = context;

        mChildEventListener = mRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot dataSnapshot, String s) {
                mMovies.add(dataSnapshot.getValue(Movie.class));
            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }

        });

    }

        @Override
        public boolean onItemMove(int fromPosition, int toPosition) {
            Collections.swap(mMovies, fromPosition, toPosition);
            notifyItemMoved(fromPosition, toPosition);
            return false;
        }

        @Override
        public void onItemDismiss(int position) {
            mMovies.remove(position);
            getRef(position).removeValue();
        }

    @Override
        protected void onBindViewHolder(@NonNull final FirebaseMovieViewHolder viewHolder, int position, @NonNull Movie model) {
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

            viewHolder.itemView.setOnClickListener(new View.OnClickListener() {

                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mContext, MovieDetailActivity.class);
                    intent.putExtra("position", viewHolder.getAdapterPosition());
                    intent.putExtra("restaurants", Parcels.wrap(mMovies));
                    mContext.startActivity(intent);
                }
            });

        }

        @NonNull
        @Override
        public FirebaseMovieViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
            View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.movie_list_item_drag, parent, false);
            return new FirebaseMovieViewHolder(view);
        }

        private void setIndexInFirebase() {
            for (Movie movie : mMovies) {
                int index = mMovies.indexOf(movie);
                DatabaseReference ref = getRef(index);
                movie.setIndex(Integer.toString(index));
                ref.setValue(movie);
            }
        }

        @Override
        public void stopListening() { super.stopListening(); mRef.removeEventListener(mChildEventListener); }}