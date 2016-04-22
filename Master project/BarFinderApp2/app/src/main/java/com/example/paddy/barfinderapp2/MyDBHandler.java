package com.example.paddy.barfinderapp2;

import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.Cursor;
import android.content.Context;
import android.content.ContentValues;

public class MyDBHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "newestBarFinderDB";
    public static final String TABLE_USER = "usertable";
    public static final String COLUMN_ID = "_id";
    public static final String COLUMN_USERNAME = "username";
    public static final String COLUMN_PASSWORD = "password";
    public static final String TABLE_POSTCODES = "postcodes";
    public static final String COLUMN_POSTID = "_id";
    public static final String COLUMN_USERID= "_userID";
    public static final String COLUMN_POSTCODES = "postcode";
    public SQLiteDatabase db;






    //We need to pass database information along to superclass
    public MyDBHandler(Context context, String name, SQLiteDatabase.CursorFactory factory, int version) {
        super(context, DATABASE_NAME, factory, DATABASE_VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        System.out.println("On Create");

        String query = "CREATE TABLE " + TABLE_USER + "(" +
                COLUMN_PASSWORD + " TEXT, "+COLUMN_USERNAME+" TEXT PRIMARY KEY "+" );";
        String query2 ="CREATE TABLE " + TABLE_POSTCODES+" ("+COLUMN_POSTID +" INTEGER PRIMARY KEY AUTOINCREMENT, "
                + COLUMN_POSTCODES + " TEXT, "+ COLUMN_USERNAME +
                " TEXT, FOREIGN KEY ("+COLUMN_USERNAME+") REFERENCES "+TABLE_USER+"("+COLUMN_USERNAME+"));";
        db.execSQL(query);
        db.execSQL(query2);

    }


    public void passwordQuery(User user){
        String username;
        username = user.get_username();

        String query = "SELECT password FROM user WHERE '"+username+"';";

        SQLiteDatabase db = getReadableDatabase();
        db.execSQL(query);


    }

    public String getSingleEntry(String userName)
    {
        db = this.getReadableDatabase();
        String query = "select username, password from "+TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);


                if(a.equals(userName)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }

    public String getPostcode(String userName)
    {
        db = this.getReadableDatabase();
        String query = "select username, postcode from "+TABLE_POSTCODES;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";

        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);


                if(a.equals(userName)){
                    b = cursor.getString(1);
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }


    public String getsSingleEntry(String userName)
    {
        db = this.getReadableDatabase();
        String query = "select username from "+TABLE_USER;
        Cursor cursor = db.rawQuery(query, null);

        String a, b;
        b = "not found";


        if(cursor.moveToFirst()){
            do{
                a = cursor.getString(0);


                if(a.equals(userName)){
                    b = "found";
                    break;
                }
            }
            while(cursor.moveToNext());
        }

        return b;
    }



    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_USER);
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_POSTCODES);
        onCreate(db);
    }

    //Add a new user to the database
    public void addUser(User user){
        ContentValues values = new ContentValues();
        ContentValues values1 = new ContentValues();
        values.put(COLUMN_USERNAME, user.get_username());
        values.put(COLUMN_PASSWORD, user.get_password());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_USER, null, values);
        System.out.println("addUser");
        db.close();
    }

    //Add a new postcode to the database
    public void addPostcode(PostCodes postcode){


        ContentValues values = new ContentValues();

        values.put(COLUMN_POSTCODES, postcode.get_postCode());
        values.put(COLUMN_USERNAME, postcode.get_username());
        SQLiteDatabase db = getWritableDatabase();
        db.insert(TABLE_POSTCODES, null, values);
        System.out.println("addPostcode");
        db.close();
    }

    //Delete a user from the database
  //  public void deleteProduct(String productName){
  //      SQLiteDatabase db = getWritableDatabase();
  //      db.execSQL("DELETE FROM " + TABLE_PRODUCTS + " WHERE " + COLUMN_PRODUCTNAME + "=\"" + productName + "\";");
  //  }

    public String databaseToString(){
        String dbString = "";
        SQLiteDatabase db = getWritableDatabase();
        String query = "SELECT * FROM " + TABLE_USER + " WHERE 1";

        //Cursor points to a location in your results
        Cursor c = db.rawQuery(query, null);
        //Move to the first row in your results
        c.moveToFirst();

        //Position after the last row means the end of the results
        while (!c.isAfterLast()) {
            if (c.getString(c.getColumnIndex("username")) != null) {
                dbString += c.getString(c.getColumnIndex("username"));
                dbString += "\n";
            }
            c.moveToNext();
        }
        db.close();
        return dbString;
    }

    public void printDB(){
        System.out.println(databaseToString());
    }

}
