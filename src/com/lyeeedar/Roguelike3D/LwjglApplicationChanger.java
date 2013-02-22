package com.lyeeedar.Roguelike3D;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;

import com.badlogic.gdx.Application;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Graphics.DisplayMode;
import com.badlogic.gdx.Preferences;
import com.badlogic.gdx.backends.lwjgl.LwjglApplication;
import com.badlogic.gdx.backends.lwjgl.LwjglApplicationConfiguration;
import com.badlogic.gdx.backends.lwjgl.LwjglPreferences;
import com.lyeeedar.Roguelike3D.Game.GameData;
import com.lyeeedar.Roguelike3D.Graphics.ApplicationChanger;
import com.lyeeedar.Roguelike3D.Graphics.Lights.LightManager;
import com.lyeeedar.Roguelike3D.Graphics.Lights.LightManager.LightQuality;

public class LwjglApplicationChanger extends ApplicationChanger {
	
	public LwjglApplicationChanger() {
		super(new LwjglPreferences("game-settings"));
	}

	@Override
	public Application createApplication(Roguelike3DGame game, Preferences pref) {
		
		LwjglApplicationConfiguration cfg = new LwjglApplicationConfiguration();
		
		cfg.title = pref.getString("window-name");
		cfg.width = pref.getInteger("resolutionX");
		cfg.height = pref.getInteger("resolutionY");
		cfg.fullscreen = pref.getBoolean("fullscreen");
		cfg.vSyncEnabled = pref.getBoolean("vSync");
		cfg.resizable = false;
		cfg.useGL20 = true;
		cfg.samples = pref.getInteger("MSAA-samples");
		
		GameData.resolution[0] = cfg.width;
		GameData.resolution[1] = cfg.height;
		
		GameData.lightQuality = LightManager.getLightQuality(prefs.getString("Renderer"));

		return new LwjglApplication(game, cfg);
	}

	@Override
	public void updateApplication(Preferences pref) {
		int width = pref.getInteger("resolutionX");
		int height = pref.getInteger("resolutionY");
		boolean fullscreen = pref.getBoolean("fullscreen");

		GameData.resolution[0] = width;
		GameData.resolution[1] = height;

		Gdx.graphics.setDisplayMode(width, height, fullscreen);
		Gdx.graphics.setVSync(pref.getBoolean("vSync"));
		
		GameData.lightQuality = LightManager.getLightQuality(prefs.getString("Renderer"));
	}

	@Override
	public String[] getSupportedDisplayModes() {
		DisplayMode[] displayModes = Gdx.graphics.getDisplayModes();
		
		ArrayList<String> modes = new ArrayList<String>();
		
		for (int i = 0; i < displayModes.length; i++)
		{
			String mode = displayModes[i].width+"x"+displayModes[i].height;
			
			boolean contained = false;
			for (String m : modes) 
			{
				if (m.equals(mode)) {
					contained = true;
					break;
				}
			}
			if (!contained)
			{
				modes.add(mode);
			}
		}

		Collections.sort(modes, new Comparator<String>(){

			@Override
			public int compare(String s1, String s2) {
				int split = s1.indexOf("x");
				int rX1 = Integer.parseInt(s1.substring(0, split));
				
				split = s2.indexOf("x");
				int rX2 = Integer.parseInt(s2.substring(0, split));
				
				if (rX1 < rX2) return -1;
				else if (rX1 > rX2) return 1;
				return 0;
			}
			
		});
		
		String[] m = new String[modes.size()];
		
		return modes.toArray(m);
	}

	@Override
	public void setToNativeResolution(Preferences prefs) {
		DisplayMode dm = Gdx.graphics.getDesktopDisplayMode();
		
		prefs.putInteger("resolutionX", dm.width);
		prefs.putInteger("resolutionY", dm.height);
		
		updateApplication(prefs);
	}

}
