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
    float x, y;

    PortalDodgeball game;



    public MainGameScreen(PortalDodgeball game){
        this.game = game;
    }



    @Override
    public void show() {
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


        switch (game.players.length){
            case 2:
                game.players[0].move();
                game.players[1].move();
                break;
            case 3:
                game.players[0].move();
                game.players[1].move();
                game.players[2].move();
                break;
            case 4:
                game.players[0].move();
                game.players[1].move();
                game.players[2].move();
                game.players[3].move();
                break;
            case 6:
                game.players[0].move();
                game.players[1].move();
                game.players[2].move();
                game.players[3].move();
                game.players[4].move();
                game.players[5].move();
                break;
        }



        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
        }

        ScreenUtils.clear(1, 0, 0, 1);

        game.batch.begin();
        for(int i = 0; i < game.players.length; i++){
            game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
        }
        game.batch.end();
        game.inputManager.update();
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
