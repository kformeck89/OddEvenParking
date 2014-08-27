package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;
import android.widget.RelativeLayout;

public class MainActivity extends Activity {
	private static final int MAIN_FAB_ANIMATION_TIME = 500;
	private static final int LOCATION_FAB_ANIMATION_TIME = 500;
	private static final int TIME_FAB_ANIMATION_TIME = 500;
	
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

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		windowHeight = getBaseContext().getResources().getDisplayMetrics().heightPixels;
		windowWidth = getBaseContext().getResources().getDisplayMetrics().widthPixels;
		
		setupLocationFab();
		setupTimeFab();
		setupMainFab();
		
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.startAnimation(mainFabAnimationSet);
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
	
	private void setupLocationFab() {		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size),
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		locationFabTranslateAnimation = new TranslateAnimation(0, 150, 0, 0);
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
		btnLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
	}
	private void setupTimeFab() {
		final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.layoutMain);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size),
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		timeFabTranslateAnimation = new TranslateAnimation(0, -150, 0, 0);
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
				mainLayout.addView(btnLocation);
				btnLocation.startAnimation(locationFabAnimationSet);
			}
		});
		
		btnTime = new FloatingActionButton(this);
		btnTime.setLayoutParams(params);
		btnTime.setDrawable(context.getResources().getDrawable(R.drawable.ic_time));
		btnTime.setColor(context.getResources().getColor(R.color.theme_accent));
		btnTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
	}
	private void setupMainFab() {
		final RelativeLayout mainLayout = (RelativeLayout)findViewById(R.id.layoutMain);
		
		RelativeLayout.LayoutParams params = new RelativeLayout.LayoutParams(
				(int) context.getResources().getDimension(R.dimen.fab_size),
				(int) context.getResources().getDimension(R.dimen.fab_size));
		params.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM);
		params.addRule(RelativeLayout.CENTER_HORIZONTAL);
		
		int resourceId = context.getResources()
				.getIdentifier("navigation_bar_height", "dimen", "android");
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
				mainLayout.addView(btnTime);
				btnTime.startAnimation(timeFabAnimationSet);
			}
			@Override
			public void onAnimationRepeat(Animation animation) { }
			@Override
			public void onAnimationStart(Animation animation) { }
			
		});
	}
}
