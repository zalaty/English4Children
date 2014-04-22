package com.zalaty.english4children;

import com.zalaty.english4children.classes.Games;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.KeyEvent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends Activity implements OnClickListener, Runnable{

	private ImageView imageButtonColors;
	private ImageView imageButtonNumbers;
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.mainlayout);
		findViews();
		setListeners();
	}
	
	private void findViews(){
		imageButtonColors = (ImageView)findViewById(R.id.imageButtonColors);
		imageButtonNumbers = (ImageView)findViewById(R.id.imageButtonNumbers);
	}
	
	private void setListeners(){
		imageButtonColors.setOnClickListener(this);
		imageButtonNumbers.setOnClickListener(this);
	}

	@Override
	public void onClick(View v) {
		Intent myIntent;
		switch (v.getId()){
		case R.id.imageButtonColors:
			myIntent = new Intent(this,Game1Activity.class);			
			Games.colores.attachTo(myIntent);
			startActivity(myIntent);
			break;
		case R.id.imageButtonNumbers:
			myIntent = new Intent(this,Game1Activity.class);			
			Games.numbers.attachTo(myIntent);
			startActivity(myIntent);
			break;
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		
	}
	
	@Override
	public boolean onKeyDown(int keyCode, KeyEvent event) {
	    if (keyCode == KeyEvent.KEYCODE_BACK ) {
    	

	    	AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(
					this);
	 
		    	
				// set title
				alertDialogBuilder.setTitle(R.string.app_name);
	 
				// set dialog message
				alertDialogBuilder
					.setMessage(R.string.terminado)
					.setCancelable(false)
					.setPositiveButton(R.string.si,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, close
							// current activity
//							
							//System.exit(0);
							finish();
							android.os.Process.killProcess(android.os.Process.myPid());
							//super.onDestroy();
						}

					  })
					.setNegativeButton(R.string.no,new DialogInterface.OnClickListener() {
						public void onClick(DialogInterface dialog,int id) {
							// if this button is clicked, just close
							// the dialog box and do nothing
							dialog.cancel();
						}
					});
	 
					// create alert dialog
					AlertDialog alertDialog = alertDialogBuilder.create();
	 
					// show it
					alertDialog.show();
	    	
	
	    	
	    	
	    	
	    }
	    return super.onKeyDown(keyCode, event);
	}
}
