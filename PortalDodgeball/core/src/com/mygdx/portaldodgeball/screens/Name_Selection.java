package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Screen;
import com.mygdx.portaldodgeball.PortalDodgeball;

import java.util.ArrayList;

public class Name_Selection implements Screen {
    public int playerCount;
    public ArrayList<String> names = new ArrayList<String>();
    PortalDodgeball game;

    public Name_Selection(PortalDodgeball game){
        this.game = game;
        this.playerCount = game.players.length;

        switch (playerCount){

            case 2:

                break;
            case 3:

                break;
            case 4:

                break;
            case 6:

        }
    }

    public void enterName(String name){
        names.add(name);
    }

    @Override
    public void show() {

    }

    @Override
    public void render(float delta) {

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
