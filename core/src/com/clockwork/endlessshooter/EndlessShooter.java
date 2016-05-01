package com.clockwork.endlessshooter;

import com.badlogic.gdx.ApplicationAdapter;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.*;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.glutils.ShapeRenderer;
import com.badlogic.gdx.math.MathUtils;
import com.badlogic.gdx.math.Vector2;

public class EndlessShooter extends ApplicationAdapter {
    public static float ScreenWidth = 800;
    public static float ScreenHeight = 800;
    public static OrthographicCamera MainCamera;
    private static SpriteBatch MainBatch;
	
	@Override
	public void create () {
        MainBatch = new SpriteBatch();
		MainCamera = new OrthographicCamera();
        MainCamera.setToOrtho(false, ScreenWidth, ScreenHeight);
        Player player = new Player();
        player.setX(ScreenWidth/2);
        player.setY(ScreenHeight/2);
        player.setSpeed(100);

//        for (int i=0; i<10; i++) {
//            new Enemy(10, new Vector2(MathUtils.random(25, ScreenWidth-25),
//                    ScreenHeight + MathUtils.random(50, 100)));
//        }

        for (int i=0; i<10; i++) {
            new ShootingEnemy(10, new Vector2(MathUtils.random(25, ScreenWidth-25),
                    ScreenHeight + MathUtils.random(50, 100)));
        }

        World.AddWorldObject(player);
	}

	@Override
	public void render () {
		Gdx.gl.glClearColor(.3f, .3f, .3f, 1);
		Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);

        MainBatch.setProjectionMatrix(MainCamera.combined);
        MainBatch.begin();
        World.Update();
        MainBatch.end();
	}

    public static SpriteBatch GetMainBatch()
    {
        return MainBatch;
    }
}
