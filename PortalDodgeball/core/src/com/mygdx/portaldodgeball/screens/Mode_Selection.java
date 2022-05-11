package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class Mode_Selection implements Screen {

    PortalDodgeball game;

    private final int TWOP_WIDTH = 282;
    private final int TWOP_HEIGHT = 84;
    private final int THREEP_WIDTH = 276;
    private final int THREEP_HEIGHT = 84;
    private final int BACK_WIDTH = 258;
    private final int BACK_HEIGHT = 66;
    private static final int FRAME_WIDTH = 768;
    private static final int FRAME_HEIGHT = 768;

    public Player[] players;

    Texture twop;
    Texture twop_sel;
    Texture threep;
    Texture threep_sel;
    Texture fourp;
    Texture fourp_sel;
    Texture sixp;
    Texture sixp_sel;
    Texture back;
    Texture back_sel;
    Texture frame;

    public Mode_Selection(PortalDodgeball game){
        this.game = game;

        twop = new Texture("Play Menu/2P.png");
        twop_sel = new Texture("Play Menu/2P_sel.png");
        threep = new Texture("Play Menu/3P.png");
        threep_sel = new Texture("Play Menu/3P_sel.png");
        fourp = new Texture("Play Menu/4P.png");
        fourp_sel = new Texture("Play Menu/4P_sel.png");
        sixp = new Texture("Play Menu/6P.png");
        sixp_sel = new Texture("Play Menu/6P_sel.png");
        frame = new Texture("Play Menu/Play_Screen.png");
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

        int frameX = (PortalDodgeball.WIDTH / 2) - (FRAME_WIDTH / 2);
        int frameY = (PortalDodgeball.HEIGHT / 2) - (FRAME_HEIGHT / 2);
        int backX = 6;
        int backY = PortalDodgeball.HEIGHT - BACK_HEIGHT - 6;
        int fourpX = frameX + 102; //17 51
        int fourpY = frameY + 306;
        int sixpX = fourpX + TWOP_WIDTH - 6;
        int sixpY = fourpY;
        int twopX = fourpX;
        int twopY = fourpY - 210;
        int threepX = sixpX;
        int threepY = twopY;


        game.batch.draw(frame, frameX, frameY);

        if(Gdx.input.getX() >= backX && Gdx.input.getX() <= backX + BACK_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= backY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= backY + BACK_HEIGHT){
            game.batch.draw(back_sel, backX, backY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                game.setScreen(new MainMenu(game));
            }
        } else {
            game.batch.draw(back, backX, backY);
        }

        if(Gdx.input.getX() >= twopX && Gdx.input.getX() <= twopX + TWOP_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= twopY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= twopY + TWOP_HEIGHT){
            game.batch.draw(twop_sel, twopX, twopY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                Player.id = 0;
                Player player1 = new Player("EFE", game);
                Player player2 = new Player("ALP", game);
                this.players = new Player[]{player1, player2};
                game.setPlayers(players);
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(twop, twopX, twopY);
        }

        if(Gdx.input.getX() >= threepX && Gdx.input.getX() <= threepX + THREEP_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= threepY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= threepY + THREEP_HEIGHT){
            game.batch.draw(threep_sel, threepX, threepY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                Player.id = 0;
                Player player1 = new Player("EFE", game);
                Player player2 = new Player("ALP", game);
                Player player3 = new Player("ART", game);
                this.players = new Player[]{player1, player2, player3};
                game.setPlayers(players);
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(threep, threepX, threepY);
        }

        if(Gdx.input.getX() >= fourpX && Gdx.input.getX() <= fourpX + TWOP_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= fourpY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= fourpY + TWOP_HEIGHT){
            game.batch.draw(fourp_sel, fourpX, fourpY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                Player player1 = new Player("p1", game);
                Player player2 = new Player("p2", game);
                Player player3 = new Player("p3", game);
                Player player4 = new Player("p4", game);
                this.players = new Player[]{player1, player2, player3, player4};
                game.setPlayers(players);
            }
        } else {
            game.batch.draw(fourp, fourpX, fourpY);
        }

        if(Gdx.input.getX() >= sixpX && Gdx.input.getX() <= sixpX + THREEP_WIDTH && PortalDodgeball.HEIGHT - Gdx.input.getY() >= sixpY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= sixpY + THREEP_HEIGHT){
            game.batch.draw(sixp_sel, sixpX, sixpY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                Player player1 = new Player("p1", game);
                Player player2 = new Player("p2", game);
                Player player3 = new Player("p3", game);
                Player player4 = new Player("p4", game);
                Player player5 = new Player("p5", game);
                Player player6 = new Player("p6", game);
                this.players = new Player[]{player1, player2, player3, player4, player5, player6};
                game.setPlayers(players);
            }
        } else {
            game.batch.draw(sixp, sixpX, sixpY);
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
