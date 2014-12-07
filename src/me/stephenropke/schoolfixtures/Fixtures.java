/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package me.stephenropke.schoolfixtures;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

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
        
        addfixture = (Button) findViewById(R.id.addfixture);
        
        addfixture.setOnClickListener(new View.OnClickListener() {

            public void onClick(View view) {
                startActivity(new Intent(Fixtures.this, CreateFixture.class));
            }
        });
    }
    
}
