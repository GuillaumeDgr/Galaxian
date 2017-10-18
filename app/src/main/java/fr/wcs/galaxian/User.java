package fr.wcs.galaxian;

/**
 * Created by wilder on 06/10/17.
 */

public class User {

    private String user_name;
    private String user_password;
    private int user_score;

    public User() {
        // Needed for firebase
    }

    public User(String user_name, String user_password, int user_score) {
        this.user_name = user_name;
        this.user_password = user_password;
        this.user_score = user_score;
    }


    public String getUser_name() {
        return user_name;
    }

    public void setUser_name(String user_name) {
        this.user_name = user_name;
    }

    public String getUser_password() {
        return user_password;
    }

    public void setUser_password(String user_password) {
        this.user_password = user_password;
    }

    public int getUser_score() {
        return user_score;
    }

    public void setUser_score(int user_score) {
        this.user_score = user_score;
    }
}
