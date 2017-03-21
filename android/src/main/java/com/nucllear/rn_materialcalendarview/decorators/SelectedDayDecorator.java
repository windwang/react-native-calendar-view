package com.nucllear.rn_materialcalendarview.decorators;

import android.graphics.Color;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.graphics.drawable.ShapeDrawable;
import android.graphics.drawable.StateListDrawable;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;
import android.support.v4.view.RoundSquareShape;
import android.view.View;

import com.prolificinteractive.materialcalendarview.CalendarDay;
import com.prolificinteractive.materialcalendarview.DayViewDecorator;
import com.prolificinteractive.materialcalendarview.DayViewFacade;

/**
 * Created by wwm on 2017-03-20.
 */

public class SelectedDayDecorator implements DayViewDecorator {
    private int selectionColor = Color.GRAY;

    StateListDrawable drawable = null;

    public SelectedDayDecorator(int selectionColor) {
        this.selectionColor = selectionColor;
        resetBackground();
    }

    @Override
    public boolean shouldDecorate(CalendarDay day) {
        return true;
    }


    private void resetBackground() {
        if (drawable != null) return;

        //    float[] outerRadii = {20, 20, 20, 20, 60, 60, 80, 80};//外矩形 左上、右上、右下、左下 圆角半径
        float[] outerRadii = {20, 20, 20, 20, 20, 20, 20, 20};//外矩形 左上、右上、右下、左下 圆角半径
        RectF inset = null;       // new RectF(0, 0, 100, 100);//内矩形距外矩形，左上角x,y距离， 右下角x,y距离
        float[] innerRadii = null;// {20, 20, 20, 20, 20, 20, 20, 20};//内矩形 圆角半径
        RoundSquareShape roundRectShape = new RoundSquareShape(outerRadii, inset, innerRadii,0);

        //   RoundRectShape roundRectShape = new RoundRectShape(outerRadii, null, innerRadii); //无内矩形

        drawable = new StateListDrawable();
        ShapeDrawable shapeDrawable = new ShapeDrawable(roundRectShape);
        shapeDrawable.getPaint().setColor(selectionColor);
        drawable.addState(new int[]{android.R.attr.state_checked}, shapeDrawable);
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
            drawable.addState(new int[]{android.R.attr.state_pressed}, shapeDrawable);
        } else {
            drawable.addState(new int[]{android.R.attr.state_pressed}, shapeDrawable);
        }


    }

    @Override
    public void decorate( DayViewFacade view) {

        view.setSelectionDrawable(drawable);
    }
}
