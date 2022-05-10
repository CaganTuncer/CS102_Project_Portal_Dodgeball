package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;
import com.mygdx.portaldodgeball.PortalDodgeball;
import com.mygdx.portaldodgeball.screens.MainGameScreen;

import java.util.Random;

public class PowerUp extends Entity{
    public int type;
    Hitbox PUpHitbox;
    private float timeSeconds = 0f;
    private float period = 15f;
    Texture texture ;

    public float x,y;

    public static int [][] spawnLocations;
    public int map;
    public static int[][] puLocations= {
            {100,300},{300,500},{1000,350},{700,500},{1300,200},{650,350},{425,700}
    };

    static Random random = new Random();

    MainGameScreen game;

    public PowerUp(int type, MainGameScreen game, int map, float x, float y){
        this.type = type;
        this.game = game;
        this.PUpHitbox = new Hitbox(x    ,y   ,30,30);
        this.map = map;
        this.x = x;
        this.y = y;

        switch (type){

            case 0:
                this.texture = new Texture("PowerUps/speedUp.png");
                break;
            case 1:
                this.texture = new Texture("PowerUps/shield.png");
        }

    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,x,y,30,30);
    }

    public boolean isLifeSpanOverPU() {
        timeSeconds += Gdx.graphics.getDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            return true;
        }
        return false;
    }

    public Hitbox getHitbox(){
        return this.PUpHitbox;
    }

    public static void powerUp(final Player player, int type){
        switch (type){

            case 0:
                if(!player.hasSpeed) {
                    Timer timer = new Timer();
                    player.hasSpeed = true;
                    player.speed *= 2;

                    timer.scheduleTask(new Timer.Task() {
                        @Override
                        public void run() {
                            player.speed /= 2;
                            player.hasSpeed = false;
                        }
                    },10f);
                }
                break;
            case 1:
                player.hasShield = true;


        }

    }

    public static void spawnPUs(final MainGameScreen screen){
        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                int location = random.nextInt(puLocations.length);
                int type = random.nextInt(2);
                screen.game.powerUps.add(new PowerUp(type,screen,0,puLocations[location][0],puLocations[location][1]));

            }
        }, 0f,10f);




    }

}
