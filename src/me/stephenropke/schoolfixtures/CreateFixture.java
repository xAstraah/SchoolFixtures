/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.stephenropke.schoolfixtures;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

/**
 *
 * @author Stephen
 */
public class CreateFixture extends Activity {
    
    EditText type, desc, date, result;
    Button submit;
    
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.createfixture);
        
        type = (EditText) findViewById(R.id.type);
        desc = (EditText) findViewById(R.id.desc);
        date = (EditText) findViewById(R.id.date);
        result = (EditText) findViewById(R.id.result);
        submit = (Button) findViewById(R.id.submit);
        
        submit.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                String type;
                String desc;
                String date;
                String result;
            }
        });
    }
    
}
