package com.pan.panoramicview;

import java.util.ArrayList;
import java.util.List;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class AlertPageActivity extends Activity{

	TextView fw_text_view;
	TextView complete_msg;
	
	
	TextView 	tt_ll_1, tt_ll_2, tt_ll_3, tt_ll_4, tt_ll_5, 
	tt_ll_6, tt_ll_7, tt_ll_8, tt_ll_9;
	
	Button take_action_but;
	
	public static ArrayList<String> threat_vals_list = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.threatalertpage);
	      
	      fw_text_view = (TextView) findViewById(R.id.firewall_textView);
	      tt_ll_1 = (TextView) findViewById(R.id.tt_l_1);
	      tt_ll_2 = (TextView) findViewById(R.id.tt_l_2);
	      tt_ll_3 = (TextView) findViewById(R.id.tt_l_3);
	      tt_ll_4 = (TextView) findViewById(R.id.tt_l_4);
	      tt_ll_5 = (TextView) findViewById(R.id.tt_l_5);
	      tt_ll_6 = (TextView) findViewById(R.id.tt_l_6);
	      tt_ll_7 = (TextView) findViewById(R.id.tt_l_7);
	      tt_ll_8 = (TextView) findViewById(R.id.tt_l_8);
	      tt_ll_9 = (TextView) findViewById(R.id.tt_l_9);
	      
	      take_action_but = (Button) findViewById(R.id.tt_button1);
	      
	      //FW_China_threat_alert
	      String fw_name = getIntent().getExtras().getString("fw_name");
	      String table_name = fw_name+"_threat_alert";
	      fw_text_view.setText(fw_name);
	      
	      //RelativeLayout rl = (RelativeLayout)findViewById(R.id.threat_page_id);
	      //rl.setBackgroundColor(Color.parseColor("#EEB4B4"));
	      
	      
	      final String [] alert_fields = { 
	    		  "threat_name",
	    		  "threat_severity",
	    		  "threat_subtype",
	    		  
	    		  "threat_receive_time",
	    		  "threat_category",
	    		  "threat_rule",
	    		  "threat_source",
	    		  "threat_dst",
	    		  "threat_time_generated"
	    };
	      
	      
	      ParseQuery<ParseObject> query = ParseQuery.getQuery(table_name);
			query.orderByDescending("updatedAt");
			query.setLimit(1);
			query.findInBackground(new FindCallback<ParseObject>() {
			    public void done(List<ParseObject> scoreList, ParseException e) {
			    	if (e == null) {
			    		//Toast.makeText(Deviceinfopage.this, "fetched data",1000).show();// display toast
			    		for(int i =0; i< scoreList.size(); i++) {
							ParseObject myObj = scoreList.get(i);
							
							for(int j=0; j< alert_fields.length;j++) {
								threat_vals_list.add(j, (String) myObj.get(alert_fields[j]));
							}
							
							
						}
			    		
			    		tt_ll_1.setText(threat_vals_list.get(0));
			    		tt_ll_2.setText(threat_vals_list.get(1));
			    		tt_ll_3.setText(threat_vals_list.get(2));
			    		tt_ll_4.setText(threat_vals_list.get(3));
			    		tt_ll_5.setText(threat_vals_list.get(4));
			    		tt_ll_6.setText(threat_vals_list.get(5));
			    		tt_ll_7.setText(threat_vals_list.get(6));
			    		tt_ll_8.setText(threat_vals_list.get(7));
			    		tt_ll_9.setText(threat_vals_list.get(8));
			    		
			    		
			        } else {
			        	Toast.makeText(AlertPageActivity.this, "Cloud service is down.",1000).show();// display toast
			        }
			    }
			});
			
			
			take_action_but.setOnClickListener(new View.OnClickListener() {
				
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					Toast.makeText(AlertPageActivity.this, "Threat Blocked.",1000).show();// display toast
					
				}
			});
	
	}
	
	
}
