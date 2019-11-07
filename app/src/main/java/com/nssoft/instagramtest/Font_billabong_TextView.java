package com.nssoft.instagramtest;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;

import androidx.appcompat.widget.AppCompatTextView;

public class Font_billabong_TextView extends AppCompatTextView {
    public Font_billabong_TextView(Context context) {
        super(context);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/billabong.otf");
        setTypeface(typeface);
    }

    public Font_billabong_TextView(Context context, AttributeSet attrs) {
        super(context, attrs);
        Typeface typeface=Typeface.createFromAsset(context.getAssets(), "fonts/billabong.otf");
        setTypeface(typeface);
    }

}
