package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class EndlessShooter extends ApplicationAdapter {
    public static float ScreenWidth = 800;
    public static float ScreenHeight = 800;

	SpriteBatch batch;
	OrthographicCamera camera;

    Player player;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		camera = new OrthographicCamera();
        camera.setToOrtho(false, ScreenWidth, ScreenHeight);
        player = new Player();
        player.setX(ScreenWidth/2);
        player.setY(ScreenHeight/2);
        player.setSpeed(2);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
        batch.begin();
        player.render(batch);
        batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
        }

        Vector3 touchPos = new Vector3();
        touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
        camera.unproject(touchPos);
        float difx = player.getX() - touchPos.x;
        float dify = player.getY() - touchPos.y;
        float tan = MathUtils.atan2(difx, dify);
        float angle = -tan * MathUtils.radiansToDegrees + 180;
        player.setRotation(angle);
        System.out.println("Rotate: " + angle);

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
