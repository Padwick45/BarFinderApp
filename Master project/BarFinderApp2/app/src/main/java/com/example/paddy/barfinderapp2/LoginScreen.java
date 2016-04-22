package com.example.paddy.barfinderapp2;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
import android.view.View.OnClickListener;


public class LoginScreen extends AppCompatActivity {

    /**
     * Here the variables are declared to which will be used to reference
     * the views from the xml.
     */
    private EditText username;
    private EditText password;
    private Button login;
    private Button register;
    private TextView numberOfRemainingLoginAttempts;
    int numberOfRemainingLoginAttempts1 = 3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login_screen);
        // calls the setUpVars method
        setUpVars();

        /**
         * Register click handler
         */
        register.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View register) {
                registerLink();
            }
        });

        /**
         * This reacts to the clicking or pressing of the login button, if the button
         * has been clicked then the 'testLoginInput' method is called
         */
        login.setOnClickListener(new OnClickListener() {
            //Specifies what happens with the login button is clicked
            public void onClick(View login) {
                //calls the testLoginInput method
                testLoginInput();
            }

        });}


    /**
     * This method tests the login input checking if the input in the two EditText views
     * matches the desired username and password. If the input is correct the user is
     * directed to the QUB website and a toast pops up telling the user they were successful
     */
    public void testLoginInput() {

        String userName = username.getText().toString();
        String passWord = password.getText().toString();

        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);


        String storedPassword = myDBHandler.getSingleEntry(userName);
        if(storedPassword==null){
            Toast.makeText(getApplicationContext(), "Account doesn't exist",
                    Toast.LENGTH_SHORT).show();
        }else if (passWord.equals(storedPassword)) {
            //A short toast is displayed telling the user they have been successful
            Toast.makeText(getApplicationContext(), "You are successful to login",
                    Toast.LENGTH_SHORT).show();
            //The method webLink() is called here
            mainLink();



        }
        // If the if conditions have not been met then this part is used.
        else {
            // Shows a short toast telling the user they got the input wrong
            Toast.makeText(getApplicationContext(), "Wrong username or passwords",
                    Toast.LENGTH_SHORT).show();
            // decrements the attempts remaining by 1
            numberOfRemainingLoginAttempts1--;
            // sets the number of attempts remaining text view to the new value
            numberOfRemainingLoginAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts1));

            //Tests to see if the number of remaining login attempts equals 0
            if (numberOfRemainingLoginAttempts1 == 0) {
                // disables the use of the login button
                login.setEnabled(false);
            }
        }
    }

    /**
     * This method accesses the main menu
     */
    public void mainLink(){
        Intent intent = new Intent(this, MainActivity.class);
        intent.putExtra("username", username.getText().toString());
        startActivity(intent);
    }

    /**
     * This method accesses the register screen
     */
    public void registerLink(){
        Intent intent = new Intent(this, RegisterScreen.class);
        startActivity(intent);
    }


    /**
     * This method connects the variables declared in this class with
     * views in the xml code, this allows the xml views to be manipulated in the java
     * code
     */
    private void setUpVars() {
       username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
       login = (Button) findViewById(R.id.LoginButton);
       numberOfRemainingLoginAttempts = (TextView) findViewById(R.id.numberOfRemainingLoginAttempts);
       numberOfRemainingLoginAttempts.setText(Integer.toString(numberOfRemainingLoginAttempts1));
        register = (Button) findViewById(R.id.registerButton);
    }

}

