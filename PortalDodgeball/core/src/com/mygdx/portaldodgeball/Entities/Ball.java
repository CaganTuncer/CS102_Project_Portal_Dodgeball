package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;

public class Ball extends Entity{

    Rectangle hitbox;
    public float angle,time;
    public int SIDE_SPEED = 500;
    public int CROSS_SPEED = 1;
    public Player player;
    public Timer timer;
    public float fireDelay;
    public Texture texture;

    public Ball(Player player,float angle){
        this.player = player;
        this.x = player.x;
        this.y = player.y;
        hitbox = new Rectangle(x,y,10,10);
        this.angle = angle;
        texture = new Texture("Players/Player 1/player3.png");


        this.timer = new Timer();

    }

    public void update(float delta){
        hitbox.x += (SIDE_SPEED * (float)Math.cos(angle) * delta);
        hitbox.y += (SIDE_SPEED * (float)Math.sin(angle) * delta);
        time -= delta;
    }

    public boolean isDespawned(){
        if(time<0){
            return true;
        }else {return false;}
    }

    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,hitbox.x,hitbox.y,10,10);
    }


}
