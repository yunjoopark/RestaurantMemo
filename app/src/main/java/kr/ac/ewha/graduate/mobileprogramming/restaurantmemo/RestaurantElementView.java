package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;

public class RestaurantElementView extends View {

    public RestaurantElementView(Context context) {
        super(context);
        //init(null, 0);
    }

    public RestaurantElementView(Context context, AttributeSet attrs) {
        super(context, attrs);
        //init(attrs, 0);
    }

    public RestaurantElementView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        //init(attrs, defStyle);
    }

    private void init(AttributeSet attrs, int defStyle) {
        // Load attributes
    }
}