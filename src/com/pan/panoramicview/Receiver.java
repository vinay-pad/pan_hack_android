package com.pan.panoramicview;

import org.json.JSONException;
import org.json.JSONObject;

import com.parse.ParsePushBroadcastReceiver;

import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class Receiver extends ParsePushBroadcastReceiver {

	
	@Override
    public void onPushOpen(Context context, Intent intent) {
        
		String dataString = null;
		String uriString = null;
        try {
            JSONObject pushData = new JSONObject(intent.getStringExtra("com.parse.Data"));
            dataString = pushData.optString("title");
            uriString = pushData.optString("uri");
        } catch (JSONException e) {
        	
            Log.v("com.parse.ParsePushReceiver", "Unexpected JSONException when receiving push data: ", e);
        }
        
        
        Intent i = null;
        if( dataString.equals("THREAT")) {
        	i = new Intent(context, AlertPageActivity.class);
        } else if(dataString.equals("System Log")){
        	i = new Intent(context, SystemLogPageActivity.class);
        } else if(dataString.equals("Connection Status")) {
        	i = new Intent(context, StatusConnActivity.class);
        	
        } else {
        	i = new Intent(context, MainActivity.class);
        }
        
        
        i.putExtra("fw_name", uriString);	
        i.putExtras(intent.getExtras());
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        
        context.startActivity(i);
        
    }
				
}
 