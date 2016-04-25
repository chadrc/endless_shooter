package com.clockwork.endlessshooter.desktop;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.clockwork.endlessshooter.EndlessShooter;

public class DesktopLauncher {
	public static void main (String[] arg) {
		LwjglApplicationConfiguration config = new LwjglApplicationConfiguration();
		config.title = "Endless Shooter";
		config.width = 800;
		config.height = 800;
		new LwjglApplication(new EndlessShooter(), config);
	}
}
