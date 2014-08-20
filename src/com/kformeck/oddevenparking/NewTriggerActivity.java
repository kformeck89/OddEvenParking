package com.kformeck.oddevenparking;

import com.kformeck.widgets.CompoundImageButton;

import android.app.Activity;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;
import android.widget.TextView;

public class NewTriggerActivity extends Activity {
	private CompoundImageButton btnLocation;
	private CompoundImageButton btnTime;
	private TextView thing;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_trigger);
		
		thing = (TextView)findViewById(R.id.thing);
		btnLocation = (CompoundImageButton)findViewById(R.id.btnLocation);
		btnLocation.setChecked(false);
		btnLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				thing.setText("Location Trigger Selected");
			}
		});
		btnTime = (CompoundImageButton)findViewById(R.id.btnTime);
		btnTime.setChecked(false);
		btnTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				thing.setText("Time Trigger Selected");
			}
		});
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}
}
