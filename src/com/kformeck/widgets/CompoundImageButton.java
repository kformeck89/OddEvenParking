package com.kformeck.widgets;

import com.kformeck.oddevenparking.R;

import android.content.Context;
import android.content.res.ColorStateList;
import android.content.res.TypedArray;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.ColorDrawable;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.util.AttributeSet;
import android.widget.CompoundButton;

public class CompoundImageButton extends CompoundButton implements CompoundButton.OnCheckedChangeListener {
	private Paint backgroundBrush;
	private Bitmap bmpSelected;
	private Bitmap bmpUnselected;
	
	public CompoundImageButton(Context context) { this(context, null); }
	public CompoundImageButton(Context context, AttributeSet attrs) { this(context, attrs, 0); }
	public CompoundImageButton(Context context, AttributeSet attrs, int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		
		backgroundBrush = new Paint();
		
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CompoundImageButton);
		
		Drawable selected = a.getDrawable(R.styleable.CompoundImageButton_drawable_selected);
		Drawable unselected = a.getDrawable(R.styleable.CompoundImageButton_drawable_unselected);

		int backgroundColor = a.getColor(R.styleable.CompoundImageButton_background_color, 0);
		backgroundBrush.setStyle(Paint.Style.FILL);
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
		
		int rippleColor = a.getColor(R.styleable.CompoundImageButton_ripple_color, 0);
		RippleDrawable ripple = (RippleDrawable)this.getBackground();
		ColorDrawable rippleBackground = (ColorDrawable) ripple.getDrawable(0);
		rippleBackground.setColor(backgroundColor);
		ripple.setColor(ColorStateList.valueOf(rippleColor));
		
		this.setChecked(false);
		this.setClickable(true);
		
		a.recycle();
	}

	@Override 
	public void onDraw(Canvas canvas) {
		if (bmpUnselected != null) {
			canvas.drawBitmap(
					bmpUnselected, 
					(getWidth() / 2) - (bmpUnselected.getWidth() / 2), 
					(getHeight() / 2) - (bmpUnselected.getHeight() / 2), 
					backgroundBrush);
		}
	}
	@Override
	public boolean performClick() { 
		return super.performClick(); 
	}
	@Override
	public void setOnCheckedChangeListener(OnCheckedChangeListener listener) {
		super.setOnCheckedChangeListener(listener);
	}
	@Override
	public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
		buttonView.setChecked(isChecked);
	}
	@Override
	protected void drawableStateChanged() {
		super.drawableStateChanged();
		invalidate();
	}
}