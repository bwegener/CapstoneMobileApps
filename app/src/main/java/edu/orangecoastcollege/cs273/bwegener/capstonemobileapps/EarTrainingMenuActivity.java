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

        // This should create the return button
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    public void intervalQuiz(View v)
    {

        Intent launchIntervalList = new Intent(this, IntervalListActivity.class);

        startActivity(launchIntervalList);
    }

    public void chordQuiz(View v)
    {

        Intent launchChordList = new Intent(this, ChordListActivity.class);

        startActivity(launchChordList);

    }

    public void modalityQuiz(View v)
    {

        Intent launchModalityList = new Intent(this, ModalityListActivity.class);

        startActivity(launchModalityList);

    }



}
