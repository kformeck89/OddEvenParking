package com.kformeck.oddevenparking;

import com.kformeck.widgets.FloatingActionButton;

import android.app.Activity;
import android.content.Context;
import android.os.Bundle;
import android.transition.Explode;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Animation.AnimationListener;
import android.view.animation.AnimationSet;
import android.view.animation.ScaleAnimation;
import android.view.animation.TranslateAnimation;

public class MainActivity extends Activity {
	private Context context;
	private FloatingActionButton btnAddTrigger;
	private TranslateAnimation fabTranlateAnimation;
	private ScaleAnimation fabShrinkAnimation;
	private int windowHeight;
	private int windowWidth;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		context = this;
		
		windowHeight = getBaseContext().getResources().getDisplayMetrics().heightPixels;
		windowWidth = getBaseContext().getResources().getDisplayMetrics().widthPixels;
		
		btnAddTrigger = (FloatingActionButton) findViewById(R.id.btnAddTrigger);
		btnAddTrigger.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View view) {
				view.startAnimation(fabTranlateAnimation);
			}
		});
		
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
		
		
		fabTranlateAnimation = new TranslateAnimation(
				0, movementInX, 0, movementInY);
		fabTranlateAnimation.setDuration(1000);
		fabTranlateAnimation.setFillAfter(true);
		
		fabShrinkAnimation = new ScaleAnimation(
				1, 0, 1, 0);
		fabShrinkAnimation.setDuration(1000);
		fabShrinkAnimation.setFillAfter(true);
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
