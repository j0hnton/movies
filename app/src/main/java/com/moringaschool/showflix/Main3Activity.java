package com.moringaschool.showflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;

public class Main3Activity extends AppCompatActivity {

    @BindView(R.id.search)
    EditText mSearch;
    @BindView(R.id.searchButton)
    Button mButton;

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
    }
}


