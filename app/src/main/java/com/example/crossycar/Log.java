package com.example.crossycar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
//import android.widget.GridView;
//import android.widget.ImageView;
//import android.widget.LinearLayout;
//import android.widget.RelativeLayout;


public class Log implements RiverObject {
    private float x;
    private float y;
    //private int width, height;
    private int velocity;
    private int length;
    private int delayDist;
    private FrameLayout view;


    //private String name;

    public Log(float x, float y, int velocity, int length, int delayDist) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.length = length;
        this.delayDist = delayDist;
    }
    public void addView(FrameLayout view) {
        this.view = view;
    }

    @Override
    public void moveObject(int screenW, int delayDist) {
        x += -1 * velocity;
        view.setX(x);
        view.setY(y);
        if (x < 0 || x > screenW) {
            x = screenW + delayDist;
            view.setX(x);
            view.setY(y);
        }
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", screenW + delayDist, -2200);
        animator.setDuration(20000 - (velocity * 10));
        // set the duration of the animation (in milliseconds)
        animator.setRepeatCount(ValueAnimator.INFINITE);
        // set the animation to repeat indefinitely
        animator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                // Update the x-position of the view to match the object
                view.setX(getX());
                view.setY(getY());
            }
        });
        animator.addListener(new AnimatorListenerAdapter() {
            @Override
            public void onAnimationEnd(Animator animation) {
                // When the animation ends, set the x-position
                // of the object to the original position
                setX(screenW + delayDist);
                // Update the x-position of the
                // view to match the object
                view.setX(getX());
                view.setY(getY());
            }
        });
        animator.start(); // start the animation
        setX(screenW + delayDist); // set the initial x-position of the object
        setY(getY());
        view.setX(getX()); // set the x-position of the view to match the object
        view.setY(getY()); // set the y-position of the view to match the object
    }
    public void setViewX(float x) {
        this.view.setX(x);
    }
    public void setViewY(float y) {
        this.view.setY(y);
    }
    public int getDelayDist() {
        return delayDist;
    }
    public float getX() {
        return x;
    }
    public float getY() {
        return y;
    }
    public void setX(float x) {
        this.x = x;
    }
    public void setY(float y) {
        this.y = y;
    }
    public int getVelocity() {
        return velocity;
    }
    public int getLength() {
        return length; }

}
