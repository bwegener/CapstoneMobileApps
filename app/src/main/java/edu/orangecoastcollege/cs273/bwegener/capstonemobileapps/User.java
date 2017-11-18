package edu.orangecoastcollege.cs273.bwegener.capstonemobileapps;

/**
 * Created by Brian Wegener on 11/17/17.
 */

public class User {

    private String mId;
    private String mUserName;
    private String mFirstName;
    private String mLastName;
    private String mPassword;

    public User(String userName, String firstName, String lastName, String password)
    {
        mUserName = userName;
        mFirstName = firstName;
        mLastName = lastName;
        mPassword = password;
    }

    public String getId() {
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

    protected String getPassword() {
        return mPassword;
    }

    public void setPassword(String password) {
        mPassword = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        User user = (User) o;

        if (mId != null ? !mId.equals(user.mId) : user.mId != null) return false;
        if (mUserName != null ? !mUserName.equals(user.mUserName) : user.mUserName != null)
            return false;
        if (mFirstName != null ? !mFirstName.equals(user.mFirstName) : user.mFirstName != null)
            return false;
        if (mLastName != null ? !mLastName.equals(user.mLastName) : user.mLastName != null)
            return false;
        return mPassword != null ? mPassword.equals(user.mPassword) : user.mPassword == null;

    }

    @Override
    public int hashCode() {
        int result = mId != null ? mId.hashCode() : 0;
        result = 31 * result + (mUserName != null ? mUserName.hashCode() : 0);
        result = 31 * result + (mFirstName != null ? mFirstName.hashCode() : 0);
        result = 31 * result + (mLastName != null ? mLastName.hashCode() : 0);
        result = 31 * result + (mPassword != null ? mPassword.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "User{" +
                "mId='" + mId + '\'' +
                ", mUserName='" + mUserName + '\'' +
                ", mFirstName='" + mFirstName + '\'' +
                ", mLastName='" + mLastName + '\'' +
                ", mPassword='" + mPassword + '\'' +
                '}';
    }
}
