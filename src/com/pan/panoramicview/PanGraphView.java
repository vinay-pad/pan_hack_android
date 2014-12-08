package com.pan.panoramicview;

import java.util.ArrayList;

import android.app.Activity;
import android.graphics.Color;
import android.os.Bundle;
import android.widget.LinearLayout;

import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.GraphView.LegendAlign;
import com.jjoe64.graphview.GraphViewSeries;
import com.jjoe64.graphview.GraphViewSeries.GraphViewSeriesStyle;
import com.jjoe64.graphview.GraphView.GraphViewData;
import com.jjoe64.graphview.GraphViewStyle.GridStyle;
import com.jjoe64.graphview.LineGraphView;


public class PanGraphView extends Activity{
	String fw_name;
	String stat;
	ArrayList<Integer> x_val;
	ArrayList<String> y_val;
	ArrayList<String> y1_val;
	ArrayList<String> y2_val;
	@SuppressWarnings("deprecation")
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.graph_info_page);
		fw_name = getIntent().getExtras().getString("fw_name");
		stat = getIntent().getExtras().getString("stat");
		x_val = getIntent().getIntegerArrayListExtra("x_val");
		y_val = getIntent().getStringArrayListExtra("y_val");
		if (!stat.equals("throughput")){
			y1_val = getIntent().getStringArrayListExtra("y1_val");
			y2_val = getIntent().getStringArrayListExtra("y2_val");
		}
		GraphViewData[] data = new GraphViewData[x_val.size()];
		GraphViewData[] data1 = new GraphViewData[x_val.size()];
		GraphViewData[] data2 = new GraphViewData[x_val.size()];
		
		for (int i = 0; i < x_val.size(); i++) { 
			Integer s_x_val = x_val.get(i);
			String s_y_val = y_val.get(i);
			if (!stat.equals("throughput")){
				String s_y1_val = y1_val.get(i);
				String s_y2_val = y2_val.get(i);
				data1[i] = new GraphViewData(s_x_val.doubleValue(), Double.parseDouble(s_y1_val)+1);
				data2[i] = new GraphViewData(s_x_val.doubleValue(), Double.parseDouble(s_y2_val)+1);
			}
			data[i] = new GraphViewData(s_x_val.doubleValue(), Double.parseDouble(s_y_val)+1);
			
		}
		GraphViewSeriesStyle style = new GraphViewSeriesStyle(Color.rgb(255, 255, 0), 6);
		GraphViewSeriesStyle style1 = new GraphViewSeriesStyle(Color.rgb(255, 0, 0), 6);
		GraphViewSeriesStyle style2 = new GraphViewSeriesStyle(Color.rgb(9, 239, 69), 6);
		GraphViewSeries exampleSeries = null;
		GraphViewSeries exampleSeries1 = null;
		GraphViewSeries exampleSeries2 = null;
		if(stat.equals("cpu")){
			exampleSeries = new GraphViewSeries("Total CPU %", style, data);
			exampleSeries1 = new GraphViewSeries("MgmtSrvr CPU %", style1, data1);
			exampleSeries2 = new GraphViewSeries("Logrcvr CPU %", style2, data2);
		} else if (stat.equals("mem")) {
			exampleSeries = new GraphViewSeries("Total Memory%", style, data);
			exampleSeries1 = new GraphViewSeries("MgmtSrvr Memory %", style1, data1);
			exampleSeries2 = new GraphViewSeries("Logrcvr Memory %", style2, data2);
		
		} else if (stat.equals("throughput")) {
			exampleSeries = new GraphViewSeries("Throughput Kbps", style, data);
		}
		GraphView graphView = new LineGraphView(
		    this // context
		    , fw_name.toString() // heading
			);
		graphView.addSeries(exampleSeries);
		if (!stat.equals("throughput")){
			graphView.addSeries(exampleSeries1);
			graphView.addSeries(exampleSeries2);
		}
		graphView.getGraphViewStyle().setGridStyle(GridStyle.HORIZONTAL);
		graphView.getGraphViewStyle().setGridColor(Color.WHITE);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.BLACK);
		graphView.setShowLegend(true);
		graphView.setLegendAlign(LegendAlign.MIDDLE);
		graphView.getGraphViewStyle().setVerticalLabelsColor(Color.WHITE);
		graphView.getGraphViewStyle().setHorizontalLabelsColor(Color.WHITE);
		graphView.getGraphViewStyle().setLegendBorder(20);
        graphView.getGraphViewStyle().setLegendSpacing(50);
        graphView.getGraphViewStyle().setLegendWidth(350);
		graphView.setScalable(true);
		//graphView.setLegendWidth(200);
		LinearLayout layout = (LinearLayout) findViewById(R.id.graph1);
		layout.addView(graphView);
	}
}
