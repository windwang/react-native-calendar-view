package com.prolificinteractive.materialcalendarview;

import com.prolificinteractive.materialcalendarview.MaterialCalendarView;
import com.prolificinteractive.materialcalendarview.MonthPagerAdapter;
import com.prolificinteractive.materialcalendarview.MonthView;

/**
 * Created by wwm on 2017-03-18.
 */
public class CustomMonthPagerAdapter extends MonthPagerAdapter {
    CustomMonthPagerAdapter(MaterialCalendarView mcv) {
        super(mcv);
    }

    @Override
    protected MonthView createView(int position) {
        return new CustomMonthView(mcv, getItem(position), mcv.getFirstDayOfWeek());
    }
}
