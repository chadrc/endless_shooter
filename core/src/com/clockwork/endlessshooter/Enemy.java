package com.clockwork.endlessshooter;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

/**
 * Created by Chad Collins on 4/26/2016.
 */
public class Enemy implements IWorldObject {
    private int health;
    private Texture img;
    private float size;
    private Vector2 position;
    private float speed;

    public Enemy(int health, Vector2 position) {
        this.health = health;
        this.position = position;
        img = new Texture("badlogic.jpg");
        size = 40;
        speed = 80;
        World.AddWorldObject(this);
    }

    @Override
    public void render() {
        float cx = position.x - size/2;
        float cy = position.y - size/2;
        EndlessShooter.Batch.begin();
        EndlessShooter.Batch.draw(img, cx, cy,
                size/2, size/2,
                size, size,
                1, 1,
                0,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
        EndlessShooter.Batch.end();
    }

    @Override
    public void update() {
        position.y -= speed * Gdx.graphics.getDeltaTime();
    }

    @Override
    public void hitOccurred(IWorldObject other) {
        if (other instanceof Bullet) {
            health--;
            if (health <= 0) {
                World.RemoveWorldObject(this);
            }
        }
    }

    @Override
    public float getHitRadius() {
        return size/2;
    }

    @Override
    public Vector2 getPosition() {
        return position;
    }
}
