package com.moringaschool.showflix;

import android.content.Intent;
import android.content.SharedPreferences;
import android.net.Uri;
import android.os.Handler;
import android.preference.PreferenceManager;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import org.parceler.Parcel;
import org.parceler.Parcels;

import java.util.ArrayList;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

        private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedMovieReference;
    private ValueEventListener mSearchedMovieReferenceListener;
    private ArrayList<Movie> mMovies = new ArrayList<>();

    @BindView(R.id.after)
    ImageView after;
    @BindView(R.id.toy)
    ImageView toy;
    @BindView(R.id.user2)
    ImageView mUser2;
    @BindView(R.id.play)
    ImageView play;
    @BindView(R.id.user)
    ImageView mUser;
    @BindView(R.id.searchButton)
    ImageView mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedMovieReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_MOVIE);


        mSearchedMovieReference.addValueEventListener(new ValueEventListener(){
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) { //something changed!
                for (DataSnapshot searchSnapshot : dataSnapshot.getChildren()) {
                    String search  = searchSnapshot.getValue().toString();
                    Log.d("Search updated", "search: " + search);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_main3);

        ButterKnife.bind(this);


play.setOnClickListener(Main3Activity.this);
        mUser2.setOnClickListener(Main3Activity.this);
mUser.setOnClickListener(this);
        mButton.setOnClickListener(this);
        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();
        after.setOnClickListener(this);
        toy.setOnClickListener(this);

    }


    @Override
    public void onClick(View v) {
        if (v == after) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=BNLta4nDM10"));
            startActivity(intent);

        }
        if (v == toy) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=wmiIUN-7qhE"));
            startActivity(intent);

        }


        if (v == mUser) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("http://www8.fmovies.to/home"));
            startActivity(intent);

        }

        if (v == mUser2) {
            Intent intent = new Intent(Main3Activity.this, SavedMovieListActivity.class);
            intent.putExtra("movies", Parcels.wrap(mMovies));
            startActivity(intent);
        }

        if (v == mButton) {
            Intent intent = new Intent(Main3Activity.this, MoiveDisplay.class);
            startActivity(intent);
        }

        if (v == play) {
            Intent intent = new Intent(Intent.ACTION_VIEW, Uri.parse("https://www.youtube.com/watch?v=Nt9L1jCKGnE"));
            startActivity(intent);
        }

    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
//        mSearchedMovieReference.removeEventListener(mSearchedMovieReferenceListener);
    }


    boolean doubleBackToExitPressedOnce = false;

    @Override
    public void onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed();
            return;
        }

        this.doubleBackToExitPressedOnce = true;
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show();

        new Handler().postDelayed(new Runnable() {

            @Override
            public void run() {
                doubleBackToExitPressedOnce=false;
            }
        }, 2000);
    }
    public void saveMovieToFirebase(String search) {
        mSearchedMovieReference.setValue(search);
    }

    private void addToSharedPreferences(String search) {
        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, search).apply();
//        mUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = (Intent) new Intent(Main3Activity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        });

    }
}


