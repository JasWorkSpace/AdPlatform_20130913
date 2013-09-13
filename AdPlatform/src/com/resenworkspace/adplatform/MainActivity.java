package com.resenworkspace.adplatform;

import com.ResenWorkSpace.ResenWorkSpace.ResenWorkSpaceConfig;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.widget.TextView;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		TextView workspace = (TextView) findViewById(R.id.MainactivityTextView);
		workspace.setText(getConfig());
	}
    private String getConfig(){
    	StringBuffer sb = new StringBuffer();
    	ResenWorkSpaceConfig config = ResenWorkSpaceConfig.getInstance();
    	sb.append(config.getWorkSpace()).append("\n")
 	      .append(config.getEmail()).append("\n")
    	  .append(config.getWorkSpaceCompany()).append("\n");
    	return sb.toString();
    }
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

}
