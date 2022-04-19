package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class Settings implements Screen {

    private static final int FRAME_WIDTH = 768;
    private static final int FRAME_HEIGHT = 768;
    private final int MENU_WIDTH = 258;
    private final int MENU_HEIGHT = 66;
    private final int PLUS_WIDTH = 36;
    private final int PLUS_HEIGHT = 36;
    private final int MINUS_WIDTH = 30;
    private final int MINUS_HEIGHT = 36;
    private final int SOUND_WIDTH = 228;
    private final int SOUND_HEIGHT = 36;
    private final int NUM_WIDTH = 30;
    private final int NUM_HEIGHT = 42;
    private final int CONTROLS_WIDTH = 552;
    private final int CONTROLS_HEIGHT = 102;

    public static int sLevel = 5;

    PortalDodgeball game;

    Texture frame;
    Texture plus_minus;
    Texture plus_sel;
    Texture minus_sel;
    Texture zero;
    Texture one;
    Texture two;
    Texture three;
    Texture four;
    Texture five;
    Texture six;
    Texture seven;
    Texture eight;
    Texture nine;
    Texture ten;
    Texture menu;
    Texture menu_sel;
    Texture controls;
    Texture controls_sel;
    Texture nZero;
    Texture nOne;
    Texture nTwo;
    Texture nThree;
    Texture nFour;
    Texture nFive;
    Texture nSix;
    Texture nSeven;
    Texture nEight;
    Texture nNine;
    Texture nTen;

    public Settings(PortalDodgeball game){
        this.game = game;
        frame = new Texture("Settings/FRAME.png");
        plus_minus = new Texture("Settings/PLUS_MINUS.png");
        plus_sel = new Texture("Settings/PLUS_SEL.png");
        minus_sel = new Texture("Settings/MINUS_SEL.png");
        zero = new Texture("Settings/Sound_Levels/0.png");
        one = new Texture("Settings/Sound_Levels/1.png");
        two = new Texture("Settings/Sound_Levels/2.png");
        three = new Texture("Settings/Sound_Levels/3.png");
        four = new Texture("Settings/Sound_Levels/4.png");
        five = new Texture("Settings/Sound_Levels/5.png");
        six = new Texture("Settings/Sound_Levels/6.png");
        seven = new Texture("Settings/Sound_Levels/7.png");
        eight = new Texture("Settings/Sound_Levels/8.png");
        nine = new Texture("Settings/Sound_Levels/9.png");
        ten = new Texture("Settings/Sound_Levels/10.png");
        menu = new Texture("Universal/MENU.png");
        menu_sel = new Texture("Universal/MENU_sel.png");
        controls = new Texture("Settings/CONTROLS_.png");
        controls_sel = new Texture("Settings/CONTROLS_sel.png");
        nZero = new Texture("Numbers/0.png");
        nOne = new Texture("Numbers/1.png");
        nTwo = new Texture("Numbers/2.png");
        nThree = new Texture("Numbers/3.png");
        nFour = new Texture("Numbers/4.png");
        nFive = new Texture("Numbers/5.png");
        nSix = new Texture("Numbers/6.png");
        nSeven = new Texture("Numbers/7.png");
        nEight = new Texture("Numbers/8.png");
        nNine = new Texture("Numbers/9.png");
        nTen = new Texture("Numbers/10.png");
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {
        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();

        Gdx.gl.glClearColor(0/255f, 98/255f, 228/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        PortalDodgeball.FPS = 60;

        int frameX = (PortalDodgeball.WIDTH / 2) - (FRAME_WIDTH / 2);
        int frameY = (PortalDodgeball.HEIGHT / 2) - (FRAME_HEIGHT / 2);
        int menuX = 6;
        int menuY = PortalDodgeball.HEIGHT - MENU_HEIGHT - 6;
        int controlsX = (PortalDodgeball.WIDTH / 2) - (CONTROLS_WIDTH / 2);
        int controlsY = (PortalDodgeball.HEIGHT / 2) - (CONTROLS_HEIGHT / 2) - 66;
        int soundX = controlsX + 132;
        int soundY = controlsY + 162;
        int plusX = soundX + 258;
        int plusY = soundY;
        int minusX = plusX + 90;
        int minusY = plusY;
        int numX = plusX + 48;
        int numY = plusY;

        game.batch.draw(frame, frameX, frameY);

        if(Gdx.input.getX() >= menuX && Gdx.input.getX() <= menuX + MENU_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= menuY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= menuY + MENU_HEIGHT){
            game.batch.draw(menu_sel, menuX, menuY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new MainMenu(game));
            }
        } else {
            game.batch.draw(menu, menuX, menuY);
        }

        if(Gdx.input.getX() >= controlsX && Gdx.input.getX() <= controlsX + CONTROLS_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= controlsY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= controlsY + CONTROLS_HEIGHT){
            game.batch.draw(controls_sel, controlsX, controlsY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new Controls(game));
            }
        } else {
            game.batch.draw(controls, controlsX, controlsY);
        }

        if(Gdx.input.getX() >= plusX && Gdx.input.getX() <= plusX + PLUS_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= plusY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= plusY + PLUS_HEIGHT){
            game.batch.draw(plus_sel, plusX, plusY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                if(sLevel < 10){
                    sLevel++;
                }
            }
        } else if(Gdx.input.getX() >= minusX && Gdx.input.getX() <= minusX + MINUS_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= minusY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= minusY + MINUS_HEIGHT){
            game.batch.draw(minus_sel, plusX, plusY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                if(sLevel > 0){
                    sLevel--;
                }
            }
        } else {
            game.batch.draw(plus_minus, plusX, plusY);
        }

        switch (sLevel) {
            case 0:
                game.batch.draw(zero, soundX, soundY);
                break;
            case 1:
                game.batch.draw(one, soundX, soundY);
                break;
            case 2:
                game.batch.draw(two, soundX, soundY);
                break;
            case 3:
                game.batch.draw(three, soundX, soundY);
                break;
            case 4:
                game.batch.draw(four, soundX, soundY);
                break;
            case 5:
                game.batch.draw(five, soundX, soundY);
                break;
            case 6:
                game.batch.draw(six, soundX, soundY);
                break;
            case 7:
                game.batch.draw(seven, soundX, soundY);
                break;
            case 8:
                game.batch.draw(eight, soundX, soundY);
                break;
            case 9:
                game.batch.draw(nine, soundX, soundY);
                break;
            case 10:
                game.batch.draw(ten, soundX, soundY);
                break;
        }

        switch (sLevel) {
            case 0:
                game.batch.draw(nZero, numX, numY);
                game.music.setVolume(0);
                game.clickVol = 0;
                break;
            case 1:
                game.batch.draw(nOne, numX, numY);
                game.music.setVolume(0.1f);
                game.clickVol = 0.1f;
                break;
            case 2:
                game.batch.draw(nTwo, numX, numY);
                game.music.setVolume(0.2f);
                game.clickVol = 0.2f;
                break;
            case 3:
                game.batch.draw(nThree, numX, numY);
                game.music.setVolume(0.3f);
                game.clickVol = 0.3f;
                break;
            case 4:
                game.batch.draw(nFour, numX, numY);
                game.music.setVolume(0.4f);
                game.clickVol = 0.4f;
                break;
            case 5:
                game.batch.draw(nFive, numX, numY);
                game.music.setVolume(0.5f);
                game.clickVol = 0.5f;
                break;
            case 6:
                game.batch.draw(nSix, numX, numY);
                game.music.setVolume(0.6f);
                game.clickVol = 0.6f;
                break;
            case 7:
                game.batch.draw(nSeven, numX, numY);
                game.music.setVolume(0.7f);
                game.clickVol = 0.7f;
                break;
            case 8:
                game.batch.draw(nEight, numX, numY);
                game.music.setVolume(0.8f);
                game.clickVol = 0.8f;
                break;
            case 9:
                game.batch.draw(nNine, numX, numY);
                game.music.setVolume(0.9f);
                game.clickVol = 0.9f;
                break;
            case 10:
                game.batch.draw(nTen, numX, numY);
                game.music.setVolume(1);
                game.clickVol = 1;
                break;
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.click.play(game.clickVol);
            game.setScreen(new MainMenu(game));
        }

        game.batch.end();
    }

    @Override
    public void resize(int width, int height) {

    }

    @Override
    public void pause() {

    }

    @Override
    public void resume() {

    }

    @Override
    public void hide() {

    }

    @Override
    public void dispose() {

    }
}
