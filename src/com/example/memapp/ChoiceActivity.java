package com.example.memapp;

import java.util.Timer;
import java.util.TimerTask;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class ChoiceActivity extends Activity {
	
	ChoiceView cv;
	Button confirm;
	TextView text;
	Timer t;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_choice);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		cv = (ChoiceView) findViewById(R.id.choice_view);
		cv.parent = this;
		
		text = (TextView) findViewById(R.id.choice_text);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_choice, menu);
		return true;
	}
	
	public void correct() {
		text.setTextSize(25f);
		text.setTextColor(Color.GREEN);
		text.setText("CORRECT");
		t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				nextRound();
			}
		}, 2000);
	}
	
	public void incorrect() {
		text.setTextSize(25f);
		text.setTextColor(Color.RED);
		text.setText("INCORRECT");

		t = new Timer();
		t.schedule(new TimerTask() {
			public void run() {
				gameOver();
			}
		}, 2000);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			// This ID represents the Home or Up button. In the case of this
			// activity, the Up button is shown. Use NavUtils to allow users
			// to navigate up one level in the application structure. For
			// more details, see the Navigation pattern on Android Design:
			//
			// http://developer.android.com/design/patterns/navigation.html#up-vs-back
			//
			NavUtils.navigateUpFromSameTask(this);
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
	
	public void nextRound() {
		cv.restoreShapes();
    	Intent i = new Intent(this, TimerActivity.class);
    	startActivity(i);
    	finish();
	}
	
	public void gameOver() {
    	Intent i = new Intent(this, EndActivity.class);
    	startActivity(i);
    	finish();
	}
	
	public void choiceClick(View v) {
		cv.confirm();
	}

}
