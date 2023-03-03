package com.example.crossycar;

import static org.junit.Assert.assertEquals;

import android.view.MotionEvent;

import org.junit.Test;

public class GameScreenTest {

    @Test
    public void testCarMovement() {
        GameScreen gameScreen = new GameScreen();

        // Simulate touch event to move car up
        MotionEvent touchUp = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 500, 1000, 0);
        gameScreen.onTouchEvent(touchUp);
        assertEquals(gameScreen.carY, 892);

        // Simulate touch event to move car right
        MotionEvent touchRight = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 800, 1000, 0);
        gameScreen.onTouchEvent(touchRight);
        assertEquals(gameScreen.carX, 1225);

        // Simulate touch event to move car down
        MotionEvent touchDown = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 500, 1400, 0);
        gameScreen.onTouchEvent(touchDown);
        assertEquals(gameScreen.carY, 1000);

        // Simulate touch event to move car left
        MotionEvent touchLeft = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 200, 1000, 0);
        gameScreen.onTouchEvent(touchLeft);
        assertEquals(gameScreen.carX, 925);
    }

    @Test
    public void testCarDoesNotMoveOffScreen() {
        GameScreen gameScreen = new GameScreen();
        gameScreen.carX = 0;
        gameScreen.carY = 0;

        MotionEvent event = MotionEvent.obtain(0, 0, MotionEvent.ACTION_DOWN, 0, 0, 0);
        // Move car off the left edge of the screen
        event.setLocation(-100, 0);
        gameScreen.onTouchEvent(event);
        assertEquals(gameScreen.carX, 0);

        // Move car off the right edge of the screen
        event.setLocation(1125, 0);
        gameScreen.onTouchEvent(event);
        assertEquals(gameScreen.carX, 1025);

        // Move car off the top edge of the screen
        event.setLocation(0, -100);
        gameScreen.onTouchEvent(event);
        assertEquals(gameScreen.carY, 0);

        // Move car off the bottom edge of the screen
        event.setLocation(0, 2000);
        gameScreen.onTouchEvent(event);
        assertEquals(gameScreen.carY, 1900);
    }
}
