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

    // TODO: FINISH THIS
    public boolean isValidUserName(String userName)
    {

        // Cycle through a list of user names
        // make sure that the same user name has not
        // been used before.
        /*

         */
        return true;
    }

    // TODO: MAKE THIS WORK
    public boolean isValidPassword(String password)
    {
        // Valid password must contain (see regex below):
        // At least one lower case letter
        // At least one digit
        // At least one special character (@, #, $, %, !)
        // At least one upper case letter
        // At least 8 characters long, but no more than 16
        return password.matches("((?=.*[a-z])(?=.*d)(?=.*[@#$%!])(?=.*[A-Z]).{8,16})");
    }

    // TODO: MAKE SURE THIS WORKS
    public boolean isValidEmail(String email)
    {
        // This checks to see if the email is valid
        return email.matches("^[_A-Za-z0-9-\\+]+(\\.[_A-Za-z0-9-]+)*@" +
                "^[_A-Za-z0-9-]+(\\.[_A-Za-z0-9] + )*(\\.[A-Za-z]{2,})$");
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
        Intent launchMainMenu = new Intent(this, MainMenuActivity.class);

        startActivity(launchMainMenu);
    }

    public void cancel(View v)
    {
        Intent launchLogIn = new Intent(this, LogInActivity.class);

        startActivity(launchLogIn);
    }
}
