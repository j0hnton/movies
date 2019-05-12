package com.moringaschool.showflix;

import android.content.Intent;
import android.os.Parcelable;
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
        EditText = (EditText) findViewById(R.id.nameEditText);
        button2 = (Button) findViewById(R.id.button2);

        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain3Activity();
            }
            public void openMain3Activity(){
                String Edit = EditText.getText().toString();
                Intent intent = new Intent(Main2Activity.this, Main3Activity.class);
                intent.putExtra("Edit",Edit);
                startActivity(intent);
            }
        });
    }
}