package com.moringaschool.showflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

@BindView(R.id.user)
ImageView mUser;
    @BindView(R.id.movetoperson)
    ImageView mPerson;
    @BindView(R.id.movetomovie)
    ImageView mMove;
    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.searchButton)
    ImageView mButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);
        Intent intent = getIntent();
//        String text = intent.getStringExtra(Main2Activity.EXTRA_TEXT);
//        TextView textView1 = (TextView) findViewById(R.id.textView1);
//        textView1.setText("Username" + ":" + "" + text);
        ButterKnife.bind(this);


        mButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String search = mSearch.getText().toString();
                Intent intent = (Intent) new Intent(Main3Activity.this, MoiveDisplay.class);
                intent.putExtra("search", search);
                startActivity(intent);
            }
        });
        mMove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (Intent) new Intent(Main3Activity.this, MoiveDisplay.class);
                startActivity(intent);
            }
        });
        mPerson.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (Intent) new Intent(Main3Activity.this, MoiveDisplay.class);
                startActivity(intent);
            }
        });
        mUser.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (Intent) new Intent(Main3Activity.this, Main2Activity.class);
                startActivity(intent);
            }
        });

    }
}


