package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

import android.animation.Animator;
import android.animation.Animator.AnimatorListener;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.transition.Explode;
import android.transition.Slide;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.view.animation.AccelerateInterpolator;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private static final int MAIN_FAB_ANIMATION_TIME = 250;
	private static final int LOCATION_FAB_ANIMATION_TIME = 250;
	private static final int TIME_FAB_ANIMATION_TIME = 250;
	
	private Context context;
	private FloatingActionButton btnAddTrigger;
	private FloatingActionButton btnLocation;
	private FloatingActionButton btnTime;
	private int windowHeight;
	private int windowWidth;
	
	private View.OnClickListener triggerFabClickListener = new View.OnClickListener() {
		@Override
		public void onClick(View view) {
			Intent intent = new Intent(context, NewTriggerActivity.class);
			if (view.getId() == btnLocation.getId()) {
				intent.putExtra(
						context.getResources().getString(R.string.extra_trigger_type),
						context.getResources().getString(R.string.trigger_type_location));
			} else if (view.getId() == btnTime.getId()) {
				intent.putExtra(
						context.getResources().getString(R.string.extra_trigger_type),
						context.getResources().getString(R.string.trigger_type_time));
			}
			startActivity(intent);
		}
	};

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		
		getWindow().requestFeature(Window.FEATURE_NO_TITLE);
		getWindow().requestFeature(Window.FEATURE_CONTENT_TRANSITIONS);
		getWindow().setEnterTransition(new Slide());
		getWindow().setExitTransition(new Explode());
		
		setContentView(R.layout.activity_main);
		context = this;
		
		windowHeight = getBaseContext().getResources().getDisplayMetrics().heightPixels;
		windowWidth = getBaseContext().getResources().getDisplayMetrics().widthPixels;
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size), 
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		params.bottomMargin = (int) context.getResources().getDimension(
				R.dimen.standard_content_margin);
		
		btnLocation = new FloatingActionButton(this);
		btnLocation.setLayoutParams(params);
		btnLocation.setDrawable(context.getResources().getDrawable(R.drawable.ic_location));
		btnLocation.setColor(context.getResources().getColor(R.color.theme_accent));
		btnLocation.setElevation(getResources().getDimension(R.dimen.fab_elevation));
		btnLocation.setOnClickListener(triggerFabClickListener);
		
		btnTime = new FloatingActionButton(this);
		btnTime.setLayoutParams(new RelativeLayout.LayoutParams(params));
		btnTime.setDrawable(context.getResources().getDrawable(R.drawable.ic_time));
		btnTime.setColor(context.getResources().getColor(R.color.theme_accent));
		btnTime.setElevation(getResources().getDimension(R.dimen.fab_elevation));
		btnTime.setOnClickListener(triggerFabClickListener);
		
		final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.mainLayout);
		
		int resourceId = context.getResources().
				getIdentifier("navigation_bar_height", "dimen", "android");
		float fabSize = context.getResources().getDimension(R.dimen.fab_size);
		float navbarHeight = resourceId > 0 ?
				context.getResources().getDimensionPixelSize(resourceId) : 0;
		final float movementInX = (windowWidth / 2) - (fabSize / 2) -
				context.getResources().getDimension(R.dimen.fab_margin_side);
		final float movementInY = windowHeight - 
				context.getResources().getDimension(R.dimen.top_sheet_height_main) -
				fabSize - navbarHeight;
	
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.animate().setInterpolator(new AccelerateInterpolator())
							  .translationXBy(movementInX)
							  .translationYBy(movementInY)
							  .scaleX(0)
							  .scaleY(0);				
				
				mainLayout.addView(btnTime);
				btnTime.animate().setInterpolator(new AccelerateInterpolator())
								 .translationXBy(250);				
				mainLayout.addView(btnLocation);
				btnLocation.animate().setInterpolator(new AccelerateInterpolator())
				           .translationXBy(-250);
				
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
