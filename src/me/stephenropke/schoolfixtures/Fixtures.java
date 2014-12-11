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
        
        addfixture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                Authentication a = new Authentication();
                
                if(a.getAuthLevel() != 1) {
                    Toast.makeText(getApplicationContext(), "You don't have authentication!", Toast.LENGTH_SHORT).show();
                    return;
                }
                
                startActivity(new Intent(Fixtures.this, CreateFixture.class));
            }
        });
    }
    
    public void connect() {
        String data;
        List<String> r = new ArrayList<String>();
        ArrayAdapter<String> adapter = new ArrayAdapter<String>(getApplicationContext(), android.R.layout.simple_list_item_1, r);
        ListView list = (ListView) findViewById(R.id.listView1);
        try {
            DefaultHttpClient client = new DefaultHttpClient();
            HttpGet request = new HttpGet("http://10.0.2.2/retrievedata.php");
            HttpResponse response = client.execute(request);
            HttpEntity entity=response.getEntity();
            data=EntityUtils.toString(entity);
            Log.e("STRING", data);
            try {
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
    }
    
}
