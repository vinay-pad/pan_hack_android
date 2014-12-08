package com.pan.panoramicview;

import android.R.bool;
import android.app.ActionBar;
import android.app.Activity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;


import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.Parse;

import android.os.Bundle;
import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;


import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseInstallation;
import com.parse.ParsePush;
import com.parse.SaveCallback;


public class MainActivity extends Activity {
	Button loginBut;
	
	EditText uname, password123;
	String username, pass;
	static boolean first_time = true;
	TextView tt1, tt2;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      
	      if(first_time) {
	    	  Parse.enableLocalDatastore(this);
		      Parse.initialize(this, "RnsDdfZemEz0bGQoqMQE4JyyfSLcdJmqTreY5QD9", "0mipbWl5aniumL4XNu1W9pQ4c7ovIM6EdyU8PLrU");
		      first_time = false;
	      }
	      
	      
	      requestWindowFeature(Window.FEATURE_NO_TITLE);
	      getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, 
	                                      WindowManager.LayoutParams.FLAG_FULLSCREEN);
	      
	      setContentView(R.layout.firstloginpage);
	      tt1 = (TextView) findViewById(R.id.textView1_usname);
	      tt2 = (TextView) findViewById(R.id.textView2_pass);
	      tt1.setTextColor(Color.WHITE);
	      tt2.setTextColor(Color.WHITE);
	      
	      
	      //Drawable loginActivityBackground = findViewById(R.id.xxx).getBackground();
	      //loginActivityBackground.setAlpha(180);
	      
	    /*  
	    ActionBar bar = getActionBar();
	    bar.setBackgroundDrawable(new ColorDrawable(Color.parseColor("#00C4CD")));
	    */
	    //for image
	    //bar.setBackgroundDrawable(getResources().getDrawable(R.drawable.ic_launcher));
	      
	      
	      /*
	       * Enabling push notifiations....
	       */
	      ParsePush.subscribeInBackground("", new SaveCallback() {
	    	  @Override
	    	  public void done(ParseException e) {
	    	    if (e == null) {
	    	      Log.d("JAY", "successfully subscribed to the broadcast channel.");
	    	    } else {
	    	      Log.e("JAY", "failed to subscribe for push", e);
	    	    }
	    	  }
	    	});
	      
	   // Save the current Installation to Parse.
	      ParseInstallation.getCurrentInstallation().saveInBackground();
	   // When users indicate they are Giants fans, we subscribe them to that channel.
	      ParsePush.subscribeInBackground("ALERT");
	      
	      loginBut = (Button) findViewById(R.id.Loginbutton);
	      
	      uname = (EditText) findViewById(R.id.uname);
	      password123 = (EditText) findViewById(R.id.password_edit);
	      
	      loginBut.setOnClickListener(new View.OnClickListener() {
			
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				username =  uname.getText().toString().trim();
				pass = password123.getText().toString().trim();
				
				//if(true) {
				if(username.equals("admin") && pass.equals("admin")) {
				
					Toast.makeText(MainActivity.this, "successfully logged in",1000).show();// display toast
				 
					Intent i=new Intent(
                        MainActivity.this,
                        MapPageActivity.class);
					i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP | Intent.FLAG_ACTIVITY_NEW_TASK);
					startActivity(i);
				} else {
					
					Toast.makeText(MainActivity.this, "Try again!",1000).show();// display toast
				}
				
			}
		});
		
	}
	
	
	
	

 // ===========================================================
 // onPause
 // ===========================================================

 protected void onPause() {
     super.onPause();

 }


 protected void onResume() {
     //super.onRestoreInstanceState(savedInstanceState)();
	 super.onResume();
 }

 
 // ===========================================================
 // onStop
 // ===========================================================

 protected void onStop() {
     super.onStop();
 }

 // ===========================================================
 // onDestroy
 // ===========================================================

 @Override
 protected void onDestroy() {
    super.onDestroy();

 }
	
	
	
	
	   
	   
	}