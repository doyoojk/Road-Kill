package com.example.crossycar;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;

import java.util.Random;

public class Cow {
    private int x, y;
    //private int width, height;
    private int velocity;


    //private String name;

    public Cow(int x, int y, int velocity) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
    }
    public void moveObject(ImageView view) {
        Animation animation = new TranslateAnimation(x, -2200, 0, 0);
        int duration = 11000 - (velocity * 10);
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
        x = x;
    }
    public void setY(int y) {
        y = x;
    }
    public int getVelocity() {
        return velocity;
    }


}
