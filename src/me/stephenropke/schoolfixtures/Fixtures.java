/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.stephenropke.schoolfixtures;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.StrictMode;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

/**
 *
 * @author Stephen
 */
public class Fixtures extends Activity {
    
    Button addfixture;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.fixtures);
        
        StrictMode.ThreadPolicy policy = new StrictMode.ThreadPolicy.Builder().permitAll().build();
        StrictMode.setThreadPolicy(policy);
        
        connect();
        
        addfixture = (Button) findViewById(R.id.addfixture);
        
        /*
        * Tells the application what to do if the add fixture button is clicked.
        */
        
        addfixture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Authentication a = new Authentication();
                
                /*
                * Checks if the user has logged in / the current users authLevel.
                * If their authLevel is 0 they will not have access.
                * If their authLevel is 1 they will have access.
                */
                
                if(a.getAuthLevel() != 1) {
                    Toast.makeText(getApplicationContext(), "You don't have authentication!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                /*
                * If they have access the screen layout will change from fixtures.xml to createfixture.xml.
                */
                
                startActivity(new Intent(Fixtures.this, CreateFixture.class));
            }
        });
    }
    
    /*
    * This is a custom method that has been made to connect to the MySQL database where all the fixtures are stored.
    * This will also make it so it displays the new fixtures on the Fixtures list.
    */
    
    public void connect() {
        
        /*
        * Creates a list to hold all of the fixtures once they are downloaded.
        */
        
        String data;
        List<String> r = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, r);
        ListView list = (ListView) findViewById(R.id.listView1);
        
        /*
        * Connects to the MySQL database through a php file and outputs the fixtures in JSON format.
        */
        
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://10.0.2.2/retrievedata.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            data=EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {
                
                /*
                * Iterates through the database to get all the fixtures and all of the fixtures information.
                */
                
                JSONArray json = new JSONArray(data);
                for(int i = 0; i < json.length(); i++) {
                    JSONObject obj = json.getJSONObject(i);
                    String type = obj.getString("type");
                    String description = obj.getString("description");
                    String date = obj.getString("date");
                    String result = obj.getString("result");
                    Log.e("STRING", type);
                    String iteminlist = type + " : " + date;
                    r.add(iteminlist);
                    list.setAdapter(adapter);
                }
            } catch(JSONException e) {
                e.printStackTrace();
            }
        } catch(ClientProtocolException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        } catch(IOException e) {
            Log.d("HTTPCLIENT", e.getLocalizedMessage());
        }
        
        /*
        * TODO: When you click on a item in the list it will change the screen layout from
        * fixtures.xml to moreinfoonfixture.xml and will display the full type, description, date, and result.
        */
        
        list.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
                Intent MoreInfoOnFixtureScreen = new Intent(Fixtures.this, MoreInfoOnFixture.class);
            }
        });
    }
    
}
