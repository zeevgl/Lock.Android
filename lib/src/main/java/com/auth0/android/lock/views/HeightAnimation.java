package com.auth0.android.lock.views;

import android.view.View;
import android.view.animation.Animation;
import android.view.animation.Transformation;

public class HeightAnimation extends Animation {
    private int originalHeight;
    private View view;
    private boolean show;

    private float fromAlpha;
    private float fromHeight;
    private float fromTranslation;
    private boolean finished;

    public HeightAnimation(View view) {
        this.view = view;
        this.originalHeight = view.getHeight();
        setDuration(300);
//        setFillAfter(true);
//        setFillEnabled(true);
    }

    public void start(boolean show) {
        this.show = show;
        this.fromHeight = show ? 0 : originalHeight;
        this.fromAlpha = show ? 0f : 1f;
//        this.fromTranslation = show ? -20 : 0;
//        view.setPivotY(0);

        finished = false;
        view.clearAnimation();
        view.startAnimation(this);
    }

    @Override
    protected void applyTransformation(float interpolatedTime, Transformation t) {
        if (finished) {
            reset();
            return;
        }
        float heightStep = interpolatedTime * (show ? originalHeight : -originalHeight);
        float alphaStep = interpolatedTime * (show ? 1f : -1f);
        float translationStep = interpolatedTime * (show ? 20 : -20);
//        view.setScaleY(show ? interpolatedTime : 1 - interpolatedTime);
        view.setAlpha(fromAlpha + alphaStep);
//        view.setTranslationY(fromTranslation + translationStep);
        view.getLayoutParams().height = (int) (fromHeight + heightStep);
        view.requestLayout();
        if (interpolatedTime == 1) {
            finished = true;
        }
    }

    @Override
    public boolean willChangeBounds() {
        return true;
    }
}

