package com.pan.panoramicview;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class StatusConnActivity extends Activity{
	TextView status_txt;
	
	protected void onCreate(Bundle savedInstanceState) {
	      super.onCreate(savedInstanceState);
	      setContentView(R.layout.statusalertpage);
	      status_txt = (TextView) findViewById(R.id.status_text);
	      String status_t = getIntent().getExtras().getString("fw_name");
	      String str = status_t + " is Disconnected!!";
	      status_txt.setText(str);
	}
}
