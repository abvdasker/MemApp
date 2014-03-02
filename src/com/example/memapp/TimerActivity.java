package com.example.memapp;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class TimerActivity extends Activity {
	
	public TextView timerText;
	public static int countTimer = 3;
	public Timer t;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_timer);
        /*try{
        	getActionBar().setDisplayHomeAsUpEnabled(true);
        } catch (Exception e) {
        	
        }*/
        timerText = (TextView) findViewById(R.id.timer_text);

    	
    	//t = new Timer("countdown");
    	//t.scheduleAtFixedRate(new MyTimerTask(), 0, 1000);
    }
    
    public void onResume() {
    	super.onResume();
    	countTimer = 3;
    	t = new Timer("countdown");
    	t.scheduleAtFixedRate(new MyTimerTask(), 0, 1000);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_timer, menu);
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
    
    public void startTimer() {
    	if (countTimer != 0) {
    		timerText.setText("" + countTimer);
        	--countTimer;
    	} else {
    		Log.d("SEGUE", "from Timer");
    		timerText.setText("" + countTimer);
    		t.cancel();
    		t.purge();
        	Intent i = new Intent(this, StartActivity.class);
        	startActivity(i);
        	finish();
    	}
    }

    private class MyTimerTask extends TimerTask{

        @Override
        public void run() {        
            runOnUiThread(new Runnable() {   
                public void run() {
                    startTimer();
                }
            });
        }       
    }
    
}
