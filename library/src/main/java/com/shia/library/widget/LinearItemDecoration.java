package com.shia.library.widget;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.support.annotation.ColorInt;
import android.support.v7.widget.RecyclerView;
import android.util.TypedValue;
import android.view.View;

public class LinearItemDecoration extends RecyclerView.ItemDecoration {

    private Paint mPaint;

    public LinearItemDecoration(@ColorInt int color) {
        mPaint = new Paint();
        mPaint.setColor(color);
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
    }

    public LinearItemDecoration() {
        this(Color.parseColor("#dedede"));
    }

    @Override
    public void onDrawOver(Canvas c, RecyclerView parent, RecyclerView.State state) {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, parent.getResources()
                .getDisplayMetrics());
        int childCount = parent.getChildCount();
        // int left = parent.getPaddingLeft();
        // int right = parent.getWidth() - parent.getPaddingRight();
        int left = 0;
        int right = parent.getWidth();

        for (int i = 0; i < childCount - 1; i++) {
            View view = parent.getChildAt(i);
            float top = view.getBottom();
            float bottom = view.getBottom() + height;
            c.drawRect(left, top, right, bottom, mPaint);
        }
    }

    @Override
    public void getItemOffsets(Rect outRect, View view, RecyclerView parent, RecyclerView.State state) {
        int height = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 1, parent.getResources()
                .getDisplayMetrics());
        outRect.set(0, 0, 0, height);
    }
}
