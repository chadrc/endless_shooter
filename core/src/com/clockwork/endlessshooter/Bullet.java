package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Bullet {
    private Rectangle rectangle;
    private float speed;
    private Vector2 direction;

    public Bullet(float x, float y, float speed, Vector2 direction) {
        direction = direction.nor();
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = 10;
        rectangle.height = 10;
        this.speed = speed;
        this.direction = direction;
    }

    public void update() {
        rectangle.x += direction.x * speed;
        rectangle.y += direction.y * speed;
    }

    public void render() {
        EndlessShooter.Renderer.begin(ShapeRenderer.ShapeType.Filled);
        EndlessShooter.Renderer.setColor(1, 0, 0, 1);
        EndlessShooter.Renderer.circle(rectangle.x, rectangle.y, rectangle.width);
        EndlessShooter.Renderer.end();
    }
}
