package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.PortalDodgeball;

import java.util.ArrayList;

public class End_Game implements Screen {

    PortalDodgeball game;

    Player firstPlayer, secondPlayer, thirdPlayer;

    int callAmount, max, frameX, frameY, menuX, menuY, rematchX, rematchY, p1IconX, p1IconY, p1NameX, p1NameY, p1ScoreX, p1ScoreY, p2IconX, p2IconY, p2NameX, p2NameY, p2ScoreX, p2ScoreY, p3IconX, p3IconY, p3NameX, p3NameY, p3ScoreX, p3ScoreY;

    ArrayList<Player> playersEnd;

    Texture p1, p2, p3, frame, menu, menu_sel, rematch, rematch_sel;


    public End_Game(PortalDodgeball game){
        playersEnd = new ArrayList<Player>();
        for(int i = 0; i < game.players.length; i++){
            Player temp = game.players[i];
            this.playersEnd.add(temp);
        }
        this.game = game;
        if(this.game.players.length == 3)
            frame = new Texture("Game Over/Game_Over.png");
        else
            frame = new Texture("Game Over/Game_Over2.png");
        p1 = new Texture("Game Over/p1.png");
        p2 = new Texture("Game Over/p2.png");
        p3 = new Texture("Game Over/p3.png");
        menu = new Texture("Game Over/MENU.png");
        menu_sel = new Texture("Game Over/MENU_sel.png");
        rematch = new Texture("Game Over/REMATCH.png");
        rematch_sel = new Texture("Game Over/REMATCH_sel.png");
        callAmount = 0;
        max = Integer.MIN_VALUE;
        frameX = (PortalDodgeball.WIDTH / 2) - (384);
        frameY = (PortalDodgeball.HEIGHT / 2) - (384);
        menuX = 12;
        menuY = 12;
        rematchX = PortalDodgeball.WIDTH - 270 - 12;
        rematchY = 12;
        p1IconX = frameX + (21 * 6) + 3;
        p1IconY = frameY + (65 * 6);
        p1NameX = p1IconX + (28 * 6);
        p1NameY = p1IconY + (14 * 6) - 6;
        p1ScoreX = p1NameX + (52 * 6);
        p1ScoreY = p1NameY;
        p2IconX = p1IconX;
        p2IconY = frameY + (44 * 6) - 6;
        p2NameX = p1NameX;
        p2NameY = p1IconY - (9 * 6);
        p2ScoreX = p1ScoreX;
        p2ScoreY = p2NameY;
        p3IconX = p1IconX;
        p3IconY = frameY + (23 * 6) - 12;
        p3NameX = p1NameX;
        p3NameY = p1IconY - (31 * 6);
        p3ScoreX = p1ScoreX;
        p3ScoreY = p3NameY;
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
        game.batch.draw(frame, frameX, frameY);
        if(Gdx.input.getX() >= menuX && Gdx.input.getX() <= menuX + 192 && PortalDodgeball.HEIGHT - Gdx.input.getY() >= menuY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= menuY + 84){
            game.batch.draw(menu_sel, menuX, menuY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                this.game.players = new Player[]{};
                game.setScreen(new MainMenu(game));
            }
        } else {
            game.batch.draw(menu, menuX, menuY);
        }
        if(Gdx.input.getX() >= rematchX && Gdx.input.getX() <= rematchX + 270 && PortalDodgeball.HEIGHT - Gdx.input.getY() >= rematchY && PortalDodgeball.HEIGHT - Gdx.input.getY() <= rematchY + 84){
            game.batch.draw(rematch_sel, rematchX, rematchY);
            if(Gdx.input.justTouched()){
                game.click.play(game.clickVol);
                for(Player player : this.game.players)
                    player.score = 0;
                game.setScreen(new MainGameScreen(game));
            }
        } else {
            game.batch.draw(rematch, rematchX, rematchY);
        }
        if(callAmount == 0){
            for(Player player : this.playersEnd){
                if(player.score > max){
                    max = player.score;
                    firstPlayer = player;
                }
            }
            this.playersEnd.remove(firstPlayer);
            max = Integer.MIN_VALUE;
            switch(firstPlayer.number){
                case 0:
                    p1 = new Texture("Game Over/p1.png");
                    break;
                case 1:
                    p1 = new Texture("Game Over/p2.png");
                    break;
                case 2:
                    p1 = new Texture("Game Over/p3.png");
                    break;
            }
            for(Player player : playersEnd){
                if(player.score > max){
                    max = player.score;
                    secondPlayer = player;
                }
            }
            this.playersEnd.remove(secondPlayer);
            switch(secondPlayer.number){
                case 0:
                    p2 = new Texture("Game Over/p1.png");
                    break;
                case 1:
                    p2 = new Texture("Game Over/p2.png");
                    break;
                case 2:
                    p2 = new Texture("Game Over/p3.png");
                    break;
            }
            if(game.players.length == 3){
                thirdPlayer = playersEnd.get(0);
                switch(thirdPlayer.number){
                    case 0:
                        p3 = new Texture("Game Over/p1.png");
                        break;
                    case 1:
                        p3 = new Texture("Game Over/p2.png");
                        break;
                    case 2:
                        p3 = new Texture("Game Over/p3.png");
                        break;
                }
            }
            callAmount++;
        }
        game.batch.draw(p1, p1IconX, p1IconY);
        game.batch.draw(p2, p2IconX, p2IconY);
        game.p1ScoreFont.draw(game.batch, firstPlayer.name, p1NameX, p1NameY);
        game.p1ScoreFont.draw(game.batch, secondPlayer.name, p2NameX, p2NameY);
        game.p1ScoreFont.draw(game.batch, firstPlayer.score + "", p1ScoreX, p1ScoreY);
        game.p1ScoreFont.draw(game.batch, secondPlayer.score + "", p2ScoreX, p2ScoreY);
        if(game.players.length == 3){
            game.batch.draw(p3, p3IconX, p3IconY);
            game.p1ScoreFont.draw(game.batch, thirdPlayer.name, p3NameX, p3NameY);
            game.p1ScoreFont.draw(game.batch, thirdPlayer.score + "", p3ScoreX, p3ScoreY);
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
