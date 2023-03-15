package com.example.crossycar;

import android.view.animation.Animation;
import android.view.animation.TranslateAnimation;
import android.widget.ImageView;



public class Deer {
    private int x;
    private int y;
    //private int width, height;
    private int velocity;
    private int direction;
    private int toX;


    //private String name;

    public Deer(int x, int y, int velocity, int direction) {
        this.x = x;
        this.y = y;
        this.velocity = velocity;
        this.direction = direction;
        if (direction == 0) {
            this.toX = -2200;
        }
        if (direction == 1) {
            this.toX = 2200;
        }
    }
    public void moveObject(ImageView view) {
        Animation animation = new TranslateAnimation(x, toX, 0, 0);
        int duration = 8000 - (velocity * 10);

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
