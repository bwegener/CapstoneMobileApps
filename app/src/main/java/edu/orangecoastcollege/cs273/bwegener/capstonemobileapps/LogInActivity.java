package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.content.Intent;
import android.graphics.drawable.AnimationDrawable;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.EditText;

public class LogInActivity extends AppCompatActivity {

    // AnimationDrawable = used for frame animations
    private AnimationDrawable frameAnim;

    // Animation = used for tween(ed) animations
    private Animation alphaAnim;

    private EditText userNameLogIn;
    private EditText passwordLogIn;
    boolean hasRunAnimation;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_in);

        userNameLogIn = (EditText) findViewById(R.id.userNameLogInET);
        passwordLogIn = (EditText) findViewById(R.id.passwordLogInET);

        RunAnimation();

    }


    private void RunAnimation()
    {
        Animation a = AnimationUtils.loadAnimation(this, R.anim.anim);
        a.reset();
        userNameLogIn.clearAnimation();
        userNameLogIn.startAnimation(a);
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
