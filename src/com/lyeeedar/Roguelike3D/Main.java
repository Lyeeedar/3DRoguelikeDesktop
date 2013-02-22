package com.lyeeedar.Roguelike3D;

import com.lyeeedar.Roguelike3D.Game.GameData;
  
public class Main {
	public static void main(String[] args) {
		
		System.setProperty("org.lwjgl.opengl.Window.undecorated","true");
		
		GameData.game = new Roguelike3DGame();
		GameData.applicationChanger = new LwjglApplicationChanger();
		GameData.createApplication();
	}
}