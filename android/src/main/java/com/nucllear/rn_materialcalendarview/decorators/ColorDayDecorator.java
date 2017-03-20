package com.nucllear.rn_materialcalendarview.decorators;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.view.RoundSquareShape;
import android.text.style.BackgroundColorSpan;
import android.view.View;

import com.facebook.react.bridge.ReadableMap;
import com.nucllear.rn_materialcalendarview.R;
import com.nucllear.rn_materialcalendarview.spans.TextSpan;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;
import com.prolificinteractive.materialcalendarview.spans.DotSpan;

/**
 * Created by wwm on 2017-03-17.
 */

public class ColorDayDecorator implements DayViewDecorator {

    Drawable drawable;
    Context context;
    CalendarDay day;
    private int color;
    private int textColor = Color.DKGRAY;
    String text;

    static float DEFAULT_TEXT_SIZE = 20;


    float textSize = 20;


    public ColorDayDecorator(Context context, CalendarDay day, ReadableMap arguments) {
        this.day = day;
        this.context = context;
        this.reset(arguments);
    }

    public ColorDayDecorator(Context context, CalendarDay day, String text, int textColor, int color) {
        this(context, day, text, textColor, DEFAULT_TEXT_SIZE, color);

    }

    public ColorDayDecorator(Context context, CalendarDay day, String text, int textColor, float textSize, int color) {
        this.context = context;
        this.day = day;
        this.text = text;
        this.textColor = textColor;
        this.textSize = textSize;
        this.setColor(color);


    }

    public void reset(ReadableMap arguments) {
        this.setColor(Color.parseColor(arguments.getString("color")));
        if (arguments.hasKey("text"))
            this.text = arguments.getString("text");
        else
            this.text = null;

        if (arguments.hasKey("textColor"))
            this.textColor = Color.parseColor(arguments.getString("textColor"));

        if (arguments.hasKey("textSize"))
            this.textSize = arguments.getInt("textSize");

    }


    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (this.color == color) return;
        this.color = color;
        resetBackground(color);
    }

    private void resetBackground(int color) {
        float[] outerRadii = {20, 20, 20, 20, 20, 20, 20, 20};//外矩形 左上、右上、右下、左下 圆角半径
        RoundSquareShape roundRectShape = new RoundSquareShape(outerRadii, null, null);
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(color);
        drawable = shapeDrawable;

    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(this.day);
    }


    @Override
    public void decorate(DayViewFacade view) {

        if (this.text != null && this.text.length() > 0)
            view.addSpan(new TextSpan(text, textSize, textColor));
        view.setBackgroundDrawable(drawable);


    }


    @Override
    public boolean equals(Object o) {
        if (o instanceof Integer) {
            return ((Integer) o) == this.color;
        }
        return super.equals(o);
    }


}
