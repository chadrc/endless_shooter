package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Rectangle;
import com.badlogic.gdx.math.Vector2;

public class EndlessShooter extends ApplicationAdapter {
    private static EndlessShooter current;
    private static float ScreenWidth = 800;
    private static float ScreenHeight = 800;
    static OrthographicCamera MainCamera;
    private SpriteBatch mainBatch;
    private World world;
    private Rectangle bounds;
    private EnemySpawner spawner;

    private static float OriginalWidth = 800;
    private static float OriginalHeight = 800;
	
	@Override
	public void create () {
        current = this;
        ScreenWidth = Gdx.graphics.getWidth();
        ScreenHeight = Gdx.graphics.getHeight();
        OriginalWidth = ScreenWidth;
        OriginalHeight = ScreenHeight;
        Assets.LoadAssets();
        world = new World();
        mainBatch = new SpriteBatch();
		MainCamera = new OrthographicCamera();
        MainCamera.setToOrtho(false, ScreenWidth, ScreenHeight);

        Player player = new Player();
        player.setX(ScreenWidth/2);
        player.setY(ScreenHeight/2);
        player.setSpeed(100);

        spawner = new EnemySpawner();

        world.addWorldObject(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MainCamera.update();
        spawner.update();

        mainBatch.setProjectionMatrix(MainCamera.combined);
        mainBatch.begin();
        world.update();
        mainBatch.end();

        if (Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)) {
            Gdx.app.exit();
        }
	}

    @Override
    public void resize(int screenWidth, int screenHeight) {
        ScreenWidth = screenWidth;
        ScreenHeight = screenHeight;
        float viewX = (screenWidth - OriginalWidth) / 2;
        float viewY = (screenHeight - OriginalHeight) / 2;
        MainCamera.setToOrtho(false, ScreenWidth, ScreenHeight);
        MainCamera.translate(-viewX, -viewY);
        bounds = new Rectangle(-viewX, -viewY, screenWidth, screenHeight);
    }

    static float RandomScreenX() {
        return MathUtils.random(current.bounds.getX(), current.bounds.getWidth()+current.bounds.getX());
    }

    static float RandomScreenY() {
        return MathUtils.random(current.bounds.getY(), current.bounds.getHeight()+current.bounds.getY());
    }

    static Vector2 RandomScreenPoint() {
        return new Vector2(RandomScreenX(), RandomScreenY());
    }

    static Rectangle GetScreenBounds()
    {
        return current.bounds;
    }

    static SpriteBatch GetMainBatch() {
        return current.mainBatch;
    }

    static void RegisterWorldObject(IWorldObject obj) {
        current.world.addWorldObject(obj);
    }

    static void UnregisterWorldObject(IWorldObject obj) {
        current.world.removeWorldObject(obj);
    }
}
