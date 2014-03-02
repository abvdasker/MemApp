package com.example.memapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.support.v4.app.NavUtils;

public class StartActivity extends Activity {
	
	MemGameView mv;
	
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);
        //getActionBar().setDisplayHomeAsUpEnabled(true);
        
        mv = (MemGameView) findViewById(R.id.mem_view);
        
        mv.parent = this;
        
        //mv.randomShape();
    }
    
    @Override
    public void onResume() {
    	super.onResume();
    	
    	//mv.reUp();
    }
    
    public void moveToChoice() {
    	AppVars.setShapes(mv.shapes);
    	Intent i = new Intent(this, ChoiceActivity.class);
    	startActivity(i);
    	finish();
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_start, menu);
        return true;
    }
    
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case android.R.id.home:
                NavUtils.navigateUpFromSameTask(this);
                return true;
        }
        return super.onOptionsItemSelected(item);
    }

}
