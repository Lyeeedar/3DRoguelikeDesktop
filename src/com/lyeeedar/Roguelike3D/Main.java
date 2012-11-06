package com.lyeeedar.Roguelike3D;

import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;

public class Main {
	public static void main(String[] args) {
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		cfg.title = "Roguelike3D";
		cfg.width = 800;
		cfg.height = 600;
		cfg.useGL20 = true;
		
		new LwjglApplication(new Roguelike3DGame(), cfg);
	}
}
