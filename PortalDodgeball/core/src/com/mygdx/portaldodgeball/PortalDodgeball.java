package com.mygdx.portaldodgeball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.Entities.PowerUp;
import com.mygdx.portaldodgeball.Entities.map.Wall;
import com.mygdx.portaldodgeball.screens.MainGameScreen;
import com.mygdx.portaldodgeball.screens.MainMenu;

import java.util.ArrayList;

public class PortalDodgeball extends Game {
	public ArrayList<PowerUp> powerUps= new ArrayList<PowerUp>();
	public ArrayList<PowerUp> deadPowerUps = new ArrayList<PowerUp>();
	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	public static int FPS = 60;

	public float clickVol = 0.5f;

	public String[] names = {null, null, null, null, null, null};
	public Player[] players;
	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public BitmapFont scoreFont;



	public SpriteBatch batch;

	public Music music;
	public Sound click;
	public Wall[] walls;

	public void setPlayers(Player[] players){
		this.players = players;
	}

	@Override
	public void create () {
		batch = new SpriteBatch();
		music = Gdx.audio.newMusic(Gdx.files.internal("Audio/main_theme.mp3"));
		click = Gdx.audio.newSound(Gdx.files.internal("Audio/click.wav"));
		music.setLooping(true);
		music.setVolume(0.5f);
		music.play();
		this.players = new Player[0];
		this.setScreen(new MainMenu(this));

		fontGenerator = new FreeTypeFontGenerator(Gdx.files.internal("FFFFORWA.TTF"));
		fontParameter = new FreeTypeFontGenerator.FreeTypeFontParameter();
		fontParameter.size = 250;
		fontParameter.borderColor = Color.BLACK;
		fontParameter.color = Color.BLACK;
		fontParameter.borderWidth = 1;
		scoreFont = fontGenerator.generateFont(fontParameter);
		this.powerUps.add(new PowerUp(1,this.mainGameScreen,0, 1000,350));
		this.powerUps.add(new PowerUp(1,this.mainGameScreen,0, 700,500));
		this.powerUps.add(new PowerUp(0,this.mainGameScreen,0, 1300,200));
		this.powerUps.add(new PowerUp(0,this.mainGameScreen,0, 650,350));

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
