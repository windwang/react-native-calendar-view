package com.prolificinteractive.materialcalendarview;

import android.support.annotation.NonNull;

import java.util.Calendar;
import java.util.Collection;

/**
 * Created by wwm on 2017-03-18.
 */

public class CustomMonthView extends  MonthView {
    public CustomMonthView(@NonNull MaterialCalendarView view, CalendarDay month, int firstDayOfWeek) {
        super(view, month, firstDayOfWeek);
    }

    @Override
    protected void addDayView(Collection<DayView> dayViews, Calendar calendar) {
        super.addDayView(dayViews, calendar);
    }
}
