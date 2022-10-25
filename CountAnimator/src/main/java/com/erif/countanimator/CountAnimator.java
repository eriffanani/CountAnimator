package com.erif.countanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.view.animation.AccelerateDecelerateInterpolator;

import androidx.annotation.NonNull;

import java.text.DecimalFormat;
import java.text.NumberFormat;
import java.util.Currency;
import java.util.regex.Pattern;

public class CountAnimator {

    private ValueAnimator anim = null;
    private CountUpdateListener updateListener;
    private CountEndListener endListener;
    private NumberFormat format = null;
    private int duration = 2000;

    public CountAnimator(int start, int end) {
        init(start, end);
    }

    public CountAnimator(int start, int end, int seconds) {
        this.duration = seconds * 1000;
        init(start, end);
    }

    public CountAnimator(String start, String end) {
        parseString(start, end);
    }

    public CountAnimator(String start, String end, int seconds) {
        this.duration = seconds * 1000;
        parseString(start, end);
    }

    private void parseString(String start, String end) {
        String mStart = start;
        String mEnd = end;
        if (start != null) {
            mStart = start.replaceAll("\\.", "");
            mStart = mStart.replaceAll(",", "");
        }
        if (end != null) {
            mEnd = end.replaceAll("\\.", "");
            mEnd = mEnd.replaceAll(",", "");
        }
        if (isNumeric(mStart)) {
            if (isNumeric(mEnd)) {
                int newStart = Integer.parseInt(mStart);
                int newEnd = Integer.parseInt(mEnd);
                init(newStart, newEnd);
            }
        }
    }

    private void init(int start, int end) {
        anim = ValueAnimator.ofInt(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(updateListener());
        anim.addListener(listener());
        anim.setInterpolator(new AccelerateDecelerateInterpolator());
    }

    public void duration(int seconds) {
        int duration = seconds * 1000;
        if (anim != null)
            anim.setDuration(duration);
    }

    public void duration(double seconds) {
        int duration = (int) seconds * 1000;
        anim.setDuration(duration);
    }

    public void duration(long millis) {
        anim.setDuration(millis);
    }

    public void format(NumberFormat format) {
        if (format != null)
            this.format = format;
    }

    private ValueAnimator.AnimatorUpdateListener updateListener() {
        return valueAnimator -> {
            if (updateListener != null) {
                int value = (int) valueAnimator.getAnimatedValue();
                if (format != null) {
                    String result = format.format(value);
                    updateListener.doOnUpdate(value, result);
                } else {
                    updateListener.doOnUpdate(value, ""+value);
                }
            }
        };
    }

    public void doOnUpdate(@NonNull CountUpdateListener listener) {
        this.updateListener = listener;
    }

    public void doOnEnd(@NonNull CountEndListener listener) {
        this.endListener = listener;
    }

    private Animator.AnimatorListener listener() {
        return new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationEnd(@NonNull Animator animator) {
                if (endListener != null)
                    endListener.doOnEnd();
            }

            @Override
            public void onAnimationCancel(@NonNull Animator animator) {

            }

            @Override
            public void onAnimationRepeat(@NonNull Animator animator) {

            }
        };
    }

    public void start() {
        if (anim != null)
            anim.start();
    }

    public void pause() {
        if (anim != null && anim.isRunning())
            anim.pause();
    }

    public void resume() {
        if (anim != null && anim.isPaused())
            anim.resume();
    }

    public void stop() {
        if (anim != null && anim.isRunning())
            anim.cancel();
    }

    private final Pattern pattern = Pattern.compile("-?\\d+(\\.\\d+)?");

    private boolean isNumeric(String strNum) {
        if (strNum == null) {
            return false;
        }
        return pattern.matcher(strNum).matches();
    }

}
