package com.pan.panoramicview;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.MarkerOptions;
import com.parse.FindCallback;
import com.parse.GetCallback;
import com.parse.ParseException;
import com.parse.ParseObject;
import com.parse.ParseQuery;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

public class Deviceinfopage extends ListActivity{
	
	TextView fw_name;
	TextView connected;
	String passedArg ="JAY_FIXME!";
	Button refresh_but;
	
	Button navi_but1;
	static StringBuffer sb = new StringBuffer();
	
	
	public static ArrayList<String> my_vals_device_info = new ArrayList<String>();
	
	public final String [] status_fields = {
			"conn_status", //0
  		  "total_cpu",	//1
  		  "total_mem",	//2
  		  "throughput",	//3
  		  "active_sess",	//4
  		  "lograte",	//5
  		  "ha_status",	//6
  		  "mgmtsrvr_cpu",	//7
	    	  "mgmtsrvr_mem",	//8
	    	  "dev_shm_usage",	//9
	    	  "opt_pancfg_usage",	//10
	    	  "opt_panlogs_usage",	//11
	    	  "opt_panrepo_usage"	//12
	  };
    
    
    public final String [] status_fields_table = {  //12
  		  "Connection Status",	//0
    		"Total CPU Usage",	//1
  		  "Total Memory Usage",	//2
  		  "Throughput",	//3
  		  "Active Sessions",	//4
  		  "Lograte",	//5
  		  "HA Status",	//6
  		  "Mangement-Server CPU",//7
	    	  "Mangement-Server Memory",	//8
	    	  "Disk-Shared",	//9
	    	  "Disk-Configurations",	//10
	    	  "Disk-Logs",	//11
	    	  "Disk-Repo"	//12
	  };
	
	
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		passedArg = getIntent().getExtras().getString("fw_name");
		//passedArg = passedArg+"_status";
		
		
	      
		List<String> empty_list = new ArrayList<String>();
	      for(int kk =0; kk<status_fields_table.length;kk++) {
	    	  empty_list.add("");
	      }
	      
	      
	      
	      super.onCreate(savedInstanceState);
	      setListAdapter(new MobileArrayAdapter(this, status_fields_table, empty_list));
	      final String table_name = passedArg;
	      
	      update_data(table_name);
	      
	      final Handler handler=new Handler();
	      final Runnable r = new Runnable()
	      {
	          public void run() 
	          {
	        	  //Log.e("JAY", "Running run()");
	        	  update_data(table_name);
	              handler.postDelayed(this, 10000);
	          }
	      };
	      handler.postDelayed(r, 10000);
	      
	      
	      
	 }
	
		public void update_list(String[] status_fields_table,
			ArrayList<String> my_vals) {
		// TODO Auto-generated method stub
			setListAdapter(new MobileArrayAdapter(this, status_fields_table, my_vals));
		}
	   
	  	@Override
	  	public void onListItemClick(ListView l, View v, int position, long id) {
	   
	  		//get selected items
	  		String selectedValue = (String) getListAdapter().getItem(position);
	  		Toast.makeText(this, selectedValue, Toast.LENGTH_SHORT).show();
	  		
	  		String status_arg = passedArg+"_status";
	  		if(position == 1 ) {
	  			//CPU usage
	  			ParseQuery<ParseObject> query = ParseQuery.getQuery(status_arg);
				
				query.orderByAscending("updatedAt");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				    	ArrayList<String> value_y = new ArrayList<String>();
				    	ArrayList<Integer> value_x = new ArrayList<Integer>();
				    	ArrayList<String> value_y2 = new ArrayList<String>();
				    	ArrayList<String> value_y3 = new ArrayList<String>();
				    	Integer first_val = 0;
				    	if (e == null) {
				    		for(int i =0; i< scoreList.size(); i++) {
				    			ParseObject myObj = scoreList.get(i);
				    			String total_cpu = (String) myObj.get("total_cpu");
				    			String mgmtsrvr_cpu = (String) myObj.get("mgmtsrvr_cpu");
				    			String logrcvr_cpu = (String) myObj.get("logrcvr_cpu");
				    			java.util.Date b_s_updated = myObj.getUpdatedAt();
				    			Long date_l = b_s_updated.getTime();
				    			if (i != 0){
				    				value_x.add(date_l.intValue()/1000 - first_val);
				    			} else {
				    				first_val = date_l.intValue()/1000;
				    				value_x.add(1);
				    			}
				    			value_y.add(total_cpu);
				    			value_y2.add(mgmtsrvr_cpu);
				    			value_y3.add(logrcvr_cpu);
				    			
				    		}
				    		Toast.makeText(Deviceinfopage.this, "CPU", 1000).show();// display toast
			                 Intent i=new Intent(
			                		 Deviceinfopage.this,
			                         PanGraphView.class);
			                 i.putExtra("fw_name", passedArg.toString());
			                 i.putExtra("stat", "cpu");
			                 i.putStringArrayListExtra("y_val", value_y);
			                 i.putStringArrayListExtra("y1_val", value_y2);
			                 i.putStringArrayListExtra("y2_val", value_y3);
			                 i.putIntegerArrayListExtra("x_val", value_x);
			                 startActivity(i);
				    	}
				    }
				});
	  			
	  		} else if( position == 2) {
	  			//Memory usage
	  			ParseQuery<ParseObject> query = ParseQuery.getQuery(status_arg);
				
				query.orderByAscending("updatedAt");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				    	ArrayList<String> value_y = new ArrayList<String>();
				    	ArrayList<Integer> value_x = new ArrayList<Integer>();
				    	ArrayList<String> value_y2 = new ArrayList<String>();
				    	ArrayList<String> value_y3 = new ArrayList<String>();
				    	Integer first_val = 0;
				    	if (e == null) {
				    		for(int i =0; i< scoreList.size(); i++) {
				    			ParseObject myObj = scoreList.get(i);
				    			String total_mem = (String) myObj.get("total_mem");
				    			String mgmtsrvr_mem = (String) myObj.get("mgmtsrvr_mem");
				    			String logrcvr_mem = (String) myObj.get("logrcvr_mem");
				    			java.util.Date b_s_updated = myObj.getUpdatedAt();
				    			Long date_l = b_s_updated.getTime();
				    			if (i != 0){
				    				value_x.add(date_l.intValue()/1000 - first_val);
				    			} else {
				    				first_val = date_l.intValue()/1000;
				    				value_x.add(1);
				    			}
				    			value_y.add(total_mem);
				    			value_y2.add(mgmtsrvr_mem);
				    			value_y3.add(logrcvr_mem);
				    			
				    		}
				    		Toast.makeText(Deviceinfopage.this, "Memory", 1000).show();// display toast
			                 Intent i=new Intent(
			                		 Deviceinfopage.this,
			                         PanGraphView.class);
			                 i.putExtra("fw_name", passedArg.toString());
			                 i.putExtra("stat", "mem");
			                 i.putStringArrayListExtra("y_val", value_y);
			                 i.putStringArrayListExtra("y1_val", value_y2);
			                 i.putStringArrayListExtra("y2_val", value_y3);
			                 i.putIntegerArrayListExtra("x_val", value_x);
			                 startActivity(i);
				    	}
				    }
				});
	  		} else if (position == 3) {
	  			//disk
	  			ParseQuery<ParseObject> query = ParseQuery.getQuery(status_arg);
				
				query.orderByAscending("updatedAt");
				query.findInBackground(new FindCallback<ParseObject>() {
				    public void done(List<ParseObject> scoreList, ParseException e) {
				    	ArrayList<String> value_y = new ArrayList<String>();
				    	ArrayList<Integer> value_x = new ArrayList<Integer>();
				    	ArrayList<String> value_y2 = new ArrayList<String>();
				    	ArrayList<String> value_y3 = new ArrayList<String>();
				    	Integer first_val = 0;
				    	if (e == null) {
				    		for(int i =0; i< scoreList.size(); i++) {
				    			ParseObject myObj = scoreList.get(i);
				    			String throughput = (String) myObj.get("throughput");
				    			java.util.Date b_s_updated = myObj.getUpdatedAt();
				    			Long date_l = b_s_updated.getTime();
				    			if (i != 0){
				    				value_x.add(date_l.intValue()/1000 - first_val);
				    			} else {
				    				first_val = date_l.intValue()/1000;
				    				value_x.add(1);
				    			}
				    			value_y.add(throughput);
				    			
				    		}
				    		Toast.makeText(Deviceinfopage.this, "Memory", 1000).show();// display toast
			                 Intent i=new Intent(
			                		 Deviceinfopage.this,
			                         PanGraphView.class);
			                 i.putExtra("fw_name", passedArg.toString());
			                 i.putExtra("stat", "throughput");
			                 i.putStringArrayListExtra("y_val", value_y);
			                 i.putIntegerArrayListExtra("x_val", value_x);
			                 startActivity(i);
				    	}
				    }
				});
	  		}
	  		
	  		
	   
	  	}
	      
	      
	     
	      
	      
		
		
		
				
	
	
	
	public void update_data(String fwname) {
		// TODO Auto-generated method stub
		
		//Log.e("JAY", "Running update_data()");
		
		String status_table = fwname +"_status";
		
		ParseQuery<ParseObject> query = ParseQuery.getQuery(status_table);
		query.orderByDescending("updatedAt");
		query.setLimit(1);
		query.findInBackground(new FindCallback<ParseObject>() {
		    public void done(List<ParseObject> scoreList, ParseException e) {
		    	if (e == null) {
		    		//Toast.makeText(Deviceinfopage.this, "fetched data",1000).show();// display toast
		    		for(int i =0; i< scoreList.size(); i++) {
						ParseObject myObj = scoreList.get(i);
						
						for(int j=0; j<status_fields.length;j++) {
							my_vals_device_info.add((String) myObj.get(status_fields[j]));
							Log.e("JAY", "Index is "+j + " sending "+status_fields[j] +" with val "+ my_vals_device_info.get(j));
						}
					}
		    		ArrayList<String> temp_val_device_info = null;
		    		//temp_val_device_info.clear();
		    		temp_val_device_info = (ArrayList<String>) my_vals_device_info.clone();
		    		update_list(status_fields_table, temp_val_device_info);
		    		my_vals_device_info.clear();
		    	} 
		    }
		 });
		
		
	}
	

}
