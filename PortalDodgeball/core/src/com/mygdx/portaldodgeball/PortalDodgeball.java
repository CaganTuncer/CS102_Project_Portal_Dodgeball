package com.mygdx.portaldodgeball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.Tools.InputManager;
import com.mygdx.portaldodgeball.screens.MainGameScreen;
import com.mygdx.portaldodgeball.screens.MainMenu;

public class PortalDodgeball extends Game {

	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	public static int FPS = 60;

	public float clickVol = 0.5f;

	public String[] names = {null, null, null, null, null, null};
	public Player[] players;

	public InputManager inputManager;

	public SpriteBatch batch;

	public Music music;
	public Sound click;

	public MainGameScreen mainGameScreen = new MainGameScreen(this);

	public void setPlayers(Player[] players){
		this.players = players;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		inputManager = new InputManager();
		Gdx.input.setInputProcessor(inputManager);
		music = Gdx.audio.newMusic(Gdx.files.internal("Audio/main_theme.mp3"));
		click = Gdx.audio.newSound(Gdx.files.internal("Audio/click.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		this.players = new Player[0];
		this.setScreen(new MainMenu(this));
	}

	@Override
	public void render () {
		super.render();
	}

	@Override
	public void dispose(){
		super.dispose();
		music.dispose();
	}
}
