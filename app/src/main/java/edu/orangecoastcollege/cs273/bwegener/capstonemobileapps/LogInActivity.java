package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class LogInActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);
    }

    public void signUp(View v)
    {
        Intent launchSignUp = new Intent(this, NewUserActivity.class);

        startActivity(launchSignUp);
    }

    public void signIn(View v)
    {
        Intent launchSignIn = new Intent(this, MainMenuActivity.class);

        startActivity(launchSignIn);

    }
}
