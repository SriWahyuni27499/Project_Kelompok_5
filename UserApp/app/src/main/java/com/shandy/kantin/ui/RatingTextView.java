package com.shandy.kantin.ui;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import androidx.core.content.ContextCompat;
import androidx.appcompat.widget.AppCompatTextView;
import android.util.AttributeSet;

import com.shandy.kantin.R;

import static android.view.Gravity.CENTER;

public class RatingTextView extends AppCompatTextView {

    private Float rating = 0.0f;

    public RatingTextView(Context context) {
        super(context);
        setUpRateStar();
        drawRating();
    }

    public RatingTextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        setUpRateStar();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatingTextView);
        rating = ta.getFloat(R.styleable.RatingTextView_rating,0.0f);
        ta.recycle();
        drawRating();
    }

    public RatingTextView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        setUpRateStar();
        TypedArray ta = context.obtainStyledAttributes(attrs, R.styleable.RatingTextView);
        rating = ta.getFloat(R.styleable.RatingTextView_rating,0.0f);
        ta.recycle();
        drawRating();
    }

    private void setUpRateStar() {
        Drawable starImage = ContextCompat.getDrawable(getContext(), R.drawable.ic_star);
        setCompoundDrawablesRelativeWithIntrinsicBounds(null,null, starImage,null);
    }

    private void drawRating() {
        setTextColor(getResources().getColor(R.color.whiteMenu));
        setText(" "+ String.valueOf(rating));
        setGravity(CENTER);
        setPadding(8,8,8,8);
        if(rating>=4){
            setBackgroundColor(getResources().getColor(R.color.ratingHighMenu));
        }else{
            setBackgroundColor(rating>=3?getResources().getColor(R.color.ratingMediumMenu):getResources().getColor(R.color.ratingLowMenu));
        }
    }

    public void setRating(Float rate){
        rating = rate;
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        drawRating();
    }
}
