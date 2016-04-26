package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/25/2016.
 */
public class Bullet implements IWorldObject {
    private float speed;
    private Vector2 direction;
    private SpriteRenderer renderer;

    public Bullet(float x, float y, float speed, Vector2 direction) {
        direction = direction.nor();
        renderer = new SpriteRenderer("badlogic.jpg");
        renderer.setPosition(new Vector2(x, y));
        renderer.setSize(new Vector2(10, 10));
        this.speed = speed;
        this.direction = direction;
        World.AddWorldObject(this);
    }

    @Override
    public void update() {
        renderer.setX(renderer.getX() + direction.x * speed * Gdx.graphics.getDeltaTime());
        renderer.setY(renderer.getY() + direction.y * speed * Gdx.graphics.getDeltaTime());

        if (renderer.getX() < -100 || renderer.getX() > EndlessShooter.ScreenWidth + 100 ||
                renderer.getY() < -100 || renderer.getY() > EndlessShooter.ScreenHeight + 100) {
            World.RemoveWorldObject(this);
        }
    }

    @Override
    public Vector2 getPosition()
    {
        return renderer.getPosition();
    }

    @Override
    public float getHitRadius()
    {
        return renderer.getSize().x;
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (!(other instanceof Bullet)) {
            World.RemoveWorldObject(this);
        }
    }

    @Override
    public void render() {
        renderer.render();
    }
}
