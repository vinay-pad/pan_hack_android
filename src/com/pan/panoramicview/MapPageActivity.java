package com.pan.panoramicview;

import java.util.List;

import com.parse.FindCallback;
import com.parse.Parse;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;



import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.GoogleMap.OnMarkerClickListener;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;

public class MapPageActivity extends Activity{

	   static final LatLng India = new LatLng(22 , 77);
	   static final LatLng China = new LatLng(38 , 97);
	   
	   static public GoogleMap googleMap;
	   
	   
	   protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.activity_main);
	      try { 
	            if (googleMap == null) {
	               googleMap = ((MapFragment) getFragmentManager().
	               findFragmentById(R.id.map)).getMap();
	            }
	         googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);
	         
	         googleMap.addMarker(new MarkerOptions().
			         position(China).title("FW_China"));
	         
	         googleMap.addMarker(new MarkerOptions().
			         position(India).title("FW_India"));
	         
	         
	         update_pins_color();  
	         
	       googleMap.setOnMarkerClickListener(new OnMarkerClickListener()
           {
	        	 @Override
               public boolean onMarkerClick(Marker arg0) {
                   
                   Toast.makeText(MapPageActivity.this, arg0.getTitle(),1000).show();// display toast
                   Intent i=new Intent(
                           MapPageActivity.this,
                           Deviceinfopage.class);
                   i.putExtra("fw_name", arg0.getTitle());
                   startActivity(i);
                   return true;
               }

           });  
	         

	      } catch (Exception e) {
	         e.printStackTrace();
	      }
	      
	      
	      
	      
	      
	      
	      final Handler handler=new Handler();
	      final Runnable r = new Runnable()
	      {
	          public void run() 
	          {
	              update_pins_color();
	              handler.postDelayed(this, 1000);
	          }
	      };
	      handler.postDelayed(r, 1000);
	      
	      
	      
	   }
	   
	   
	      
	   
	private void update_pins_color() {
		// TODO Auto-generated method stub
		Parse.initialize(this, "RnsDdfZemEz0bGQoqMQE4JyyfSLcdJmqTreY5QD9", "0mipbWl5aniumL4XNu1W9pQ4c7ovIM6EdyU8PLrU");
			ParseQuery<ParseObject> query = ParseQuery.getQuery("FW_India_conn");
			query.orderByDescending("updatedAt");
			query.setLimit(1);
			query.findInBackground(new FindCallback<ParseObject>() {
			    public void done(List<ParseObject> scoreList, ParseException e) {
			    	if (e == null) {
			    		//Toast.makeText(Deviceinfopage.this, "fetched data",1000).show();// display toast
			    		for(int i =0; i< scoreList.size(); i++) {
							ParseObject myObj = scoreList.get(i);
							String b_s_conn = (String) myObj.get("Connected");
							if( b_s_conn.equals("1")) {
								googleMap.addMarker(new MarkerOptions().
								         position(India).title("FW_India").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
							} else {
								googleMap.addMarker(new MarkerOptions().
								         position(India).title("FW_India"));
							}
							
						}
			    		
			        } else {
			        	googleMap.addMarker(new MarkerOptions().
						         position(India).title("FW_India"));
			        }
			    }
			});
			
			ParseQuery<ParseObject> query2 = ParseQuery.getQuery("FW_China_conn");
			query2.orderByDescending("updatedAt");
			query2.setLimit(1);
			query2.findInBackground(new FindCallback<ParseObject>() {
			    public void done(List<ParseObject> scoreList, ParseException e) {
			    	if (e == null) {
			    		//Toast.makeText(Deviceinfopage.this, "fetched data",1000).show();// display toast
			    		for(int i =0; i< scoreList.size(); i++) {
							ParseObject myObj = scoreList.get(i);
							String b_s_conn = (String) myObj.get("Connected");
							if( b_s_conn.equals("1")) {
								googleMap.addMarker(new MarkerOptions().
								         position(China).title("FW_China").icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_GREEN)));
							} else {
								googleMap.addMarker(new MarkerOptions().
								         position(China).title("FW_China"));
							}
							
						}
			    		
			        } else {
			    		googleMap.addMarker(new MarkerOptions().
						         position(China).title("FW_China"));
			        }
			    }
			});
			
		}
}