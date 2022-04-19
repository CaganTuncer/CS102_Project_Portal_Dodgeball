package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.PortalDodgeball;

import javax.swing.*;

public class MainMenu implements Screen {

    private static final int PLAY_BUTTON_WIDTH = 198;
    private static final int PLAY_BUTTON_HEIGHT = 84;
    private static final int SETTINGS_BUTTON_WIDTH = 270;
    private static final int SETTINGS_BUTTON_HEIGHT = 84;
    private static final int FRAME_WIDTH = 768;
    private static final int FRAME_HEIGHT = 768;

    PortalDodgeball game;

    Texture play;
    Texture play_sel;
    Texture settings;
    Texture settings_sel;
    Texture quit;
    Texture quit_sel;
    Texture history;
    Texture history_sel;
    Texture frame;


    public MainMenu(PortalDodgeball game){
        this.game = game;
        play = new Texture("Main_Menu/PLAY.png");
        play_sel = new Texture("Main_Menu/PLAY_sel.png");
        settings = new Texture("Main_Menu/SETTINGS.png");
        settings_sel = new Texture("Main_Menu/SETTINGS_sel.png");
        quit = new Texture("Main_Menu/QUIT.png");
        quit_sel = new Texture("Main_Menu/QUIT_sel.png");
        history = new Texture("Main_Menu/HISTORY.png");
        history_sel = new Texture("Main_Menu/HISTORY_sel.png");
        frame = new Texture("Main_Menu/FRAME.png");

    }
    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        int frameX = (PortalDodgeball.WIDTH / 2) - (FRAME_WIDTH / 2);
        int frameY = (PortalDodgeball.HEIGHT / 2) - (FRAME_HEIGHT / 2);
        int playX = (PortalDodgeball.WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2);
        int playY = (PortalDodgeball.HEIGHT / 2) - (PLAY_BUTTON_HEIGHT / 2);
        int settingsX = (PortalDodgeball.WIDTH / 2) - (SETTINGS_BUTTON_WIDTH / 2);
        int settingsY = playY - (PLAY_BUTTON_HEIGHT) - 18;
        int quitX = (PortalDodgeball.WIDTH / 2) - (PLAY_BUTTON_WIDTH / 2);
        int quitY = settingsY - (PLAY_BUTTON_HEIGHT) - 18;
        int historyX = 6;
        int historyY = 12;

        game.batch.begin();

        Gdx.gl.glClearColor(0/255f, 98/255f, 228/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        PortalDodgeball.FPS = 60;

        game.batch.draw(frame, frameX, frameY);

        if(Gdx.input.getX() >= playX && Gdx.input.getX() <= playX + PLAY_BUTTON_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= playY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= playY + PLAY_BUTTON_HEIGHT){
            game.batch.draw(play_sel, playX, playY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new Mode_Selection(game));
            }
        } else {
            game.batch.draw(play, playX, playY);
        }

        if(Gdx.input.getX() >= settingsX && Gdx.input.getX() <= settingsX + SETTINGS_BUTTON_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= settingsY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= settingsY + SETTINGS_BUTTON_HEIGHT){
            game.batch.draw(settings_sel, settingsX, settingsY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new Settings(game));
            }
        } else {
            game.batch.draw(settings, settingsX, settingsY);
        }

        if(Gdx.input.getX() >= quitX && Gdx.input.getX() <= quitX + PLAY_BUTTON_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= quitY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= quitY + PLAY_BUTTON_HEIGHT) {
            game.batch.draw(quit_sel, quitX, quitY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                Gdx.app.exit();
            }
        } else {
            game.batch.draw(quit, quitX, quitY);
        }

        if(Gdx.input.getX() >= historyX && Gdx.input.getX() <= historyX + SETTINGS_BUTTON_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= historyY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= historyY + SETTINGS_BUTTON_HEIGHT){
            game.batch.draw(history_sel, 12, 6);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
            }
        } else {
            game.batch.draw(history, 12, 6);
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {
    }

    @Override
    public void pause() {}

    @Override
    public void resume() {}

    @Override
    public void hide() {}

    @Override
    public void dispose() {

    }
}
