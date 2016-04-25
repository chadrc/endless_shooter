package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.scenes.scene2d.ui.List;
import com.badlogic.gdx.utils.Array;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Player {
    private Texture img;
    private Rectangle rectangle;
    private float speed;
    private float rotation;

    private Array<Bullet> bullets;

    public Player() {
        img = new Texture("badlogic.jpg");
        bullets = new Array<Bullet>();
        rectangle = new Rectangle();
        rectangle.width = 100;
        rectangle.height = 100;
    }

    public void shoot() {
        Vector2 dir = new Vector2(MathUtils.cosDeg(rotation), MathUtils.sinDeg(rotation));
        Bullet b = new Bullet(rectangle.x, rectangle.y, 10, dir);
        bullets.add(b);
    }

    public void update() {
        for (Bullet b : bullets) {
            b.update();
        }
    }

    public void render() {
        float cx = rectangle.x - rectangle.width/2;
        float cy = rectangle.y - rectangle.height/2;
        EndlessShooter.Batch.begin();
        EndlessShooter.Batch.draw(img, cx, cy,
                rectangle.width/2, rectangle.height/2,
                rectangle.width, rectangle.height,
                1, 1,
                rotation,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
        EndlessShooter.Batch.end();

        for (Bullet b : bullets) {
            b.render();
        }
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
