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
    
    EditText username, password;
    Button login, skiplogin;
    
    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        
        username = (EditText) findViewById(R.id.username);
        password = (EditText) findViewById(R.id.password);
        login = (Button) findViewById(R.id.login);
        skiplogin = (Button) findViewById(R.id.skiplogin);
        
        login.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                if(username.getText().toString().equals("Mr.Ropke") && password.getText().toString().equals("Thenameisropke")) {
                    Authentication a = new Authentication();
                    a.setUsername(username.getText().toString());
                    a.setAuthLevel(1);
                    Intent fixturesScreen = new Intent(Main.this, Fixtures.class);
                    startActivity(fixturesScreen);
                } else {
                    String incorrectunop = "Incorrect username or password!";
                    Toast.makeText(getApplicationContext(), incorrectunop, Toast.LENGTH_LONG).show();
                }
            }
        });
        
        skiplogin.setOnClickListener(new View.OnClickListener() {
            public void onClick(View view) {
                Intent fixturesScreen = new Intent(Main.this, Fixtures.class);
                startActivity(fixturesScreen);
            }
        });
    }
}
