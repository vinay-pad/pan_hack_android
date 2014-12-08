package com.pan.panoramicview;
import java.util.List;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;
 
public class MobileArrayAdapter extends ArrayAdapter<String> {
	private final Context context;
	private final String[]  lables;
	public List<String> values;
 
	public MobileArrayAdapter(Context context, String[] lables, List<String> values) {
		super(context, R.layout.list_stats, values);
		this.context = context;
		this.values = values;
		this.lables = lables;
	}
 
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		LayoutInflater inflater = (LayoutInflater) context
			.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
 
		View rowView = inflater.inflate(R.layout.list_stats, parent, false);
		TextView textView_Tag = (TextView) rowView.findViewById(R.id.TAG_NAME);
		TextView textView_val = (TextView) rowView.findViewById(R.id.label);
		Log.e("JAY", "Getting index val "+position);
		textView_val.setText(values.get(position));
		textView_Tag.setText(lables[position]);
		
		return rowView;
	}
}