package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Ball;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.PortalDodgeball;
import com.sun.org.apache.bcel.internal.generic.BALOAD;

import java.util.ArrayList;
import java.util.Collection;

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
        game.batch.begin();
        ScreenUtils.clear(1, 0, 0, 1);
        Gdx.gl.glClearColor(0/255f, 98/255f, 228/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
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

        for(int i = 0; i < game.players.length; i++){
            for (Ball ball: game.players[i].balls) {
                if(ball.isLifeSpanOver() == true){
                    Player.deadBalls.add(ball);
                }
<<<<<<< HEAD
                for(int j = 0; j < game.players.length; j++ ){
                    if(ball.getHitbox().collidesWidth(game.players[j].hitbox)){
                        Player.deadBalls.add(ball);
                        game.players[j].setTexture("Players/Player 3/player0.png");
                    }
                }
=======
>>>>>>> ball
            }
        }

        for(int i = 0; i < game.players.length; i++){
            game.players[i].balls.removeAll(Player.deadBalls);
        }



        for(int i = 0; i < game.players.length; i++){
            game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
            for (Ball ball: game.players[i].balls) {
                ball.draw(game.batch);
            }
        }

        for(int i = 0; i < game.players.length; i++){
            game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
            for (Ball ball: game.players[i].balls) {
                    ball.draw(game.batch);
            }
        }

        if(Gdx.input.isKeyPressed(Input.Keys.ESCAPE)){
            game.setScreen(new MainMenu(game));
            Player.id = 0;
            game.setPlayers(new Player[]{});
        }
        game.batch.end();
    }
    public void drawMap(){
        MapRender g = new MapRender(1);
        int[][] map = g.returnMap();
        int WunitSize = 3;
        Texture wallUnit = new Texture("Gameplay sprites/wall unit piece.png");
        game.walls = g.walls;
        for(int i = 0; i < map.length; i++){
            int Wx = map[i][0];
            int Wy= map[i][1];
            int Wheight = map[i][2];
            int Wwidth = map[i][3];
            int a = Wy;
            int b = Wx;
            while(b < Wx+ Wwidth){
                while(a< Wy + Wheight){
                    game.batch.draw(wallUnit,b,a);
                    a += WunitSize;
                }
                b += WunitSize;
                a =Wy;
            }
        }


        for(int i = 0; i < game.players.length; i++){
            for (Ball ball: game.players[i].balls) {
                ball.update(Gdx.graphics.getDeltaTime());
            }
        }

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