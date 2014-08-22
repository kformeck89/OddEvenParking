package com.kformeck.widgets;

import com.kformeck.oddevenparking.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ToggleButton;

public class ImageToggleButton extends ToggleButton {
	private static final int ALPHA = 128;
	private Context context;
	private RippleDrawable ripple;
	private Paint backgroundBrush;
	private Bitmap bmpSelected;
	private Bitmap bmpUnselected;

	public ImageToggleButton(Context context) { this(context, null); }
	public ImageToggleButton(Context context, AttributeSet attrs) { this(context, attrs, 0); }
	public ImageToggleButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		this.context = context;
		
		backgroundBrush = new Paint();
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ImageToggleButton);
		
		Drawable selected = a.getDrawable(R.styleable.ImageToggleButton_drawable_selected);
		Drawable unselected = a.getDrawable(R.styleable.ImageToggleButton_drawable_unselected);

		int backgroundColor = a.getColor(R.styleable.ImageToggleButton_background_color, 0);
		backgroundBrush.setStyle(Paint.Style.FILL);
		backgroundBrush.setAlpha(ALPHA);
		backgroundBrush.setColor(backgroundColor);
		
		if (unselected != null) {
			bmpUnselected = ((BitmapDrawable) unselected).getBitmap();
		}
		if (bmpSelected != null) {
			bmpSelected = ((BitmapDrawable) selected).getBitmap();
		} else {
			bmpSelected = bmpUnselected;
		}
		setWillNotDraw(false);
		
		int rippleColor = a.getColor(R.styleable.ImageToggleButton_ripple_color, 0);
		ripple = (RippleDrawable)this.getBackground();
		((ColorDrawable) ripple.getDrawable(0)).setColor(backgroundColor);
		ripple.setColor(ColorStateList.valueOf(rippleColor));
		
		this.setClickable(true);
		
		a.recycle();
	}

	@Override 
	public void onDraw(Canvas canvas) {
		if (bmpUnselected != null) {
			canvas.drawColor(backgroundBrush.getColor());
			canvas.drawBitmap(
					bmpUnselected, 
					(getWidth() / 2) - (bmpUnselected.getWidth() / 2), 
					(getHeight() / 2) - (bmpUnselected.getHeight() / 2), 
					backgroundBrush);
		}
	}
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN) {
			if (isChecked()) {
				//backgroundBrush.setColor(context.getResources().getColor(R.color.theme_primary));
			} else {
				//backgroundBrush.setColor(context.getResources().getColor(R.color.theme_primary_light));
			}
		}
		this.performClick();
		return super.onTouchEvent(event);
	}
	@Override
	public boolean performClick() { return super.performClick(); }
}
