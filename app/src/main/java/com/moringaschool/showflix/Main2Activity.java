package com.moringaschool.showflix;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.BindView;
import butterknife.ButterKnife;


public class Main2Activity<intent> extends AppCompatActivity {
    private Button button2;
    private EditText EditText;
public  static final String EXTRA_TEXT="com.moringaschool.showflix.moringaschool.EXTRA_TEXT";
    @BindView(R.id.signup)
    TextView mSign;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);
        ButterKnife.bind(this);



        button2 = (Button) findViewById(R.id.button3);
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openMain3Activity();
            }
        });
        mSign.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = (Intent) new Intent(Main2Activity.this, SignUp.class);
                startActivity(intent);
            }
        });
    }
    public void openMain3Activity(){

 EditText editText=(EditText) findViewById(R.id.nameEditText1);
 String search=editText.getText().toString();

        Toast.makeText(Main2Activity.this, "Saved Successfully", Toast.LENGTH_LONG).show();
        Intent intent= (Intent) new Intent(this, Main3Activity.class);

        intent.putExtra(EXTRA_TEXT,search);
        startActivity((Intent) intent) ;
    }


}
