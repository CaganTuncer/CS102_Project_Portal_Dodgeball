package com.mygdx.portaldodgeball;

import com.badlogic.gdx.backends.lwjgl3.Lwjgl3Application;
import com.badlogic.gdx.backends.lwjgl3.Lwjgl3ApplicationConfiguration;
import com.mygdx.portaldodgeball.PortalDodgeball;

// Please note that on macOS your application needs to be started with the -XstartOnFirstThread JVM argument
public class DesktopLauncher {
	public static void main (String[] arg) {
		Lwjgl3ApplicationConfiguration config = new Lwjgl3ApplicationConfiguration();
		config.setForegroundFPS(PortalDodgeball.FPS);
		config.setTitle("Portal Dodgeball!");
		config.setResizable(false);
		config.setWindowedMode(PortalDodgeball.WIDTH, PortalDodgeball.HEIGHT);
		new Lwjgl3Application(new PortalDodgeball(), config);
	}
}
