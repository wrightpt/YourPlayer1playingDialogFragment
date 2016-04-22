package com.yourplayer.c.yourplayer1;

import android.app.*;
import android.graphics.drawable.*;
import android.os.*;
import android.view.*;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.AttributeSet;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by c on 4/19/16.
 */
class TextImageButton extends FrameLayout {
    private ImageView imageView;
    private TextView textView;

    public TextImageButton(Context context) {
        this(context, null);
    }

    public TextImageButton(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public TextImageButton(Context context, AttributeSet attrs, int defaultStyle) {
        super(context, attrs, defaultStyle);
        imageView = new ImageView(context, attrs, defaultStyle);
        textView = new TextView(context, attrs, defaultStyle);
        FrameLayout.LayoutParams params = new FrameLayout.LayoutParams(
                LayoutParams.FILL_PARENT, LayoutParams.FILL_PARENT);
        this.addView(imageView, params);
        this.addView(textView, params);
        setClickable(true);
        setFocusable(true);
        setBackgroundResource(android.R.drawable.btn_default);
        if (imageView.getDrawable() != null) {
            textView.setVisibility(View.GONE);
            imageView.setVisibility(View.VISIBLE);
        } else {
            textView.setVisibility(View.VISIBLE);
            imageView.setVisibility(View.GONE);
        }
    }

    public void setText(CharSequence text) {
        textView.setVisibility(View.VISIBLE);
        imageView.setVisibility(View.GONE);
        textView.setText(text);
    }

    public void setImageResource(int resId) {
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageResource(resId);
    }

    public void setImageDrawable(Drawable drawable) {
        textView.setVisibility(View.GONE);
        imageView.setVisibility(View.VISIBLE);
        imageView.setImageDrawable(drawable);
    }
}

