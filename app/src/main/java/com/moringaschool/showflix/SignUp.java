package com.moringaschool.showflix;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SignUp<intent> extends AppCompatActivity {
    private Button buttonnew;
    private android.widget.EditText EditText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.signup_activity);

        buttonnew = (Button) findViewById(R.id.buttonnew);
        buttonnew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain2Activity();
            }
        });
    }
    public void openMain2Activity(){

        EditText editText=(EditText) findViewById(R.id.nameEditTextnew);
        String search=editText.getText().toString();

        Toast.makeText(SignUp.this, "Saved Successfully", Toast.LENGTH_LONG).show();
        Intent intent= (Intent) new Intent(this, Main2Activity.class);


        startActivity((Intent) intent) ;
    }
}