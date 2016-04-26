package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Bullet implements IWorldObject {
    private Rectangle rectangle;
    private float speed;
    private Vector2 direction;

    public Bullet(float x, float y, float speed, Vector2 direction) {
        direction = direction.nor();
        rectangle = new Rectangle();
        rectangle.x = x;
        rectangle.y = y;
        rectangle.width = 5;
        rectangle.height = 5;
        this.speed = speed;
        this.direction = direction;
        World.AddWorldObject(this);
    }

    @Override
    public void update() {
        rectangle.x += direction.x * speed;
        rectangle.y += direction.y * speed;

        if (rectangle.x < -100 || rectangle.x > EndlessShooter.ScreenWidth + 100 ||
                rectangle.y < -100 || rectangle.y > EndlessShooter.ScreenHeight + 100) {
            World.RemoveWorldObject(this);
        }
    }

    @Override
    public Vector2 getPosition()
    {
        return new Vector2(rectangle.x, rectangle.y);
    }

    @Override
    public float getHitRadius()
    {
        return rectangle.width/2;
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (!(other instanceof Bullet) && !(other instanceof Player)) {
            World.RemoveWorldObject(this);
        }
    }

    @Override
    public void render() {
        EndlessShooter.Renderer.begin(ShapeRenderer.ShapeType.Filled);
        EndlessShooter.Renderer.setColor(1, 0, 0, 1);
        EndlessShooter.Renderer.circle(rectangle.x, rectangle.y, rectangle.width);
        EndlessShooter.Renderer.end();
    }
}
