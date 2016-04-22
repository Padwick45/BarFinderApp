package com.example.paddy.barfinderapp2;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

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


public class RegisterScreen extends AppCompatActivity {

    // declare variables
    private EditText username, pass1, pass2;
    private Button regBut;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register_screen);
        setUpVars();
    }

    // when the user clicks register button do this
    public void onClick(View view){

        String userName=username.getText().toString();
        String password1=pass1.getText().toString();
        String password2=pass2.getText().toString();

        System.out.println("Register clicked");

        if (userName.equals("")||password1.equals("")||password2.equals("")){
            Toast.makeText(getApplicationContext(), "Field Vaccant", Toast.LENGTH_LONG).show();
            return;

        }

        MyDBHandler myDBHandler = new MyDBHandler(this, null, null, 1);
        String usernameStatus = myDBHandler.getsSingleEntry(userName);


        if(!password1.equals(password2))
        {
            Toast.makeText(getApplicationContext(), "Password does not match", Toast.LENGTH_LONG).show();
            return;
        }
        if(usernameStatus.equals("found")){
            Toast.makeText(getApplicationContext(), "Username unvailable, try another username", Toast.LENGTH_LONG).show();
            return;
        } else {
            User user = new User(password1, userName);
            user.printUser();

            myDBHandler.addUser(user);


            Intent intent = new Intent(this, LoginScreen.class);
            startActivity(intent);
        }






    }



    // gets the references to the xml ids
   private void setUpVars(){
       username = (EditText) findViewById(R.id.registerUsernameET);
       pass1 = (EditText) findViewById(R.id.registerPassword1ET);
       pass2 = (EditText) findViewById(R.id.registerPassword2ET);
   }



}
