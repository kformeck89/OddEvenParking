package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;

public class MainActivity extends Activity {
	private Context context;
	private FloatingActionButton btnAddTrigger;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				Intent triggerIntent = new Intent(context, NewTriggerActivity.class);
				startActivity(triggerIntent);
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
