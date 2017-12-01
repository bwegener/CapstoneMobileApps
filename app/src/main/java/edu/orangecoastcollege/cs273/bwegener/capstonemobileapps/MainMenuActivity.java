package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MainMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
    }

    public void earTrainingMenu(View v) {

        Intent launchEarTrainingMenu = new Intent(this, EarTrainingMenuActivity.class);

        startActivity(launchEarTrainingMenu);

    }

    public void sightSinging(View v)
    {


    }

    public void auditionRoom(View v) {


    }

    public void editProfile(View v) {

        Intent launchProfile = new Intent(this, ProfileActivity.class);
        startActivity(launchProfile);
    }
}
