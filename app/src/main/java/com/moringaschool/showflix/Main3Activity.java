package com.moringaschool.showflix;

import android.content.Intent;
import android.content.SharedPreferences;
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

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity implements View.OnClickListener {

    //    private SharedPreferences mSharedPreferences;
//    private SharedPreferences.Editor mEditor;
    private DatabaseReference mSearchedMovieReference;
    private ValueEventListener mSearchedMovieReferenceListener;
    @BindView(R.id.user2)
    ImageView mUser2;
    @BindView(R.id.user)
    ImageView mUser;
    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.searchButton)
    ImageView mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        mSearchedMovieReference = FirebaseDatabase
                .getInstance()
                .getReference()
                .child(Constants.FIREBASE_CHILD_SEARCHED_MOVIE);

        mSearchedMovieReference.addValueEventListener(new ValueEventListener


        (){
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

        Intent intent = getIntent();

        String text = intent.getStringExtra(Main2Activity.EXTRA_TEXT);
        TextView textView1 = (TextView) findViewById(R.id.textView1);
        textView1.setText(text);
        ButterKnife.bind(this);

        mUser2.setOnClickListener(Main3Activity.this);

//        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
//        mEditor = mSharedPreferences.edit();
        mButton.setOnClickListener(this);
    }


    @Override
    public void onClick(View v) {
        if (v == mButton) {

            String search = mSearch.getText().toString();
            saveMovieToFirebase(search);
//                if(!(search).equals("")){
//                addToSharedPreferences(search);
//            }
            Toast.makeText(Main3Activity.this, "Searching...", Toast.LENGTH_LONG).show();
            Intent intent = (Intent) new Intent(Main3Activity.this, MoiveDisplay.class);
            intent.putExtra("search", search);
            startActivity(intent);
        }
        if (v == mUser2) {
            Intent intent = new Intent(Main3Activity.this, SavedMovieListActivity.class);
            startActivity(intent);
        }

    };
    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSearchedMovieReference.removeEventListener(mSearchedMovieReferenceListener);
    }


    public void saveMovieToFirebase(String search) {
        mSearchedMovieReference.setValue(search);
    }
}
////    private void addToSharedPreferences(String search) {
////        mEditor.putString(Constants.PREFERENCES_SEARCH_KEY, search).apply();
//        mUser.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//
//
//                Intent intent = (Intent) new Intent(Main3Activity.this, Main2Activity.class);
//                startActivity(intent);
//            }
//        });
//
//    }
//}


