package android.support.v4.view;

import android.annotation.TargetApi;
import android.graphics.Canvas;
import android.graphics.Outline;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.drawable.shapes.RectShape;
import android.graphics.drawable.shapes.RoundRectShape;
import android.os.Build;

/**
 * Created by wwm on 2017-03-20.
 */

public class RoundSquareShape extends RectShape {

    private float[] mOuterRadii;
    private RectF mInset;
    private float[] mInnerRadii;

    private RectF mInnerRect;
    private Path mPath;    // this is what we actually draw
    float padding;

    /**
     * RoundRectShape constructor.
     * Specifies an outer (round)rect and an optional inner (round)rect.
     *
     * @param outerRadii An array of 8 radius values, for the outer roundrect.
     *                   The first two floats are for the
     *                   top-left corner (remaining pairs correspond clockwise).
     *                   For no rounded corners on the outer rectangle,
     *                   pass null.
     * @param inset      A RectF that specifies the distance from the inner
     *                   rect to each side of the outer rect.
     *                   For no inner, pass null.
     * @param innerRadii An array of 8 radius values, for the inner roundrect.
     *                   The first two floats are for the
     *                   top-left corner (remaining pairs correspond clockwise).
     *                   For no rounded corners on the inner rectangle,
     *                   pass null.
     *                   If inset parameter is null, this parameter is ignored.
     */
    public RoundSquareShape(float[] outerRadii, RectF inset,
                            float[] innerRadii, float padding) {
        if (outerRadii != null && outerRadii.length < 8) {
            throw new ArrayIndexOutOfBoundsException("outer radii must have >= 8 values");
        }
        if (innerRadii != null && innerRadii.length < 8) {
            throw new ArrayIndexOutOfBoundsException("inner radii must have >= 8 values");
        }
        mOuterRadii = outerRadii;
        mInset = inset;
        mInnerRadii = innerRadii;

        if (inset != null) {
            mInnerRect = new RectF();
        }
        mPath = new Path();
        this.padding = padding;
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        canvas.drawPath(mPath, paint);
    }

    @TargetApi(Build.VERSION_CODES.LOLLIPOP)
    @Override
    public void getOutline(Outline outline) {
        if (mInnerRect != null) return; // have a hole, can't produce valid outline

        float radius = 0;
        if (mOuterRadii != null) {
            radius = mOuterRadii[0];
            for (int i = 1; i < 8; i++) {
                if (mOuterRadii[i] != radius) {
                    // can't call simple constructors, use path
                    outline.setConvexPath(mPath);
                    return;
                }
            }
        }

        final RectF rect = rect();
        outline.setRoundRect((int) Math.ceil(rect.left), (int) Math.ceil(rect.top),
                (int) Math.floor(rect.right), (int) Math.floor(rect.bottom),
                radius);
    }

    @Override
    protected void onResize(float width, float height) {
        super.onResize(width, height);

        float w = Math.min(width, height)-this.padding*2;
        float h = w;

        RectF r = rect();
        r.left = (width - w) / 2;
        r.top = (height - h) / 2;
        r.right = r.left + w;
        r.bottom = r.top + h;
        mPath.reset();

        if (mOuterRadii != null) {
            mPath.addRoundRect(r, mOuterRadii, Path.Direction.CW);
        } else {
            mPath.addRect(r, Path.Direction.CW);
        }
        if (mInnerRect != null) {
            mInnerRect.set(r.left + mInset.left, r.top + mInset.top,
                    r.right - mInset.right, r.bottom - mInset.bottom);
            if (mInnerRect.width() < w && mInnerRect.height() < h) {
                if (mInnerRadii != null) {
                    mPath.addRoundRect(mInnerRect, mInnerRadii, Path.Direction.CCW);
                } else {
                    mPath.addRect(mInnerRect, Path.Direction.CCW);
                }
            }
        }
    }

    @Override
    public RoundSquareShape clone() throws CloneNotSupportedException {
        RoundSquareShape shape = (RoundSquareShape) super.clone();
        shape.mOuterRadii = mOuterRadii != null ? mOuterRadii.clone() : null;
        shape.mInnerRadii = mInnerRadii != null ? mInnerRadii.clone() : null;
        shape.mInset = new RectF(mInset);
        shape.mInnerRect = new RectF(mInnerRect);
        shape.mPath = new Path(mPath);
        return shape;
    }
}
