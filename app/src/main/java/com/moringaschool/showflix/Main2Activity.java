package com.moringaschool.showflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;


public class Main2Activity<intent> extends AppCompatActivity {
    private Button button2;
    private EditText EditText;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        button2 = (Button) findViewById(R.id.button2);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain3Activity();
            }
        });
    }
    public void openMain3Activity(){

        Toast.makeText(Main2Activity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
        intent intent= (intent) new Intent(this, Main3Activity.class);

        startActivity((Intent) intent) ;
    }
}
