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

        // This should allow the users to return to the
        // LogInActivity
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }


    // TODO: MAKE THIS WHOLE METHOD WORK
    /*
    public String signUpUser(String userName, String email, String password) {

        String message;
        if (!isValidEmail(email))
            message = "Email address is not valid. Please use different address.";
        if (!isValidPassword(password))
            message = "Password is not valid. " +
                    "Please create a password that is 8 characters, " +
                    "including 1 upper case letter, 1 lower case letter, " +
                    "1 digit, and one symbol.";



        return message;



        // FROM JAVA 2

        for (User u : theOne.mAllUsersList)
            if (u.getEmail().equalsIgnoreCase(email))
                return "Email address already used.  Please sign in or use different email.";

        if (!isValidPassword(password))
            return "Password must be at least 8 characters, including 1 upper case letter, 1 lower case letter, 1 digit and one symbol.";

        try
        {
            // In practice, passwords should always be encrypted before storing
            // in database:
            // See http://www.jasypt.org/howtoencryptuserpasswords.html for a
            // useful tutorial
            String[] values = { name, email, "STANDARD", password };
            int id = theOne.mUserDB.createRecord(Arrays.copyOfRange(USER_FIELD_NAMES, 1, USER_FIELD_NAMES.length),
                    values);
            mCurrentUser = new User(id, name, email, "STANDARD");
            theOne.mAllUsersList.add(mCurrentUser);
        }
        catch (Exception e)
        {
            e.printStackTrace();
            return "Error creating user, please try again.";
        }
        return "SUCCESS";
    }


    }
    */

    public void createUser(View v)
    {
        // This will allow the user to be added to the database
        // once the user table has been added to the database
        // db.addUser(userName, firstName, lastName, email, password)
        Intent launchMainMenu = new Intent(this, MainMenuActivity.class);

        startActivity(launchMainMenu);
    }

    public void cancel(View v)
    {
        Intent launchLogIn = new Intent(this, LogInActivity.class);

        startActivity(launchLogIn);
    }
}
