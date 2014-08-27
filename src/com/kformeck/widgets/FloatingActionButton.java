package com.kformeck.widgets;

import com.kformeck.oddevenparking.R;

import android.animation.ObjectAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Point;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.util.AttributeSet;
import android.view.Display;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Interpolator;

@SuppressLint("ClickableViewAccessibility")
public class FloatingActionButton extends View {
	private final Paint buttonPaint;
	private final Paint drawablePaint;
	private final Interpolator interpolator;
	
	private Bitmap bitmap;
	private int color;
	private int screenHeight;
	private float currentY;
	private boolean hidden;
	
	public FloatingActionButton(Context context) { this(context, null); }
	public FloatingActionButton(Context context, AttributeSet attrs) { this(context, attrs, 0); }
	public FloatingActionButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		hidden = false;
		interpolator = new AccelerateDecelerateInterpolator();
		buttonPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		drawablePaint = new Paint(Paint.ANTI_ALIAS_FLAG);
		
		TypedArray a = getContext().obtainStyledAttributes(
				attrs, R.styleable.FloatingActionButton);
		color = a.getColor(R.styleable.FloatingActionButton_color, Color.WHITE);
		buttonPaint.setStyle(Paint.Style.FILL);
		buttonPaint.setColor(color);
		
		float radius = a.getFloat(R.styleable.FloatingActionButton_shadowRadius, 10.0f);
		float dx = a.getFloat(R.styleable.FloatingActionButton_shadowDx, 0.0f);
		float dy = a.getFloat(R.styleable.FloatingActionButton_shadowDy, 3.5f);
		int shadowColor = a.getInteger(
				R.styleable.FloatingActionButton_shadowColor, Color.argb(100, 0, 0, 0));
		buttonPaint.setShadowLayer(radius, dx, dy, shadowColor);
		
		Drawable drawable = a.getDrawable(R.styleable.FloatingActionButton_drawable);
		if (drawable != null) {
			bitmap = ((BitmapDrawable) drawable).getBitmap();
		}
		setWillNotDraw(false);
		setLayerType(View.LAYER_TYPE_SOFTWARE, null);
		
		WindowManager windowManager = (WindowManager)
				context.getSystemService(Context.WINDOW_SERVICE);
		Display display = windowManager.getDefaultDisplay();
		Point size = new Point();
		display.getSize(size);
		screenHeight = size.y;
		
		a.recycle();
	}

	public void hide(boolean hide) {
		if (hidden != hide) {
			float offset;
			if (hidden) {
				offset = currentY;
			} else {
				currentY = getY();
				offset = screenHeight;
			}
			hidden = hide;
			ObjectAnimator animator = ObjectAnimator.ofFloat(this, "Y", offset);
			animator.setInterpolator(interpolator);
			animator.start();
		}
	}
	@Override
	public void onDraw(Canvas canvas) {
		canvas.drawCircle(
				getWidth() / 2, 
				getHeight() / 2,
				(float) (getWidth() / 2.6),	
				buttonPaint);
		if (bitmap != null) {
			canvas.drawBitmap(
					bitmap, 
					(getWidth() - bitmap.getWidth()) / 2,
					(getHeight() - bitmap.getHeight()) / 2, 
					drawablePaint);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		int tempColor;
		if (event.getAction() == MotionEvent.ACTION_UP) {
			tempColor = this.color;
		} else {
			tempColor = darkenColor(color);
		}
		buttonPaint.setColor(tempColor);
		invalidate();
		return super.onTouchEvent(event);
	}
	@Override
	public boolean performClick() { return super.performClick(); }
	private int darkenColor(int color) {
		float[] hsv = new float[3];
		Color.colorToHSV(color, hsv);
		hsv[2] *= 0.8f;
		return Color.HSVToColor(hsv);
	}
	
 	public void setColor(int color) {
		this.color = color;
		buttonPaint.setColor(color);
		invalidate();
	}
	public void setDrawable(Drawable drawable) {
		bitmap = ((BitmapDrawable) drawable).getBitmap();
		invalidate();
	}
}
