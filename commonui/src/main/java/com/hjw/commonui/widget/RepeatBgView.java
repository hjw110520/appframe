package com.hjw.commonui.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.drawable.Drawable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

public class RepeatBgView extends View {

    private static final String REPEAT_X = "x";
    private static final String REPEAT_Y = "y";

    public RepeatBgView(Context context) {
        super(context);
    }

    public RepeatBgView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    public RepeatBgView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        int widthSize = MeasureSpec.getSize(widthMeasureSpec);
        int widthMode = MeasureSpec.getMode(widthMeasureSpec);
        if (widthMode == MeasureSpec.EXACTLY) {
            // do nothing
        } else if (widthMode == MeasureSpec.AT_MOST) {
            // if widthSize <=0, ignore
            if (widthSize > 0) {
                widthSize = Math.min(widthSize, getBackgroundInitWidth());
            }
        } else {
            widthSize = getBackgroundInitWidth();
        }

        int heightSize = MeasureSpec.getSize(heightMeasureSpec);
        int heightMode = MeasureSpec.getMode(heightMeasureSpec);
        if (heightMode == MeasureSpec.EXACTLY) {
            // do nothing
        } else if (heightMode == MeasureSpec.AT_MOST) {
            // if widthSize <=0, ignore
            if (heightSize > 0) {
                heightSize = Math.min(heightSize, getBackgroundInitHeight());
            }
        } else {
            heightSize = getBackgroundInitHeight();
        }

        super.onMeasure(MeasureSpec.makeMeasureSpec(widthSize, MeasureSpec.EXACTLY),
                MeasureSpec.makeMeasureSpec(heightSize, MeasureSpec.EXACTLY));
    }

    /*@Override
    public void draw(Canvas canvas) {
        onDraw(canvas);
    }
*/
    @Override
    protected void onDraw(Canvas canvas) {
        Drawable bg = getBackground();
        if (null == bg) {
            super.onDraw(canvas);
            return ;
        }

        canvas.save();

        final int paddingLeft = getPaddingLeft();
        final int paddingTop = getPaddingTop();
        final int paddingRight = getPaddingRight();
        final int paddingBottom = getPaddingBottom();

        final int width = getMeasuredWidth();
        final int height = getMeasuredHeight();

        final int drawLeft = paddingLeft;
        final int drawTop = paddingTop;
        final int drawRight = Math.max(0, width - paddingRight);
        final int drawBottom = Math.max(0, height - paddingBottom);

        boolean repeatX = isRepeatX();
        boolean repeatY = isRepeatY();
        if (!repeatX && !repeatY) {
            bg.setBounds(drawLeft, drawTop, drawRight, drawBottom);
            bg.draw(canvas);
        } else {
            final int bgWidth = getBackgroundInitWidth();
            final int bgHeight = getBackgroundInitHeight();

            int drawLeftCopy = drawLeft;
            int drawRightCopy;
            if (repeatX) {
                do {
                    drawRightCopy = drawLeftCopy + bgWidth;
                    if (repeatY) {
                        int drawTopCopy = drawTop;
                        int drawBottomCopy;
                        do {
                            drawBottomCopy = drawTopCopy + bgHeight;
                            bg.setBounds(drawLeftCopy, drawTopCopy, drawRightCopy, drawBottomCopy);
                            bg.draw(canvas);
                            drawTopCopy = drawBottomCopy;
                        } while (drawBottomCopy <= drawBottom);
                    } else {
                        // only repeatX
                        bg.setBounds(drawLeftCopy, drawTop, drawRightCopy, drawBottom);
                        bg.draw(canvas);
                    }
                    drawLeftCopy = drawRightCopy;
                } while (drawRightCopy <= drawRight);
            } else {
                // must be repeatY, only
                int drawTopCopy = drawTop;
                int drawBottomCopy;
                do {
                    drawBottomCopy = drawTopCopy + bgHeight;
                    bg.setBounds(drawLeft, drawTopCopy, drawRight, drawBottomCopy);
                    bg.draw(canvas);
                    drawTopCopy = drawBottomCopy;
                } while (drawBottomCopy <= drawBottom);
            }
        }

        canvas.restore();
    }

    private boolean isRepeatX() {
        String tag = (String) getTag();
        if (TextUtils.isEmpty(tag)) {
            return false;
        }
        return tag.indexOf(REPEAT_X) >= 0;
    }

    private boolean isRepeatY() {
        String tag = (String) getTag();
        if (TextUtils.isEmpty(tag)) {
            return false;
        }
        return tag.indexOf(REPEAT_Y) >= 0;
    }

    private int getBackgroundInitWidth() {
        Drawable bg = getBackground();
        if (null == bg) return 0;

        return bg.getIntrinsicWidth();
    }

    private int getBackgroundInitHeight() {
        Drawable bg = getBackground();
        if (null == bg) return 0;

        return bg.getIntrinsicHeight();
    }

}
