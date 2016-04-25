package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector3;

public class EndlessShooter extends ApplicationAdapter {
	SpriteBatch batch;
	Texture img;
	OrthographicCamera camera;
    Rectangle rectangle;
	
	@Override
	public void create () {
		batch = new SpriteBatch();
		img = new Texture("badlogic.jpg");
		camera = new OrthographicCamera();
        camera.setToOrtho(false, 800, 800);
        rectangle = new Rectangle();
        rectangle.x = 400 - 256/2;
        rectangle.y = 400 - 256/2;
        rectangle.width = 40;
        rectangle.height = 40;
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(1, 0, 0, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        batch.setProjectionMatrix(camera.combined);
		batch.begin();
		batch.draw(img, rectangle.x, rectangle.y);
		batch.end();

        if (Gdx.input.isTouched()) {
            Vector3 touchPos = new Vector3();
            touchPos.set(Gdx.input.getX(), Gdx.input.getY(), 0);
            camera.unproject(touchPos);
            System.out.println("Touched: " + touchPos);
        }

        if (Gdx.input.isKeyPressed(Input.Keys.W)) {
            System.out.println("Move Up");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.A)) {
            System.out.println("Move Left");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.S)) {
            System.out.println("Move Down");
        }

        if (Gdx.input.isKeyPressed(Input.Keys.D)) {
            System.out.println("Move Right");
        }
	}
}
