package com.moringaschool.showflix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.widget.Adapter;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;


import com.moringaschool.showflix.adapters.MoviesListAdapter;

import java.io.IOException;
import java.util.ArrayList;

;
import butterknife.BindView;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

    public class MoiveDisplay extends AppCompatActivity {
        private SharedPreferences mSharedPreferences;
        private String mRecentSearch;
        public static final String EXTRA_TEXT = "com.moringaschool.showflix.moringaschool.EXTRA_TEXT";

        public static final String TAG = MoiveDisplay.class.getSimpleName();

            @BindView(R.id.recyclerView)
            RecyclerView mRecyclerView;
private  MoviesListAdapter mAdapter;


            public ArrayList<Movie> mMovie = new ArrayList<>();

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.movie_display);
            ButterKnife.bind(this);


            Intent intent = getIntent();
            String search = intent.getStringExtra("search");

            TextView results = (TextView) findViewById(R.id.search);

            getMovie(search);
            mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
            mRecentSearch = mSharedPreferences.getString(Constants.PREFERENCES_SEARCH_KEY, null);
            if (mRecentSearch != null) {
                getMovie(mRecentSearch);
            }
        }
        private void getMovie(String search){
            final MovieService movieService=new MovieService();
            movieService.findMovie(search, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) {
                    mMovie = movieService.processResults(response);
                    MoiveDisplay.this.runOnUiThread(new Runnable() {

                        @Override

                            public void run(){
                            mAdapter= new MoviesListAdapter(getApplicationContext(), mMovie);
                            mRecyclerView.setAdapter(mAdapter);
                            RecyclerView.LayoutManager layoutManager =
                                    new LinearLayoutManager(MoiveDisplay.this);
                            mRecyclerView.setLayoutManager(layoutManager);
                            mRecyclerView.setHasFixedSize(true);

                        }
                    });
                }
            });
        }
    }
//
//        RecyclerView.LayoutManager layoutManager = new GridLayoutManager(getApplicationContext(),2);
//                            mRecyclerView.setLayoutManager(layoutManager);