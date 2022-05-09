package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.portaldodgeball.PortalDodgeball;

import java.util.Random;

public class PowerUp {
    int type;
    Hitbox PUpHitbox;
    private float timeSeconds = 0f;
    private float period = 15f;
    Texture texture ;

    public float x,y;

    public static int [][] spawnLocations;
    public int map;

    Random random = new Random();

    PortalDodgeball game;

    public PowerUp(int type, PortalDodgeball game, int map){
        this.type = type;
        this.game = game;
        this.PUpHitbox = new Hitbox(0    ,0   ,30,30);
        this.texture = new Texture("PowerUps/speedUp.png");
        this.map = map;
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,x,y,10,10);
    }

    public boolean isLifeSpanOverPU() {
        timeSeconds += Gdx.graphics.getDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            return true;
        }
        return false;
    }

}
