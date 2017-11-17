package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);
    }

    public void intervalQuiz(View v) {

        Intent launchEarTrainingQuiz = new Intent(this, EarTrainingQuizActivity.class);

        startActivity(launchEarTrainingQuiz);

    }
}
