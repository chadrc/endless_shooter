package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Player {
    private Texture img;
    private Rectangle rectangle;
    private float speed;
    private float rotation;

    public Player() {
        img = new Texture("badlogic.jpg");
        rectangle = new Rectangle();
        rectangle.width = 100;
        rectangle.height = 100;
    }

    public void update() {

    }

    public void render(SpriteBatch batch) {
//        batch.draw(img, rectangle.x - rectangle.width/2, rectangle.y - rectangle.height/2);
        float cx = rectangle.x - rectangle.width/2;
        float cy = rectangle.y - rectangle.height/2;
        batch.draw(img, cx, cy,
                rectangle.width/2, rectangle.height/2,
                rectangle.width, rectangle.height,
                1, 1,
                rotation,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
    }

    public void setX(float x) {
        rectangle.x = x;
    }

    public void setY(float y) {
        rectangle.y = y;
    }

    public float getX() {
        return rectangle.x;
    }

    public float getY() {
        return rectangle.y;
    }

    public float getSpeed() {
        return speed;
    }

    public void setSpeed(float speed) {
        this.speed = speed;
    }

    public float getTopBound() {
        return rectangle.y + rectangle.height/2;
    }

    public float getRightBound() {
        return rectangle.x + rectangle.width/2;
    }

    public float getBottomBound() {
        return rectangle.y - rectangle.height/2;
    }

    public float getLeftBound() {
        return rectangle.x - rectangle.width/2;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }
}
