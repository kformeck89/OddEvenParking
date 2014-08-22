package com.kformeck.oddevenparking;

import com.kformeck.widgets.ImageToggleButton;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.CompoundButton;

public class NewTriggerActivity extends Activity {
	private TimeSelectionFragment timeSelectionFragment;
	private LocationSelectionFragment locationSelectionFragment;
	private ImageToggleButton btnLocation;
	private ImageToggleButton btnTime;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_trigger);

		if (savedInstanceState != null) {
			return;
		}
		
		timeSelectionFragment = new TimeSelectionFragment();
		locationSelectionFragment = new LocationSelectionFragment();
		final FragmentManager fragmentManager = getFragmentManager();
	
		btnLocation = (ImageToggleButton)findViewById(R.id.btnLocation);
		btnLocation.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (fragmentManager.findFragmentById(android.R.id.content) == null) {
					fragmentManager.beginTransaction()
								   .add(
									    android.R.id.content, 
										locationSelectionFragment)
								   .commit();
				} else {
					fragmentManager.beginTransaction()
								   .replace(
										android.R.id.content,
										locationSelectionFragment)
								   .commit();
				}
			}
		});
		
		btnTime = (ImageToggleButton)findViewById(R.id.btnTime);
		btnTime.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
			@Override
			public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
				if (fragmentManager.findFragmentById(android.R.id.content) == null) {
					fragmentManager.beginTransaction()
					        	   .add(
					        	        android.R.id.content,
					        	        timeSelectionFragment)
					        	   .commit();						
				} else {
					fragmentManager.beginTransaction()
								   .replace(
								        android.R.id.content,
								        timeSelectionFragment)
								   .commit();
				}
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
