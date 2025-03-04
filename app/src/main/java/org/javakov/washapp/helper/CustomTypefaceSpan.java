package org.javakov.washapp.helper;

import android.graphics.Typeface;
import android.text.TextPaint;
import android.text.style.TypefaceSpan;

public class CustomTypefaceSpan extends TypefaceSpan {
    private final Typeface typeface;

    public CustomTypefaceSpan(Typeface typeface) {
        super("");
        this.typeface = typeface;
    }

    @Override
    public void updateDrawState(TextPaint ds) {
        ds.setTypeface(typeface);
    }

    @Override
    public void updateMeasureState(TextPaint paint) {
        paint.setTypeface(typeface);
    }
}
