package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class Controls implements Screen {

    private static final int CONTROLS_WIDTH = 768;
    private static final int CONTROLS_HEIGHT = 768;
    private final int BACK_WIDTH = 258;
    private final int BACK_HEIGHT = 66;

    PortalDodgeball game;

    Texture controls;
    Texture back;
    Texture back_sel;

    public Controls(PortalDodgeball game){
        this.game = game;
        controls = new Texture("Settings/Controls/CONTROLS.png");
        back = new Texture("Universal/BACK.png");
        back_sel = new Texture("Universal/BACK_sel.png");
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

        int controlsX = (PortalDodgeball.WIDTH / 2) - (CONTROLS_WIDTH / 2);
        int controlsY = (PortalDodgeball.HEIGHT / 2) - (CONTROLS_HEIGHT / 2);
        int backX = 6;
        int backY = PortalDodgeball.HEIGHT - BACK_HEIGHT - 6;

        game.batch.draw(controls, controlsX, controlsY);

        if(Gdx.input.getX() >= backX && Gdx.input.getX() <= backX + BACK_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= backY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= backY + BACK_HEIGHT){
            game.batch.draw(back_sel, backX, backY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new Settings(game));
            }
        } else {
            game.batch.draw(back, backX, backY);
        }

        if(Gdx.input.isKeyJustPressed(Input.Keys.ESCAPE)){
            game.click.play(game.clickVol);
            game.setScreen(new Settings(game));
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
