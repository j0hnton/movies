package com.moringaschool.showflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.widget.Button;
import android.widget.TextView;


import java.io.IOException;

;
import butterknife.ButterKnife;
import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.Response;

    public class MoiveDisplay extends AppCompatActivity {
        public static final String TAG = com.moringaschool.showflix.Main3Activity.class.getSimpleName();
        public static final String EXTRA_TEXT = "com.moringaschool.showflix.moringaschool.EXTRA_TEXT";


        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.movie_display);
            Intent intent = getIntent();
            String search = intent.getStringExtra("search");
            TextView results = (TextView) findViewById(R.id.searchDisplay);

            results.setText ("Search Results for " + " " + search);
              getMovie(search);

        }
        private void getMovie(String search){
            final MovieService movieService=new MovieService();
            movieService.findMovie(search, new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    e.printStackTrace();
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    try{
                        String jsonData=response.body().string();
                        Log.v (TAG, jsonData);
                    }catch (IOException e){
                        e.printStackTrace();
                    }

                }
            });
        }

    }


