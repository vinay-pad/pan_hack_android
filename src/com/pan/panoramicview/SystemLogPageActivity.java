package com.pan.panoramicview;

import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.ArrayList;
import java.util.List;

import org.w3c.dom.Text;

import com.parse.FindCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class SystemLogPageActivity extends Activity{

	TextView fw_text_view;
	Button ssaction;
	TextView 	st_ll_1, st_ll_2, st_ll_3, st_ll_4,st_ll_5; 
	public static ArrayList<String> sys_vals_list = new ArrayList<String>();
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.systemlogpage);
	      
	      //FW_China_threat_alert
	      String fw_name = getIntent().getExtras().getString("fw_name");
	      String table_name = fw_name+"_sys_alert";
	      
	      fw_text_view = (TextView) findViewById(R.id.sfirewall_textView);
	      st_ll_1 = (TextView) findViewById(R.id.st_l_1);
	      st_ll_2 = (TextView) findViewById(R.id.st_l_2);
	      st_ll_3 = (TextView) findViewById(R.id.st_l_3);
	      st_ll_4 = (TextView) findViewById(R.id.st_l_4);
	      st_ll_5 = (TextView) findViewById(R.id.st_l_5);
	      //ssaction = (Button) findViewById(R.id.st_button1);
	      //ssaction.setVisibility(View.GONE);
	      
	      fw_text_view.setText(fw_name);
	      
	      //RelativeLayout rl = (RelativeLayout)findViewById(R.id.threat_page_id);
	      //rl.setBackgroundColor(Color.parseColor("#3F6065"));
	      
	      
	      
	      
	      final String [] syslog_fields = { 
	    		  "sys_logseverity",
	    		  "sys_logtype",
	    		  "sys_logsubtype",
	    		  "sys_logtime_generated",
	    		  "sys_log_opaque"
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
							
							for(int j=0; j< syslog_fields.length;j++) {
								sys_vals_list.add(j, (String) myObj.get(syslog_fields[j]));
							}
							
							
						}
			    		st_ll_1.setText(sys_vals_list.get(0));
			    		st_ll_2.setText(sys_vals_list.get(1));
			    		st_ll_3.setText(sys_vals_list.get(2));
			    		st_ll_4.setText(sys_vals_list.get(3));
			    		st_ll_5.setText(sys_vals_list.get(4));
			    		
			    		
			        } else {
			        	Toast.makeText(SystemLogPageActivity.this, "Cloud is down now!",1000).show();// display toast
			        }
			    }
			});
			
			
		
	}
	
	
	
	
}
