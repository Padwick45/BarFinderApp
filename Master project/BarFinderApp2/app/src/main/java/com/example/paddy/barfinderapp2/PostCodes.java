package com.example.paddy.barfinderapp2;

/**
 * Created by Paddy on 09/04/2016.
 */
public class PostCodes {

    private int _id;
    private String _username;
    private String _postCode;

    public PostCodes(){

    }

    public PostCodes(String username, String postcode){
        this._username = username;
        this._postCode = postcode;

    }

    public String get_username() {
        return _username;
    }

    public void set_username(String _username) {
        this._username = _username;
    }




    public int get_id() {
        return _id;
    }

    public void set_id(int _id) {
        this._id = _id;
    }

    public String get_postCode() {
        return _postCode;
    }

    public void set_postCode(String _postCode) {
        this._postCode = _postCode;
    }





}
