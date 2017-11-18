package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class EarTrainingMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ear_training_menu);
    }


    public void earTrainingQuiz(View v) {

        Intent launchEarTrainingQuiz = new Intent(this, EarTrainingQuizActivity.class);

        startActivity(launchEarTrainingQuiz);
    }

    public void returnToMain(View v)
    {
        Intent launchMainMenu = new Intent(this, MainMenuActivity.class);

        startActivity(launchMainMenu);
    }
}
