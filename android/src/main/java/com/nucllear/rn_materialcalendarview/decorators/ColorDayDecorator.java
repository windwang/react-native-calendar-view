package com.nucllear.rn_materialcalendarview.decorators;

import android.annotation.TargetApi;
import android.content.Context;
import android.content.res.ColorStateList;
import android.graphics.Color;
import android.graphics.Rect;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.RippleDrawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.OvalShape;
import android.os.Build;

import com.nucllear.rn_materialcalendarview.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by wwm on 2017-03-17.
 */

public class ColorDayDecorator implements DayViewDecorator {

    Drawable drawable;
    Context context;
    CalendarDay day;
    private int color;

    public ColorDayDecorator(Context context, CalendarDay day, int color) {
        this.context = context;
        this.day = day;
        this.setColor(color);

        resetDraw();

    }

    private void resetDraw() {
        drawable = context.getResources().getDrawable(R.drawable.shape).mutate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(getColor());
        }
    }


    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return day.equals(this.day);
    }

    @Override
    public void decorate(DayViewFacade view) {

        view.setBackgroundDrawable(drawable);
    }

    public boolean equalStyle(int color) {
        return this.getColor() == color;
    }

    public int getColor() {
        return color;
    }

    public void setColor(int color) {
        if (this.color == color) return;
        this.color = color;
        resetDraw();
    }

//    private void regenerateBackground() {
//
//            drawable = generateBackground(this.color, fadeTime, tempRect);
//
//    }
//
//    private static Drawable generateBackground(int color, int fadeTime, Rect bounds) {
//        StateListDrawable drawable = new StateListDrawable();
//        drawable.setExitFadeDuration(fadeTime);
//        drawable.addState(new int[]{android.R.attr.state_checked}, generateCircleDrawable(color));
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            drawable.addState(new int[]{android.R.attr.state_pressed}, generateRippleDrawable(color, bounds));
//        } else {
//            drawable.addState(new int[]{android.R.attr.state_pressed}, generateCircleDrawable(color));
//        }
//
//        drawable.addState(new int[]{}, generateCircleDrawable(Color.TRANSPARENT));
//
//        return drawable;
//    }
//
//    private static Drawable generateCircleDrawable(final int color) {
//        ShapeDrawable drawable = new ShapeDrawable(new OvalShape());
//        drawable.getPaint().setColor(color);
//        return drawable;
//    }
//
//    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
//    private static Drawable generateRippleDrawable(final int color, Rect bounds) {
//        ColorStateList list = ColorStateList.valueOf(color);
//        Drawable mask = generateCircleDrawable(Color.WHITE);
//        RippleDrawable rippleDrawable = new RippleDrawable(list, null, mask);
////        API 21
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP) {
//            rippleDrawable.setBounds(bounds);
//        }
//
////        API 22. Technically harmless to leave on for API 21 and 23, but not worth risking for 23+
//        if (Build.VERSION.SDK_INT == Build.VERSION_CODES.LOLLIPOP_MR1) {
//            int center = (bounds.left + bounds.right) / 2;
//            rippleDrawable.setHotspotBounds(center, bounds.top, center, bounds.bottom);
//        }
//
//        return rippleDrawable;
//    }

//    @Override
//    public boolean equals(Object o) {
//        if(o instanceof  Integer){
//            return ((Integer)o)==this.color;
//        }
//        return super.equals(o);
//    }
}
