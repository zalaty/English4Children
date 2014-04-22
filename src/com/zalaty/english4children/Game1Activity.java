package com.zalaty.english4children;


import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;

import android.app.Activity;
import android.app.Dialog;
import android.content.Intent;
import android.media.AudioManager;
import android.media.MediaPlayer;
import android.media.SoundPool;
import android.os.Bundle;
import android.os.Handler;
import android.os.SystemClock;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.zalaty.english4children.classes.Games;

public class Game1Activity extends Activity implements OnClickListener{
	
	private static final String TAG = Game1Activity.class.getSimpleName();
	private ImageButton imageButton0;
	private ImageButton imageButton1;
	private ImageButton imageButton2;
	private ImageButton imageButton3;
	private ImageView imageButtonNew;
	private ImageView imageViewInicio;
	private ImageView imageViewInfo;
	private TextView textViewSelected;
	private TextView textViewElement;
	//private TextView textViewCorrect;
	
	int NCorrecto;
	int NIncorrecto;
	private ImageView imageViewC1;
	private ImageView imageViewC2;
	private ImageView imageViewC3;
	private ImageView imageViewI1;
	private ImageView imageViewI2;
	private ImageView imageViewI3;
	
	//
	ArrayList<String> aTElements;	
	ArrayList<String> a4Elements;
	String element;
	Handler handler = new Handler(); 
	
	Games game;
	
	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState){
		super.onCreate(savedInstanceState);
		setContentView(R.layout.game1layout);
		
		findViews();		
		setListeners();
		LoadGame();
		CleanGame();
	}
	
	private void findViews(){
		imageButton0 = (ImageButton)findViewById(R.id.imageButton0);
		imageButton1 = (ImageButton)findViewById(R.id.imageButton1);
		imageButton2 = (ImageButton)findViewById(R.id.imageButton2);
		imageButton3 = (ImageButton)findViewById(R.id.imageButton3);
		imageButtonNew = (ImageView)findViewById(R.id.imageButtonNew);
		imageViewInicio = (ImageView)findViewById(R.id.imageViewInicio);
		imageViewInfo = (ImageView)findViewById(R.id.imageViewInfo);
		textViewElement = (TextView)findViewById(R.id.textViewElement);
		textViewSelected = (TextView)findViewById(R.id.textViewSelected);
		//textViewCorrect = (TextView)findViewById(R.id.textViewCorrect);
		imageViewC1 = (ImageView)findViewById(R.id.imageViewC1);
		imageViewC2 = (ImageView)findViewById(R.id.imageViewC2);
		imageViewC3 = (ImageView)findViewById(R.id.imageViewC3);
		imageViewI1 = (ImageView)findViewById(R.id.imageViewI1);
		imageViewI2 = (ImageView)findViewById(R.id.imageViewI2);
		imageViewI3 = (ImageView)findViewById(R.id.imageViewI3);
	}
	
	private void setListeners(){
		imageButton0.setOnClickListener(this);
		imageButton1.setOnClickListener(this);
		imageButton2.setOnClickListener(this);
		imageButton3.setOnClickListener(this);
		imageButtonNew.setOnClickListener(this);
		imageViewInicio.setOnClickListener(this);
		imageViewInfo.setOnClickListener(this);
		textViewElement.setOnClickListener(this);
	}
	
	private void LoadGame(){
		GetTElements();
		Get4Elements();
		GetElement();
	}
	
	private void GetTElements(){
		try{
			//aTElements = new String[getResources().getStringArray(R.array.acolors).length];
			 // LOAD THE GAME, ACCORDING WITH DE PARAMETER THROW THE MAIN ACTIVITY 
			//Bundle extra = this.getIntent().getExtras();
			game = Games.detachFrom(getIntent());
			 // CHECK IF THE GAME IS PASSED 
			if(game != null){
				switch (game){
					case colores:
						aTElements =  new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.acolors)));
						break;
					case numbers:
						aTElements =  new ArrayList<String>(Arrays.asList(getResources().getStringArray(R.array.anumbers)));
						break;	
				default:
					//startActivity(new Intent(this,MainActivity.class));
					this.finish();
					break;
				}
			}
			else{
				this.finish();
				//startActivity(new Intent(this,MainActivity.class));
			}
		}catch(Exception ex){
			Log.d(TAG,ex.toString());
		}
	}

	private void Get4Elements(){
		try{
			// LOAD ARRAY[4] 
			a4Elements = new ArrayList<String>();
			int rnd = 0; 
			Random rand = new Random();
			for (int i = 0; i<4; i++){				
				if(game != null){
					switch (game){
						case colores:
							rnd = rand.nextInt(getResources().getStringArray(R.array.acolors).length-i);
							break;
						case numbers:
							rnd = rand.nextInt(getResources().getStringArray(R.array.anumbers).length-i);
							break;	
						default:
							this.finish();
							//startActivity(new Intent(this,MainActivity.class));
							break;
					}	
				}
				a4Elements.add(aTElements.get(rnd));
				aTElements.remove(rnd);
	             
			} 
			// LOAD IMAGENBUTTONS 
			imageButton0.setBackgroundResource(GetImagen(a4Elements.get(0)));
			imageButton1.setBackgroundResource(GetImagen(a4Elements.get(1)));
			imageButton2.setBackgroundResource(GetImagen(a4Elements.get(2)));
			imageButton3.setBackgroundResource(GetImagen(a4Elements.get(3)));
		}catch(Exception ex){
			Log.d(TAG,ex.toString());
		}
	}
	
	private void GetElement(){
		try{
			// LOAD ELEMENT 
			int rnd = 0; 
			Random rand = new Random();
			rnd = rand.nextInt(a4Elements.size());
			element = a4Elements.get(rnd);
			// LOAD EDITTEXTELEMENT
			textViewElement.setText(element.toUpperCase());
			// PLAY THE ELEMENT
			PlaySound(element);
		}catch(Exception ex){
			Log.d(TAG,ex.toString());
		}
	}
	
	
	private void PlaySound(String element){
		MediaPlayer mp = MediaPlayer.create(getApplicationContext(), R.raw.chimes);
		if(element.equals("black")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.black);
		}else if(element.equals("blue")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.blue);
		}else if(element.equals("pink")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.pink);
		}else if(element.equals("red")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.red);
		}else if(element.equals("white")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.white);
		}else if(element.equals("yellow")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.yellow);
		}else if(element.equals("green")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.green);
		}else if(element.equals("brown")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.brown);
		}else if(element.equals("orange")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.orange);
		}else if(element.equals("one")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.one);
		}else if(element.equals("two")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.two);
		}else if(element.equals("three")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.three);
		}else if(element.equals("four")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.four);
		}else if(element.equals("five")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.five);
		}else if(element.equals("six")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.six);
		}else if(element.equals("seven")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.seven);
		}else if(element.equals("eight")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.eight);
		}else if(element.equals("nine")){
			mp = MediaPlayer.create(getApplicationContext(), R.raw.nine);
		}
	
	    mp.start();
		
	}
	
	private int GetImagen(String element){
		int draw = R.drawable.white;
		if(element.equals("black")){
			draw = R.drawable.black;
		}else if(element.equals("blue")){
			draw = R.drawable.blue;
		}else if(element.equals("pink")){
			draw = R.drawable.pink;
		}else if(element.equals("red")){
			draw = R.drawable.red;
		}else if(element.equals("white")){
			draw = R.drawable.white;
		}else if(element.equals("yellow")){
			draw = R.drawable.yellow;
		}else if(element.equals("green")){
			draw = R.drawable.green;
		}else if(element.equals("brown")){
			draw = R.drawable.brown;
		}else if(element.equals("orange")){
			draw = R.drawable.orange;
		}else if(element.equals("one")){
			draw = R.drawable.one;
		}else if(element.equals("two")){
			draw = R.drawable.two;
		}else if(element.equals("three")){
			draw = R.drawable.three;
		}else if(element.equals("four")){
			draw = R.drawable.four;
		}else if(element.equals("five")){
			draw = R.drawable.five;
		}else if(element.equals("six")){
			draw = R.drawable.six;
		}else if(element.equals("seven")){
			draw = R.drawable.seven;
		}else if(element.equals("eight")){
			draw = R.drawable.eight;
		}else if(element.equals("nine")){
			draw = R.drawable.nine;
		}
		
		return draw;
	}

	private void CleanGame(){
		NCorrecto = 0;
		NIncorrecto = 0;
		imageViewC1.setBackgroundResource(0);
		imageViewC2.setBackgroundResource(0);
		imageViewC3.setBackgroundResource(0);
		imageViewI1.setBackgroundResource(0);
		imageViewI2.setBackgroundResource(0);
		imageViewI3.setBackgroundResource(0);
	}
	
	private void CheckElement(final String elementSelected){ 
		try{		
			
			// LOAD EDITTEXTSELECTED 
			textViewSelected.setText(elementSelected.toUpperCase()); 
			// OK 
			if(elementSelected.equals(element)){
				//textViewCorrect.setText(R.string.correct);
				ShowToastCIN(R.string.correct, R.drawable.ok);
				
				NCorrecto++;
				if (NCorrecto == 1)
					imageViewC1.setBackgroundResource(R.drawable.cake);
				if (NCorrecto == 2)
					imageViewC2.setBackgroundResource(R.drawable.cake);
				if (NCorrecto == 3)
					imageViewC3.setBackgroundResource(R.drawable.cake);
			}else{
				//textViewCorrect.setText(R.string.incorrect);
				ShowToastCIN(R.string.incorrect, R.drawable.error);
				NIncorrecto++;
				if (NIncorrecto == 1)
					imageViewI1.setBackgroundResource(R.drawable.bomb);
				if (NIncorrecto == 2)
					imageViewI2.setBackgroundResource(R.drawable.bomb);
				if (NIncorrecto == 3)
					imageViewI3.setBackgroundResource(R.drawable.bomb);
			}
			
			
		    handler.postDelayed(new Runnable() { 
		         public void run() { 
		        	 textViewSelected.setText("");
		         } 
		    }, 1000);
		    
		    
		    // SHOW A DIALOG IF THE GAME IS OVER
		    if(NIncorrecto == 3){
		    	ShowDialog(R.string.try_again,R.drawable.tryagain,26);
		    }else if(NCorrecto == 3){
		    	switch(NIncorrecto){
		    	case 0:
		    		ShowDialog(R.string.excellent,R.drawable.excellent,26);
		    		break;
		    	case 1:
		    		ShowDialog(R.string.good_job,R.drawable.goodjob,26);
		    		break;
		    	case 2:
		    		ShowDialog(R.string.nice,R.drawable.nice,26);
		    		break;
		    	}
		    }else{
		    	LoadGame();
		    }
			
			
		}catch(Exception ex){
			Log.d(TAG,ex.toString());
		}
	}
	
	@Override
	public void onClick(View v) {
		try{
			switch (v.getId()){
			case R.id.imageButton0:
				CheckElement(a4Elements.get(0));
				break;
			case R.id.imageButton1:
				CheckElement(a4Elements.get(1));
				break;
			case R.id.imageButton2:
				CheckElement(a4Elements.get(2));
				break;
			case R.id.imageButton3:
				CheckElement(a4Elements.get(3));
				break;
			case R.id.imageButtonNew:
				LoadGame();
				CleanGame();
				break;
			case R.id.imageViewInicio:
				this.finish();
				startActivity(new Intent(this,MainActivity.class));
				break;
			case R.id.imageViewInfo:
				ShowDialog(R.string.info,R.drawable.children,16);
				break;
			case R.id.textViewElement:
				PlaySound(element);
				break;				
			}
		}catch(Exception ex){
			
		}
		
	}

	private void ShowToastCIN(int msg, int pic){
		LayoutInflater inflater = getLayoutInflater();
		View layout = inflater.inflate(R.layout.toastcinlayout,
		                               (ViewGroup) findViewById(R.id.toast_layout_root));

		ImageView image = (ImageView) layout.findViewById(R.id.imageViewCIN);
		image.setImageResource(pic);
		TextView text = (TextView) layout.findViewById(R.id.textViewCIN);
		text.setText(msg);

		Toast toast = new Toast(getApplicationContext());
		toast.setGravity(Gravity.TOP, 0, 30);
		toast.setDuration(Toast.LENGTH_SHORT);
		toast.setView(layout);
		toast.show();
	}
	
	private void ShowDialog(int msg, int pic, int size){
		// Create custom dialog object
        final Dialog dialog = new Dialog(this);
        
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE); 
        // Include dialog.xml file
        dialog.setContentView(R.layout.dialoglayout);
        // Set dialog title
        dialog.setTitle("Custom Dialog");
  
        // set values for custom dialog components - text, image and button
        TextView text = (TextView) dialog.findViewById(R.id.textViewDialog);
        text.setText(msg);
        text.setTextSize(size);
        ImageView image = (ImageView) dialog.findViewById(R.id.imageViewDialog);
        image.setImageResource(pic);

        dialog.show();
         
        Button declineButton = (Button) dialog.findViewById(R.id.buttonDialog);
        // if decline button is clicked, close the custom dialog
        declineButton.setOnClickListener(new OnClickListener() {
            @Override
            public void onClick(View v) {
            	LoadGame();
				CleanGame();
                // Close dialog
                dialog.dismiss();
            }
        });
	}
}
