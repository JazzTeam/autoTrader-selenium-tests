package org.jazzteam.test.user;

/**
 * Created by Savenok.Ivan on 27.04.2017.
 */
public enum User {

    SIMPLE_USER("test.ivanovich007@gmail.com", "q1234567");

    private String login;
    private String password;

    User(String login, String password){
        this.login = login;
        this.password = password;
    }

    public String getLogin() {
        return login;
    }

    public String getPassword() {
        return password;
    }
}
