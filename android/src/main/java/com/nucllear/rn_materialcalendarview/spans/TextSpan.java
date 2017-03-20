package com.nucllear.rn_materialcalendarview.spans;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.os.Build;
import android.text.style.LineBackgroundSpan;

/**
 * Created by wwm on 2017-03-20.
 */

public class TextSpan implements LineBackgroundSpan {
    private final int color;
    private String text;

    /**
     * Create a span to draw a dot using a specified radius and color
     *
     * @param text  radius for the dot
     * @param color color of the dot
     */
    public TextSpan(String text, int color) {
        this.text = text;
        this.color = color;
    }

    @Override
    public void drawBackground(
            Canvas canvas, Paint paint,
            int left, int right, int top, int baseline, int bottom,
            CharSequence charSequence,
            int start, int end, int lineNum
    ) {
        int oldColor = paint.getColor();
        if (color != 0) {
            paint.setColor(color);

        }
        Rect bounds = new Rect();
        paint.getTextBounds(text, 0, text.length(), bounds);
        float width = bounds.width();
        canvas.drawText(text, (left + right - width) / 2, bottom + bounds.height(), paint);
        // canvas.drawCircle((left + right) / 2, bottom + radius, radius, paint);
        paint.setColor(oldColor);



    }
}
