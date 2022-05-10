package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.Screen;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.badlogic.gdx.utils.TimeUtils;
import com.mygdx.portaldodgeball.Entities.Ball;
import com.mygdx.portaldodgeball.Entities.Player;
import com.mygdx.portaldodgeball.Entities.Portal;
import com.mygdx.portaldodgeball.Entities.PowerUp;
import com.mygdx.portaldodgeball.Entities.StillPortal;
import com.mygdx.portaldodgeball.Entities.map.MapRender;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class MainGameScreen implements Screen {

    PortalDodgeball game;
    Texture player1Score;
    Texture player2Score;
    Texture player3Score;
    Texture p1Screen;
    Texture p2Screen;
    Texture p3Screen;




    Texture timer;

    public long startTime = 0;
    int timeSecond = 20;
    int secondRemaining = 0;
    int timeMinute = timeSecond / 60;

    Texture wallUnit;
    MapRender g = new MapRender(1);

    public MainGameScreen(PortalDodgeball game){
        this.game = game;
        player1Score = new Texture("Game_Screen/player1Score.png");
        player2Score = new Texture("Game_Screen/player2Score.png");
        player3Score = new Texture("Game_Screen/player3Score.png");
        p1Screen = new Texture("Players/Player1edited/p1ThrowBeforeMiddle.png");
        p2Screen = new Texture("Players/Player2edited/p2ThrowBeforeMiddle.png");
        p3Screen = new Texture("Players/Player3edited/p3ThrowBeforeMiddle.png");
        timer = new Texture("Game_Screen/timer.png");
        wallUnit = new Texture("Gameplay sprites/wall unit piece.png");
        this.game.powerUps.add(new PowerUp(1,this,0, 1000,350));
        this.game.powerUps.add(new PowerUp(1,this,0, 700,500));
        this.game.powerUps.add(new PowerUp(0,this,0, 1300,200));
        this.game.powerUps.add(new PowerUp(0,this,0, 650,350));
    }

    @Override
    public void show() {
    }

    @Override
    public void render(float delta) {
        if(timeSecond < 0){
            game.setScreen(new End_Game(this.game));
        }
        game.batch.begin();
        ScreenUtils.clear(1, 0, 0, 1);
        Gdx.gl.glClearColor(0/255f, 98/255f, 228/255f, 1);
        Gdx.gl.glClear(GL20.GL_COLOR_BUFFER_BIT);
        drawMap();
        game.batch.draw(player1Score, 50, 790);
        game.batch.draw(player2Score, 1325, 790);

        game.batch.draw(timer, 678, 795);
        game.p1ScoreFont.draw(game.batch, game.players[0].score + "", 165,875);
        game.p2ScoreFont.draw(game.batch, game.players[1].score + "", 1395,875);
        if(game.players.length == 3) {
            game.batch.draw(player3Score, 687, 0);
            game.p3ScoreFont.draw(game.batch, game.players[2].score + "", 825, 85);

        }
        game.batch.draw(p1Screen,62,817);
        game.batch.draw(p2Screen,1499,817);
        game.batch.draw(p3Screen,711,30);


        if (TimeUtils.timeSinceNanos(startTime) > 1000000000) {
            // if time passed since the time you set startTime at is more than 1 second
            timeSecond--;
            //so this block will execute every one second
            startTime = TimeUtils.nanoTime();
        }
        if(secondRemaining < 10){
            game.secondFont.draw(game.batch, "0" + secondRemaining,818, 880);
        }
        else{
            game.secondFont.draw(game.batch, secondRemaining + "",818, 880);
        }
        timeMinute = timeSecond / 60;
        secondRemaining = timeSecond % 60;
        game.minuteFont.draw(game.batch,"0" + timeMinute, 708,880);

        switch (game.players.length){
            case 2:
                game.players[0].checkOrientation();
                game.players[0].move();
                game.players[1].checkOrientation();
                game.players[1].move();
                break;
            case 3:
                game.players[0].checkOrientation();
                game.players[0].move();
                game.players[1].checkOrientation();
                game.players[1].move();
                game.players[2].checkOrientation();
                game.players[2].move();
                break;
        }

        for(int i = 0; i < game.players.length; i++){
            for (Ball ball: game.players[i].balls) {
                if(ball.isLifeSpanOver()){
                    Player.deadBalls.add(ball);
                }
                for(int j = 0; j < game.players.length; j++ ){
                    if(ball.getHitbox().collidesWith(game.players[j].hitbox) && ball.player != game.players[j]){
                        Player.deadBalls.add(ball);
                        if(!game.players[j].hasShield){
                            game.players[j].die();
                            ball.player.score++;
                        }else {
                            game.players[j].hasShield = false;
                        }
                    }
                    
                    for (StillPortal still : game.players[i].stillPortals) {
                        if (ball.getHitbox().collidesWith(still.getHitbox())){
                            //Player.deadBalls.add(ball);
                            //ball.setTexture("Players/Player 3/player0.png");
                            int in = game.players[i].stillPortals.indexOf(still);
                            ball.transport(in);
                            //ball.setter(200,200);
                            //game.players[i].transportBall();
                            //Ball ballNew = (,still.Teleport().x, still.Teleport().y);
                            }
                        }
                }

                for (int j = 0; j < game.walls.length; j++) {

                    if(ball.getHitbox().collidesWith(game.walls[j].wallHitbox)){

                        if(ball.angle == 0){
                            ball.angle = 180;
                        } else if (ball.angle == 45 && game.walls[j].wallRotation == 1) {
                            ball.angle = 315;
                        }else if (ball.angle == 45 && game.walls[j].wallRotation == 2) {
                            ball.angle = 135;
                        }else if (ball.angle == 90) {
                            ball.angle = 270;
                        }else if (ball.angle == 135 && game.walls[j].wallRotation == 1) {
                            ball.angle = 225;
                        }else if (ball.angle == 135 && game.walls[j].wallRotation == 2) {
                            ball.angle = 45;
                        }else if (ball.angle == 180) {
                            ball.angle = 0;
                        }else if (ball.angle == 225 && game.walls[j].wallRotation == 1) {
                            ball.angle = 135;
                        }else if (ball.angle == 225 && game.walls[j].wallRotation == 2) {
                            ball.angle = 315;
                        }else if (ball.angle == 270) {
                            ball.angle = 90;
                        }else if (ball.angle == 315 && game.walls[j].wallRotation == 1) {
                            ball.angle = 45;
                        }else if (ball.angle == 315 && game.walls[j].wallRotation == 2) {
                            ball.angle = 225;
                        }
                    }
                }
            }
        }

        for(int i = 0; i < game.players.length; i++){
            game.players[i].balls.removeAll(Player.deadBalls);
        }

        for (int i = 0; i < game.powerUps.size(); i++) {
            game.powerUps.get(i).draw(game.batch);
        }

        for (int i = 0; i < game.players.length; i++) {
            for (int j = 0; j < game.powerUps.size(); j++) {
                if(game.players[i].hitbox.collidesWith(game.powerUps.get(j).getHitbox())){
                    if(!game.players[i].hasSpeed || game.powerUps.get(j).type != 0) {
                        PowerUp.powerUp(game.players[i], game.powerUps.get(j).type);
                        game.deadPowerUps.add(game.powerUps.get(j));
                    }
                }
            }
        }

        for (int i = 0; i < game.powerUps.size(); i++) {
            if(game.powerUps.get(i).isLifeSpanOverPU()){
                game.deadPowerUps.add(game.powerUps.get(i));
            }
        }

        game.powerUps.removeAll(game.deadPowerUps);


        for(int i = 0; i < game.players.length; i++){
            if(game.players[i].canDispose){
                game.players[i].stillPortals.removeAll(Player.deadStill);
                game.players[i].canDispose = false;
            }
        }

        for(int i = 0; i < game.players.length; i++){
            game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
            for (Ball ball: game.players[i].balls) {
                ball.draw(game.batch);
            }
        }

        for(int i = 0; i < game.players.length; i++){
                game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
            for (Portal portal:  game.players[i].portals) {
                portal.draw(game.batch);
            }
        }

        for(int i = 0; i < game.players.length; i++){
            game.batch.draw(game.players[i].texture, game.players[i].x, game.players[i].y);
            for (StillPortal still:  game.players[i].stillPortals) {
                still.draw(game.batch);
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
        int[][] map = g.returnMap();
        int WunitSize = 3;
        game.walls = MapRender.walls;
        for (int[] ints : map) {
            int Wx = ints[0];
            int Wy = ints[1];
            int Wheight = ints[2];
            int Wwidth = ints[3];
            int a = Wy;
            int b = Wx;
            while (b < Wx + Wwidth) {
                while (a < Wy + Wheight) {
                    game.batch.draw(wallUnit, b, a);
                    a += WunitSize;
                }
                b += WunitSize;
                a = Wy;
            }
        }

        for(int i = 0; i < game.players.length; i++){
            for (Ball ball: game.players[i].balls) {
                ball.update(Gdx.graphics.getDeltaTime());
            }
        }

        for(int i = 0; i < game.players.length; i++){

            for (Portal portal: game.players[i].portals) {
                portal.update(Gdx.graphics.getDeltaTime());
            }
        }

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