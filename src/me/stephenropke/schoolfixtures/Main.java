package me.stephenropke.schoolfixtures;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main extends Activity
{
    
    /*
    * Creates fields to be initialised later.
    */
    
    EditText username, password;
    Button login, skiplogin;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        
        /*
        * Tells the application to display the main.xml layout file on the screen.
        */
        
        setContentView(R.layout.main);
        
        /*
        * Initialising the fields that we set earlier.
        */
        
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        skiplogin = (Button) findViewById(R.id.skiplogin);
        
        /*
        * Tells the application what to do when the login button is clicked.
        */
        
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                
                /*
                * Checks if the username and password equal what they should.
                */
                
                if(username.getText().toString().equals("Mr.Ropke") && password.getText().toString().equals("Thenameisropke")) {
                    
                    /*
                    * If the username and password they entered is correct it will give them access to add and update fixtures.
                    */
                    
                    Authentication a = new Authentication();
                    a.setUsername(username.getText().toString());
                    a.setAuthLevel(1);
                    Intent fixturesScreen = new Intent(Main.this, Fixtures.class);
                    startActivity(fixturesScreen);
                } else {
                    
                    /*
                    * If the username and password they entered is wrong it will show a popup saying "Incorrect username or password!"
                    */
                    
                    String incorrectunop = "Incorrect username or password!";
                    Toast.makeText(getApplicationContext(), incorrectunop, Toast.LENGTH_SHORT).show();
                }
            }
        });
        
        /*
        * Tells the application what to do if the skiplogin button is clicked.
        */
        
        skiplogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                
                /*
                * This will set the user as a guest and they will only be able to view fixtures.
                */
                
                Authentication a = new Authentication();
                a.setUsername("Guest");
                a.setAuthLevel(0);
                
                /*
                * Will change the screen layout from main.xml to fixtures.xml.
                */
                
                Intent fixturesScreen = new Intent(Main.this, Fixtures.class);
                startActivity(fixturesScreen);
            }
        });
    }
}
