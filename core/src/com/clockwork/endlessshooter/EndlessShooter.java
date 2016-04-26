package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;

public class EndlessShooter extends ApplicationAdapter {
    public static float ScreenWidth = 800;
    public static float ScreenHeight = 800;
    public static ShapeRenderer Renderer;
    public static SpriteBatch Batch;
    public static OrthographicCamera MainCamera;
	
	@Override
	public void create () {
		Batch = new SpriteBatch();
        Renderer = new ShapeRenderer();
		MainCamera = new OrthographicCamera();
        MainCamera.setToOrtho(false, ScreenWidth, ScreenHeight);
        Player player = new Player();
        player.setX(ScreenWidth/2);
        player.setY(ScreenHeight/2);
        player.setSpeed(2);

        World.AddWorldObject(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        Batch.setProjectionMatrix(MainCamera.combined);
        Renderer.setProjectionMatrix(MainCamera.combined);

        World.Update();
	}
}
