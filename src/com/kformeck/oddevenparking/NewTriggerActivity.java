package com.kformeck.oddevenparking;

import android.app.Activity;
import android.app.FragmentManager;
import android.os.Bundle;

public class NewTriggerActivity extends Activity {
	private LocationSelectionFragment locationFragment;
	private TimeSelectionFragment timeFragment;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_new_trigger);
		
		String triggerType = getIntent().getExtras()
										.getString(getResources().getString(
												R.string.extra_trigger_type));
		
		FragmentManager fragmentManager = getFragmentManager();
		
		locationFragment = new LocationSelectionFragment();
		timeFragment = new TimeSelectionFragment();
		
		if (triggerType != null) {
			if (triggerType.equals(getResources().getString(R.string.trigger_type_location))) {
				if (fragmentManager.findFragmentById(android.R.id.content) == null) {
					fragmentManager.beginTransaction()
								   .add(android.R.id.content, locationFragment)
								   .commit();
				} else {
					fragmentManager.beginTransaction()
								   .replace(android.R.id.content, locationFragment)
								   .commit();
				}
			} else if (triggerType.equals(getResources().getString(R.string.trigger_type_time))) {
				if (fragmentManager.findFragmentById(android.R.id.content) == null) {
					fragmentManager.beginTransaction()
								   .add(android.R.id.content, timeFragment)
								   .commit();
				} else {
					fragmentManager.beginTransaction()
								   .replace(android.R.id.content, timeFragment)
								   .commit();
				}
			}
		}
	}
}
