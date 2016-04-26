package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/26/2016.
 */
public class Enemy implements IWorldObject {

    private int health;
    private SpriteRenderer renderer;
    private float speed;

    public Enemy(int health, Vector2 position) {
        this.health = health;
        renderer = new SpriteRenderer("badlogic.jpg");
        renderer.setPosition(position);
        renderer.setSize(new Vector2(40, 40));
        speed = 80;
        World.AddWorldObject(this);
    }

    @Override
    public void render() {
        renderer.render();
    }

    @Override
    public void update() {
        renderer.setY(renderer.getY() - speed * Gdx.graphics.getDeltaTime());
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (other instanceof PlayerBullet) {
            health--;
            if (health <= 0) {
                World.RemoveWorldObject(this);
            }
        }
    }

    @Override
    public float getHitRadius() {
        return renderer.getSize().x/2;
    }

    @Override
    public Vector2 getPosition() {
        return renderer.getPosition();
    }

    public Vector2 getSize() {
        return renderer.getSize();
    }

    public int getHealth() {
        return health;
    }
}
