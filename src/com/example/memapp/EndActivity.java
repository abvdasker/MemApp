package com.example.memapp;

import java.util.LinkedList;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;
import android.support.v4.app.NavUtils;

public class EndActivity extends Activity {
	
	final String[] badEndings = {
			"This is why computers are better than people.",
			"Your brain has succumbed to the rigorous trials of MemApp.",
			"Try again if you're an optimist.",
			"Pathetic!",
			"You did not do well.",
			"Well I think we all saw this coming.",
			"I forgive you for being so bad at this very simple game.",
			"What a surprise.",
			"As a computer, I can honestly say that your memory is terrible.",
			"That was bad and you should feel bad."
	};
	
	final String[] goodEndings = {
			"Not bad for a human.",
			"Someone's got a lot of RAM.",
			"You're not impressing anyone.",
			"Scared of a little rematch?",
			"I am proud of you."
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_end);
		// Show the Up button in the action bar.
		//getActionBar().setDisplayHomeAsUpEnabled(true);
		
		TextView msg = (TextView) findViewById(R.id.end_info);
		int s = AppVars.getShapes().size();
		String outText;
		int i;
		if (s > 8) {
			i = (int) (Math.random()*goodEndings.length);
			outText = goodEndings[i];
			msg.setText(outText + " You remembered " + (s - 1) + " shapes.");
		} else {
			i = (int) (Math.random()*badEndings.length);
			outText = badEndings[i];
			msg.setText(outText + " You only remembered " + (s - 1) + " shapes.");
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.activity_end, menu);
		return true;
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
	
	public void replay(View v) {
		AppVars.setShapes(new LinkedList<MyShape>());
    	Intent i = new Intent(this, TimerActivity.class);
    	startActivity(i);
    	finish();
	}

}
