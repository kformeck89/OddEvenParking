package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

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
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.LinearLayout;

public class MainActivity extends Activity {
	private static final int MAIN_FAB_ANIMATION_TIME = 250;
	private static final int LOCATION_FAB_ANIMATION_TIME = 250;
	private static final int TIME_FAB_ANIMATION_TIME = 250;
	
	private Context context;
	private FloatingActionButton btnAddTrigger;
	private FloatingActionButton btnLocation;
	private FloatingActionButton btnTime;
	private AnimationSet mainFabAnimationSet;
	private AnimationSet locationFabAnimationSet;
	private AnimationSet timeFabAnimationSet;
	private TranslateAnimation mainFabTranlateAnimation;
	private ScaleAnimation mainFabShrinkAnimation;
	private TranslateAnimation locationFabTranslateAnimation;
	private ScaleAnimation locationFabShrinkAnimation;
	private TranslateAnimation timeFabTranslateAnimation;
	private ScaleAnimation timeFabShrinkAnimation;
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
	
		setupMainFab();
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
	
	private void setupLocationFab() {		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size),
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.weight = 0;
		
		locationFabTranslateAnimation = new TranslateAnimation(250, 0, 0, 0);
		locationFabTranslateAnimation.setDuration(LOCATION_FAB_ANIMATION_TIME);
		locationFabTranslateAnimation.setFillAfter(true);
		
		locationFabShrinkAnimation = new ScaleAnimation(0, 1, 0, 1);
		locationFabShrinkAnimation.setDuration(LOCATION_FAB_ANIMATION_TIME);
		locationFabShrinkAnimation.setInterpolator(locationFabTranslateAnimation.getInterpolator());
		locationFabShrinkAnimation.setFillAfter(true);
		
		locationFabAnimationSet = new AnimationSet(false);
		locationFabAnimationSet.setFillAfter(true);
		locationFabAnimationSet.addAnimation(locationFabShrinkAnimation);
		locationFabAnimationSet.addAnimation(locationFabTranslateAnimation);
		
		btnLocation = new FloatingActionButton(this);
		btnLocation.setLayoutParams(params);
		btnLocation.setDrawable(context.getResources().getDrawable(R.drawable.ic_location));
		btnLocation.setColor(context.getResources().getColor(R.color.theme_accent));
		btnLocation.setElevation(getResources().getDimension(R.dimen.fab_elevation));
		btnLocation.setOnClickListener(triggerFabClickListener);
	}
	private void setupTimeFab() {
		final LinearLayout container = (LinearLayout)findViewById(R.id.triggerFabContainer);
		
		LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size),
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.weight = 0;
		
		timeFabTranslateAnimation = new TranslateAnimation(-250, 0, 0, 0);
		timeFabTranslateAnimation.setDuration(TIME_FAB_ANIMATION_TIME);
		timeFabTranslateAnimation.setFillAfter(true);
		
		timeFabShrinkAnimation = new ScaleAnimation(0, 1, 0, 1);
		timeFabShrinkAnimation.setDuration(TIME_FAB_ANIMATION_TIME);
		timeFabShrinkAnimation.setInterpolator(timeFabTranslateAnimation.getInterpolator());
		timeFabShrinkAnimation.setFillAfter(true);
		
		timeFabAnimationSet = new AnimationSet(false);
		timeFabAnimationSet.setFillAfter(true);
		timeFabAnimationSet.addAnimation(timeFabShrinkAnimation);
		timeFabAnimationSet.addAnimation(timeFabTranslateAnimation);
		timeFabAnimationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) { }
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationStart(Animation animation) {
				setupLocationFab();
				container.addView(btnLocation);
				btnLocation.startAnimation(locationFabAnimationSet);
			}
		});
		
		btnTime = new FloatingActionButton(this);
		btnTime.setLayoutParams(params);
		btnTime.setDrawable(context.getResources().getDrawable(R.drawable.ic_time));
		btnTime.setColor(context.getResources().getColor(R.color.theme_accent));
		btnTime.setElevation(getResources().getDimension(R.dimen.fab_elevation));
		btnTime.setOnClickListener(triggerFabClickListener);
	}
	private void setupMainFab() {
		final LinearLayout container = (LinearLayout)findViewById(R.id.triggerFabContainer);
		
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
		
		mainFabTranlateAnimation = new TranslateAnimation(0, movementInX, 0, movementInY);
		mainFabTranlateAnimation.setDuration(MAIN_FAB_ANIMATION_TIME);
		mainFabTranlateAnimation.setFillAfter(true);
		
		mainFabShrinkAnimation = new ScaleAnimation(1, 0, 1, 0);
		mainFabShrinkAnimation.setDuration(MAIN_FAB_ANIMATION_TIME);
		mainFabShrinkAnimation.setInterpolator(mainFabTranlateAnimation.getInterpolator());
		mainFabShrinkAnimation.setFillAfter(true);
		
		mainFabAnimationSet = new AnimationSet(false);
		mainFabAnimationSet.setFillAfter(true);
		mainFabAnimationSet.addAnimation(mainFabShrinkAnimation);
		mainFabAnimationSet.addAnimation(mainFabTranlateAnimation);
		mainFabAnimationSet.setAnimationListener(new AnimationListener() {
			@Override
			public void onAnimationEnd(Animation animation) {
				setupTimeFab();
				container.addView(btnTime);
				btnTime.startAnimation(timeFabAnimationSet);
			}
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationStart(Animation animation) { }
			
		});
	
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.startAnimation(mainFabAnimationSet);
			}
		});
	}
}
