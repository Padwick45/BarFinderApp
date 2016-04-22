package com.example.paddy.barfinderapp2;

/**
 * Created by Paddy on 09/04/2016.
 */
public class User {

    private int _id;
    private String _username;
    private String _password;

    public User(){

    }

    public User ( String password, String username){

        this._password = password;
        this._username = username;

    }

    public void printUser(){

    }

    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_password() {
        return _password;
    }

    public void set_password(String _password) {
        this._password = _password;
    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }

}
