package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

import android.os.Parcel;
import android.os.Parcelable;

/**
 * The <code>User</code> creates a user with a specific ID, user name, first name,
 * last name, password, and gets their latitude, and longitude.
 *
 * @author bwegener
 * @version 1.0
 *
 * Created by Brian Wegener on 11/17/17.
 */

public class User implements Parcelable {

    private long mId;
    private String mUserName;
    private String mFirstName;
    private String mLastName;
    private String mEmail;
    private String mPassword;
    private double mLatitude;
    private double mLongitude;

    public User(long id, String userName, String firstName, String lastName, String email, String password, double latitude, double longitude)
    {
        mId = id;
        mUserName = userName;
        mFirstName = firstName;
        mLastName = lastName;
        mEmail = email;
        mPassword = password;
        mLatitude = latitude;
        mLongitude = longitude;
    }

    public User(String userName, String firstName, String lastName, String email, String password, double latitude, double longitude) {
        this(-1, userName, firstName, lastName, email, password, latitude, longitude);
    }

    protected User(Parcel in) {
        mId = in.readLong();
        mUserName = in.readString();
        mFirstName = in.readString();
        mLastName = in.readString();
        mEmail = in.readString();
        mPassword = in.readString();
        mLatitude = in.readDouble();
        mLongitude = in.readDouble();
    }

    public long getId() {
        return mId;
    }

    public String getUserName() {
        return mUserName;
    }

    public void setUserName(String userName) {
        mUserName = userName;
    }

    public String getFirstName() {
        return mFirstName;
    }

    public void setFirstName(String firstName) {
        mFirstName = firstName;
    }

    public String getLastName() {
        return mLastName;
    }

    public void setLastName(String lastName) {
        mLastName = lastName;
    }

    public String getEmail() { return mEmail; }

    public void setEmail(String email) { mEmail = email; }

    protected String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    public double getLatitude() { return mLatitude; }

    public void setLatitude(double latitude) { mLatitude = latitude; }

    public double getLongitude() { return mLongitude; }

    public void setLongitude(double longitude) { mLongitude = longitude; }


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

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel parcel, int i) {
        parcel.writeLong(mId);
        parcel.writeString(mUserName);
        parcel.writeString(mFirstName);
        parcel.writeString(mLastName);
        parcel.writeString(mEmail);
        parcel.writeString(mPassword);
        parcel.writeDouble(mLatitude);
        parcel.writeDouble(mLongitude);
    }

    public static final Creator<User> CREATOR = new Creator<User>() {
        @Override
        public User createFromParcel(Parcel in) {
            return new User(in);
        }

        @Override
        public User[] newArray(int size) {
            return new User[size];
        }
    };
}
