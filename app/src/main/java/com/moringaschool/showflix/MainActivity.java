package com.moringaschool.showflix;
        import android.content.Intent;
        import android.media.MediaPlayer;
        import android.os.Bundle;
        import android.os.Handler;
        import android.support.v7.app.AppCompatActivity;
        import android.widget.TextView;

        import butterknife.BindView;
        import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {
    @BindView(R.id.appTitle) TextView appTitle;
MediaPlayer mediaPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mediaPlayer=MediaPlayer.create(getApplicationContext(),R.raw.audio);
        mediaPlayer.start();

        new Handler().postDelayed(
                new Runnable() {
                    @Override
                    public void run() {

                        Intent intent = new Intent(getApplicationContext(),Main3Activity.class);
                        startActivity(intent);


                        finish();

                    }
                },
                5130);




    }
}
