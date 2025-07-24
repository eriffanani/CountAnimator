package com.erif.countanimator;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.util.Log;
import android.view.animation.Interpolator;
import android.view.animation.PathInterpolator;

import androidx.annotation.NonNull;

import com.erif.countanimator.format.CountFormat;
import com.erif.countanimator.interfaces.CountEndListener;
import com.erif.countanimator.interfaces.CountUpdateListener;

import java.util.regex.Pattern;

public class CountAnimator {

    private ValueAnimator anim = null;
    private CountUpdateListener updateListener;
    private CountEndListener endListener;
    private CountFormat format = null;
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
        String mStart = strToNumber(start);
        String mEnd = strToNumber(end);
        if (mStart != null && mEnd != null) {
            try {
                int newStart = Integer.parseInt(mStart);
                int newEnd = Integer.parseInt(mEnd);
                init(newStart, newEnd);
            } catch (NumberFormatException e) {
                Log.e("CountAnim", e.getMessage());
            }
        }
    }

    private String strToNumber(String value) {
        String allow = "[^0-9]";
        if (value == null)
            return null;
        return value.replaceAll(allow, "");
    }

    private void init(int start, int end) {
        anim = ValueAnimator.ofInt(start, end);
        anim.setDuration(duration);
        anim.addUpdateListener(updateListener());
        anim.addListener(listener());
        anim.setInterpolator(mInterpolator());
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

    public void format(CountFormat format) {
        if (format != null)
            this.format = format;
    }

    private ValueAnimator.AnimatorUpdateListener updateListener() {
        return valueAnimator -> {
            if (updateListener != null) {
                int value = (int) valueAnimator.getAnimatedValue();
                if (format != null && format.getFormat() != null) {
                    String result = format.getFormat().format(value);
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
        if (anim != null && !anim.isRunning())
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

    private Interpolator mInterpolator() {
        return new PathInterpolator(
                1f, 0.075f, 0f, 0.975f
        );
    }

}
