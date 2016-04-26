package com.clockwork.endlessshooter;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Matrix4;
import com.badlogic.gdx.math.Vector2;
import sun.plugin.dom.exception.InvalidStateException;

/**
 * Created by Chad Collins on 4/26/2016.
 */
public class SpriteRenderer {
    private static SpriteBatch batch = new SpriteBatch();
    private static boolean batchStarted = false;

    private Texture img;
    private Vector2 position;
    private Vector2 size;
    private float rotation;

    public SpriteRenderer(String path) {
        img = new Texture(path);
        position = new Vector2();
        size = new Vector2();
        rotation = 0;
    }

    public void render() {
        batch.draw(
                img,
                position.x - size.x/2, position.y - size.y/2,
                size.x/2, size.y/2,
                size.x, size.y,
                1, 1,
                rotation,
                0, 0,
                img.getWidth(), img.getHeight(),
                false, false);
    }

    // Access
    public float getX() {
        return position.x;
    }

    public void setX(float x) {
        position.x = x;
    }

    public float getY() {
        return position.y;
    }

    public void setY(float y) {
        position.y = y;
    }

    public Vector2 getPosition() {
        return position;
    }

    public void setPosition(Vector2 position) {
        this.position = position;
    }

    public Vector2 getSize() {
        return size;
    }

    public void setSize(Vector2 size) {
        this.size = size;
    }

    public float getRotation() {
        return rotation;
    }

    public void setRotation(float rotation) {
        this.rotation = rotation;
    }

    // Static
    public static void SetProjection(Matrix4 mat) {
        batch.setProjectionMatrix(mat);
    }

    public static void BeginBatch() {
        batch.begin();
    }

    public static void EndBatch() {
        batch.end();
    }
}
