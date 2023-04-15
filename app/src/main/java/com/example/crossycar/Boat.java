package com.example.crossycar;

import android.animation.Animator;
import android.animation.AnimatorListenerAdapter;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
//import android.view.animation.Animation;
//import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
//import android.widget.ImageView;



public class Boat implements RiverObject {
    private float x;
    private float y;
    //private int width, height;
    private int velocity;
    private int length;
    private FrameLayout view;


    //private String name;

    public Boat(int x, int y, int velocity, int length) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.length = length;
    }
    public void addView(FrameLayout view) {
        this.view = view;
    }
    @Override
    public void moveObject(int screenW, int delayDist) {
        ObjectAnimator animator = ObjectAnimator.ofFloat(this, "x", screenW + delayDist, -2200);
        animator.setDuration(15000 - (velocity * 10));
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
                // Update the x-position of the view to match the object
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
        return length;
    }
}
