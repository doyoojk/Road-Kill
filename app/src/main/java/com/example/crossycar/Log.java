package com.example.crossycar;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.FrameLayout;
import android.widget.GridView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;


public class Log {
    private int x;
    private int y;
    //private int width, height;
    private int velocity;


    //private String name;

    public Log(int x, int y, int velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }
    public void moveObject(FrameLayout view) {
        Animation animation = new TranslateAnimation(x, -2200, 0, 0);
        int duration = 20000 - (velocity * 10);
        animation.setDuration(duration); // set the duration of the animation (in milliseconds)
        animation.setRepeatCount(Animation.INFINITE); // set the animation to repeat indefinitely
        view.startAnimation(animation); // apply the animation to the view
    }

    public int getX() {
        return x;
    }
    public int getY() {
        return y;
    }
    public void setX(int x) {
        this.x = x;
    }
    public void setY(int y) {
        this.y = y;
    }
    public int getVelocity() {
        return velocity;
    }


}
