package com.example.memapp;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.view.Menu;
import android.view.View;
//import android.widget.Button;

public class MainActivity extends Activity {

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.activity_main, menu);
        return true;
    }
    
    public void button1Press(View v) {
    	Intent i = new Intent(this, TimerActivity.class);
    	startActivity(i);
    	finish();
    }
    
    public void showInstructions(View v) {
    	Intent i = new Intent(this, InstructionActivity.class);
    	startActivity(i);
    	finish();
    }
}
