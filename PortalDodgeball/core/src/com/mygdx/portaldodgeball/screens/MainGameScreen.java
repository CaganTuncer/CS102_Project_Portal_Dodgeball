package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class MainGameScreen implements Screen {

    Texture img;
    Player player1 = new Player("Universal/BACK.png", "temp");
    float x, y;

    PortalDodgeball game;

    public MainGameScreen(PortalDodgeball game){
        this.game = game;
    }

    @Override
    public void show() {
        //img = new Texture("Universal/badlogic.jpg");
    }

    @Override
    public void render(float delta) {
        /*if(Gdx.input.isKeyPressed(Input.Keys.W)){
            y += 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.S)){
            y -= 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.D)){
            x += 4;
        }
        if(Gdx.input.isKeyPressed(Input.Keys.A)){
            x -= 4;
        }*/

        player1.move();

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
        }

        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();
        game.batch.draw(player1, player1.x, player1.y);
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
