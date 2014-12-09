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
import android.widget.Toast;

/**
 *
 * @author Stephen
 */
public class CreateFixture extends Activity {
	
	Button submit;
	
    /**
     * Called when the activity is first created.
     */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.createfixture);
        
        submit = (Button) findViewById(R.id.submitfixture);
        
        submit.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Toast.makeText(getApplicationContext(), "Feature not yet implemented!", Toast.LENGTH_LONG).show();
			}
		});
    }
    
}
