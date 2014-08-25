package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {
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
		mainFabTranlateAnimation.setDuration(750);
		mainFabTranlateAnimation.setFillAfter(true);
		
		mainFabShrinkAnimation = new ScaleAnimation(1, 0, 1, 0);
		mainFabShrinkAnimation.setDuration(750);
		mainFabShrinkAnimation.setInterpolator(mainFabTranlateAnimation.getInterpolator());
		mainFabShrinkAnimation.setFillAfter(true);
		
		locationFabTranslateAnimation = new TranslateAnimation(0, 150, 0, 0);
		locationFabTranslateAnimation.setDuration(750);
		locationFabTranslateAnimation.setFillAfter(true);
		
		locationFabShrinkAnimation = new ScaleAnimation(0, 1, 0, 1);
		locationFabShrinkAnimation.setDuration(750);
		locationFabShrinkAnimation.setInterpolator(locationFabTranslateAnimation.getInterpolator());
		locationFabShrinkAnimation.setFillAfter(true);
		
		timeFabTranslateAnimation = new TranslateAnimation(0, -150, 0, 0);
		timeFabTranslateAnimation.setDuration(750);
		timeFabTranslateAnimation.setFillAfter(true);
		
		timeFabShrinkAnimation = new ScaleAnimation(250, 250, 250, 250);
		timeFabShrinkAnimation.setDuration(750);
		timeFabShrinkAnimation.setInterpolator(timeFabTranslateAnimation.getInterpolator());
		timeFabShrinkAnimation.setFillAfter(true);
		
		mainFabAnimationSet = new AnimationSet(false);
		mainFabAnimationSet.setFillAfter(true);
		locationFabAnimationSet = new AnimationSet(false);
		locationFabAnimationSet.setFillAfter(true);
		timeFabAnimationSet = new AnimationSet(false);
		timeFabAnimationSet.setFillAfter(true);
		mainFabAnimationSet.addAnimation(mainFabShrinkAnimation);
		mainFabAnimationSet.addAnimation(mainFabTranlateAnimation);
		locationFabAnimationSet.addAnimation(locationFabShrinkAnimation);
		locationFabAnimationSet.addAnimation(locationFabTranslateAnimation);
		timeFabAnimationSet.addAnimation(timeFabShrinkAnimation);
		timeFabAnimationSet.addAnimation(timeFabTranslateAnimation);
		
		btnLocation = new FloatingActionButton(this);
		btnLocation.setDrawable(context.getResources().getDrawable(R.drawable.ic_location));
		btnLocation.setBackgroundColor(context.getResources().getColor(R.color.theme_primary));
		btnLocation.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
		
		btnTime = new FloatingActionButton(this);
		btnTime.setDrawable(context.getResources().getDrawable(R.drawable.ic_clock));
		btnTime.setBackgroundColor(context.getResources().getColor(R.color.theme_primary));
		btnTime.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				
			}
		});
		
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.startAnimation(mainFabAnimationSet);
				btnTime.setVisibility(View.VISIBLE);
				btnLocation.setVisibility(View.VISIBLE);
				btnLocation.startAnimation(locationFabAnimationSet);
				btnTime.startAnimation(timeFabAnimationSet);
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
