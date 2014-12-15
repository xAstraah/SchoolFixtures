/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.stephenropke.schoolfixtures;

import android.app.Activity;
import android.os.Bundle;
import android.os.StrictMode;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;

/**
 *
 * @author Stephen
 */
public class CreateFixture extends Activity {
    
    /*
    * Initialise fields.
    */
    
    EditText etType, etDesc, etDate, etResult;
    Button etSubmit;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        /*
        * StrictMode is a developer tool that will alert you if you have made a error in your code accidentally or if you have
        * Created a bug or something around the same lines.
        */
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        setContentView(R.layout.createfixture);
        
        /*
        * Initialises the fields that were set earlier into variables.
        */
        
        etType = (EditText) findViewById(R.id.etType);
        etDesc = (EditText) findViewById(R.id.etDesc);
        etDate = (EditText) findViewById(R.id.etDate);
        etResult = (EditText) findViewById(R.id.etResult);
        etSubmit = (Button) findViewById(R.id.etSubmit);
        
        /*
        * Tells the application what to do when the Submit button is clicked.
        */
        
        etSubmit.setOnClickListener(new View.OnClickListener() {
            
            InputStream is = null;
            
            /*
            * The code will upload the values in the EditText boxes to the MySQL database that is hosted on a local machine
            * if they contain values if not it will the data as blank fields. (Needs to be checked over for slight bugs)
            */
            
            public void onClick(View view) {
                String type = etType.getText().toString();
                String description = etDesc.getText().toString();
                String date = etDate.getText().toString();
                String result = etResult.getText().toString();
                
                List<NameValuePair> nameValuePairs = new ArrayList<NameValuePair>();
                
                nameValuePairs.add(new BasicNameValuePair("type", type));
                nameValuePairs.add(new BasicNameValuePair("description", description));
                nameValuePairs.add(new BasicNameValuePair("date", date));
                nameValuePairs.add(new BasicNameValuePair("result", result));
                
                try {
                    if(type.isEmpty() && description.isEmpty() && date.isEmpty() && result.isEmpty()) {
                        Toast.makeText(getApplicationContext(), "You have not entered anything!", Toast.LENGTH_SHORT).show();
                        return;
                    }
                    
                    HttpClient httpClient = new DefaultHttpClient();
                    HttpPost httpPost = new HttpPost("http://10.0.2.2/mysqlfile.php"); //Connect to the MySQL database via a PHP file.
                    httpPost.setEntity(new UrlEncodedFormEntity(nameValuePairs));
                    
                    HttpResponse response = httpClient.execute(httpPost);
                    HttpEntity entity = response.getEntity();
                    is = entity.getContent();
                    
                    String msg = "Fixture submitted!";
                    Toast.makeText(getApplicationContext(), msg, Toast.LENGTH_SHORT).show();
                    
                    startActivity(new Intent(CreateFixture.this, Fixtures.class));
                    
                    /*
                    * If there is a error make sure we catch it and print out where the error is and what type of error it is.
                    */
                    
                } catch (IOException e) {
                    e.printStackTrace();
                } catch (IllegalStateException e) {
                    e.printStackTrace();
                }
            }
        });
    }
    
}
