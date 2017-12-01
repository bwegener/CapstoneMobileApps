package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import java.util.Timer;
import java.util.TimerTask;

public class SplashActivity extends AppCompatActivity {

    private Animation alphaAnim;

    private TextView audiateTV;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_splash);

        audiateTV = (TextView) findViewById(R.id.audiateTV);

        RunAnimation();


        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                finish();

                Intent logInIntent = new Intent(SplashActivity.this, LogInActivity.class);

                startActivity(logInIntent);

            }
        };

        Timer timer = new Timer();
        timer.schedule(task, 2500);
    }

    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.anim);
        a.reset();
        audiateTV.clearAnimation();
        audiateTV.startAnimation(a);
    }
}
