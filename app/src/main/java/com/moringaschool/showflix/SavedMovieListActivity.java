package com.moringaschool.showflix;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.firebase.ui.database.FirebaseRecyclerAdapter;
import com.firebase.ui.database.FirebaseRecyclerOptions;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.moringaschool.showflix.adapters.FirebaseMovieListAdapter;
import com.moringaschool.showflix.adapters.FirebaseMovieViewHolder;
import com.moringaschool.showflix.util.OnStartDragListener;
import com.moringaschool.showflix.util.SimpleItemTouchHelperCallback;

import butterknife.BindView;
import butterknife.ButterKnife;

public class SavedMovieListActivity extends AppCompatActivity implements OnStartDragListener {

    private DatabaseReference mMovieReference;
    private FirebaseMovieListAdapter mFirebaseAdapter;
    private ItemTouchHelper mItemTouchHelper;
    @BindView(R.id.recyclerView) RecyclerView mRecycler;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.movie_display);
        ButterKnife.bind(this);

        mMovieReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIE);
        setUpFirebaseAdapter();
    }

    private void setUpFirebaseAdapter(){

        mMovieReference = FirebaseDatabase.getInstance().getReference(Constants.FIREBASE_CHILD_MOVIE);
        Query query = FirebaseDatabase.getInstance()
                .getReference(Constants.FIREBASE_CHILD_MOVIE)

                .orderByChild(Constants.FIREBASE_QUERY_INDEX);

        FirebaseRecyclerOptions<Movie> options =
                new FirebaseRecyclerOptions.Builder<Movie>()
                        .setQuery(mMovieReference, Movie.class)
                        .build();

        mFirebaseAdapter = new FirebaseMovieListAdapter(options, query, this, this);

        mFirebaseAdapter = new FirebaseMovieListAdapter(options, mMovieReference, this, this);

        mRecycler.setLayoutManager(new LinearLayoutManager (this));
        mRecycler.setAdapter(mFirebaseAdapter);
        ItemTouchHelper.Callback callback = new SimpleItemTouchHelperCallback
                (mFirebaseAdapter);
        mItemTouchHelper = new ItemTouchHelper(callback);
        mItemTouchHelper.attachToRecyclerView(mRecycler);
    }

    @Override
    protected void onStart() {
        super.onStart();
        mFirebaseAdapter.startListening();
    }

    @Override
    protected void onStop() {
        super.onStop();
        if(mFirebaseAdapter!= null) {
            mFirebaseAdapter.stopListening();
        }
    }

    @Override
    public void onStartDrag(RecyclerView.ViewHolder viewHolder) {
        mItemTouchHelper.startDrag(viewHolder);

    }
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mFirebaseAdapter.stopListening(); } }