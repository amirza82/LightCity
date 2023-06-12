package org.example.models;

import org.example.Information;

public class User {
    private String username; //must be unique
    private String password;
    private boolean correctUserName;

    public User() {
    }
    public User(String username, String password) {
        correctUserName = true; // First, we assume that the username is unique
        for (User u: Information.users) {
            if (u.getUsername().equals(username)){
                correctUserName = false;
                break;
            }

        }
        if (correctUserName) {
            this.username = username;
            this.password = password;
        }
        else{
            System.out.println("username has already taken. Try again.");
        }
    }

    public boolean isCorrectUserName() {
        return correctUserName;
    }

    public void setCorrectUserName(boolean correctUserName) {
        this.correctUserName = correctUserName;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
