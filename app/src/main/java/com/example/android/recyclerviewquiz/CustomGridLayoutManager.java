package com.example.android.recyclerviewquiz;

import android.content.Context;
import android.graphics.PointF;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.LinearSmoothScroller;
import android.support.v7.widget.RecyclerView;
import android.util.DisplayMetrics;
import android.widget.HorizontalScrollView;

/**
 * Created by ElGreetly on 2/10/18.
 */

public class CustomGridLayoutManager extends LinearLayoutManager {
    private static final float MILLISECONDS_PER_INCH = 7f;
    static boolean isScrollEnabled = false;
    CustomGridLayoutManager(Context context, int orientation, boolean b) {
        super(context, orientation, b);
    }



    @Override
    public boolean canScrollHorizontally() {
        //Similarly you can customize "canScrollHorizontally()" for managing horizontal scroll
        return isScrollEnabled && super.canScrollHorizontally();
    }

    @Override
    public void smoothScrollToPosition(RecyclerView recyclerView, RecyclerView.State state, int position) {

        final LinearSmoothScroller linearSmoothScroller = new LinearSmoothScroller(recyclerView.getContext()) {

            @Override
            public PointF computeScrollVectorForPosition(int targetPosition) {
                return CustomGridLayoutManager.this.computeScrollVectorForPosition(targetPosition);
            }

            @Override
            protected float calculateSpeedPerPixel(DisplayMetrics displayMetrics) {
                return MILLISECONDS_PER_INCH / displayMetrics.densityDpi;
            }
        };

        linearSmoothScroller.setTargetPosition(position);
        startSmoothScroll(linearSmoothScroller);
    }


}