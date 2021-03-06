package com.mygdx.portaldodgeball;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.audio.Music;
import com.badlogic.gdx.audio.Sound;
import com.badlogic.gdx.graphics.Color;
import com.badlogic.gdx.graphics.g2d.BitmapFont;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.graphics.g2d.freetype.FreeTypeFontGenerator;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.Entities.PowerUp;
import com.mygdx.portaldodgeball.Entities.map.Wall;
import com.mygdx.portaldodgeball.screens.MainMenu;

import java.util.ArrayList;

public class PortalDodgeball extends Game {
	public ArrayList<PowerUp> powerUps= new ArrayList<PowerUp>();
	public ArrayList<PowerUp> deadPowerUps = new ArrayList<PowerUp>();
	public static int WIDTH = 1600;
	public static int HEIGHT = 900;
	public static int FPS = 60;

	public int map = 1;

	public float clickVol = 0.5f;

	public String[] names = {null, null, null, null, null, null};
	public Player[] players;
	public FreeTypeFontGenerator fontGenerator;
	public FreeTypeFontGenerator.FreeTypeFontParameter fontParameter;
	public BitmapFont p1ScoreFont, p2ScoreFont, p3ScoreFont, minuteFont, secondFont;

	public ArrayList<Player> playerList = new ArrayList<Player>();
	public ArrayList<Player> deadPlayerList = new ArrayList<Player>();

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
		fontParameter.size = 55;
		fontParameter.borderColor = Color.BLACK;
		fontParameter.color = Color.BLACK;
		fontParameter.borderWidth = 1;
		p1ScoreFont = fontGenerator.generateFont(fontParameter);
		p2ScoreFont = fontGenerator.generateFont(fontParameter);
		p3ScoreFont = fontGenerator.generateFont(fontParameter);
		secondFont = fontGenerator.generateFont(fontParameter);
		minuteFont = fontGenerator.generateFont(fontParameter);

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
