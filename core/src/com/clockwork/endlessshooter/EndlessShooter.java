package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;
import com.badlogic.gdx.math.Vector3;

public class EndlessShooter extends ApplicationAdapter {
    public static float ScreenWidth = 800;
    public static float ScreenHeight = 800;
    public static ShapeRenderer Renderer;
    public static SpriteBatch Batch;

	OrthographicCamera camera;
    Player player;
	
	@Override
	public void create () {
		Batch = new SpriteBatch();
        Renderer = new ShapeRenderer();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, ScreenWidth, ScreenHeight);
        player = new Player();
        player.setX(ScreenWidth/2);
        player.setY(ScreenHeight/2);
        player.setSpeed(2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Batch.setProjectionMatrix(camera.combined);
        Renderer.setProjectionMatrix(camera.combined);

        player.update();

        player.render();

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        float difX = player.getX() - touchPos.x;
        float difY = player.getY() - touchPos.y;
        float tan = MathUtils.atan2(difY, difX);
        float angle = tan * MathUtils.radiansToDegrees + 180;
        player.setRotation(angle);

        if (Gdx.input.isTouched()) {
            player.shoot();
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W) && player.getTopBound() < ScreenHeight) {
            player.setY(player.getY() + player.getSpeed());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A) && player.getLeftBound() > 0) {
            player.setX(player.getX() - player.getSpeed());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S) && player.getBottomBound() > 0) {
            player.setY(player.getY() - player.getSpeed());
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D) && player.getRightBound() < ScreenWidth) {
            player.setX(player.getX() + player.getSpeed());
        }
	}
}
