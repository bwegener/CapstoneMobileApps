package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class NewUserActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_new_user);
    }

    public void createUser(View v)
    {
        Intent launchMainMenu = new Intent(this, MainMenuActivity.class);

        startActivity(launchMainMenu);
    }

    public void cancel(View v)
    {
        Intent launchLogIn = new Intent(this, LogInActivity.class);

        startActivity(launchLogIn);
    }
}
