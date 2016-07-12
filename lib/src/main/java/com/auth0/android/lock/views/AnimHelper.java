package com.auth0.android.lock.views;

import android.animation.Animator;
import android.support.annotation.Nullable;
import android.util.Log;
import android.view.View;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;

import com.auth0.android.lock.R;

public class AnimHelper {

    private static final long DURATION_ALPHA_SHORT = 300;
    private static final long DURATION_TRANSLATE_SHORT = 300;
    private static final String TAG = AnimHelper.class.getSimpleName();


    public static void slideAnimate(final View view, final boolean show) {
        final Animation animation = AnimationUtils.loadAnimation(view.getContext(), show ? R.anim.com_auth0_lock_slide_down : R.anim.com_auth0_lock_slide_up);
        animation.setAnimationListener(new Animation.AnimationListener() {
            @Override
            public void onAnimationStart(Animation animation) {
//                view.setVisibility(View.VISIBLE);
                view.setAlpha(show ? 0f : 1f);
            }

            @Override
            public void onAnimationEnd(Animation animation) {
//                if (!show) {
//                    view.setVisibility(View.GONE);
//                }
            }

            @Override
            public void onAnimationRepeat(Animation animation) {

            }
        });
        view.startAnimation(animation);
    }

    public static void changeVisibilityWithTranslation(@Nullable final View view, final boolean show) {
        if (view == null) {
            return;
        }
        final float fromAlpha = show ? 0f : 1f;
        final float toAlpha = show ? 1f : 0f;
        final float toTranslation = show ? 100f : -100f;
        final float originalAlpha = view.getAlpha();
        final float originalTranslation = view.getTranslationY();
        view.setAlpha(fromAlpha);

        view.animate()
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if (show) {
                            view.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if (!show) {
                            view.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {
                        view.setAlpha(originalAlpha);
                        view.setTranslationY(originalTranslation);
                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {
                    }
                })
                .alpha(toAlpha)
                .translationY(toTranslation)
                .setDuration(DURATION_ALPHA_SHORT)
                .start();
    }

    public static void translate(@Nullable final View view, final int deltaY) {
        if (view == null) {
            return;
        }
        Log.e(TAG, "Translate by " + deltaY);
        view.animate()
                .translationYBy(deltaY)
                .setDuration(DURATION_TRANSLATE_SHORT)
                .start();
    }

    public static void changeVisibilityWithAlpha(@Nullable final View view, final boolean show) {
        if (view == null) {
            return;
        }
        final float fromAlpha = show ? 0f : 1f;
        final float toAlpha = show ? 1f : 0f;
        view.setAlpha(fromAlpha);

        view.animate()
                .alpha(toAlpha)
                .setListener(new Animator.AnimatorListener() {
                    @Override
                    public void onAnimationStart(Animator animator) {
                        if (show){
                            view.setVisibility(View.VISIBLE);
                        }
                    }

                    @Override
                    public void onAnimationEnd(Animator animator) {
                        if (!show){
                            view.setVisibility(View.GONE);
                        }
                    }

                    @Override
                    public void onAnimationCancel(Animator animator) {

                    }

                    @Override
                    public void onAnimationRepeat(Animator animator) {

                    }
                })
                .setDuration(DURATION_ALPHA_SHORT)
                .start();
    }

}
