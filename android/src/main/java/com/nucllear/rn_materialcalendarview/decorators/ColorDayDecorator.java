package com.nucllear.rn_materialcalendarview.decorators;

import android.content.Context;
import android.graphics.Color;
import android.graphics.drawable.Drawable;
import android.os.Build;

import com.nucllear.rn_materialcalendarview.R;
import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

import java.util.Collection;
import java.util.HashSet;
import java.util.Map;

/**
 * Created by wwm on 2017-03-17.
 */

public class ColorDayDecorator implements DayViewDecorator {

    Drawable drawable;
    Context context;
    CalendarDay day;
    int color;

    public ColorDayDecorator(Context context, CalendarDay day, int color) {
        this.context = context;
        this.day = day;
        this.color = color;
        drawable =  context.getResources().getDrawable(R.drawable.shape).mutate();
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.setTint(color);
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


}
