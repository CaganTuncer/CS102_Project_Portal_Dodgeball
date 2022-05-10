package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;

public class Ball extends Entity{

    public float angle,time;
    public int SIDE_SPEED = 250;
    public Player player;
    public Texture texture;

    private float timeSeconds = 0f;
    private float period = 5f;
    Hitbox hitbox;

    public Ball(final Player player, float angle, int x, int y){
        this.player = player;
        this.x = player.x ;
        this.y = player.y ;
        this.hitbox = new Hitbox(x, y,10,10);

        this.angle = angle;
        texture = new Texture("Players/Player 1/player3.png");
    }
    public void update(float delta){
        hitbox.x += (SIDE_SPEED * (float)Math.cos(angle) * delta);
        hitbox.y += (SIDE_SPEED * (float)Math.sin(angle) * delta);
        time -= delta;
    }
    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,hitbox.x,hitbox.y,10,10);
    }

    public boolean isLifeSpanOver() {
        timeSeconds += Gdx.graphics.getDeltaTime();
        if (timeSeconds > period) {
            timeSeconds -= period;
            return true;
        }
        return false;
    }
    public void setter(int x, int y){
        this.x = x;
        this.y = y;
        this.hitbox.x = x;
        this.hitbox.y = y;
    }
    public void transport(int in){
        float change = 0;
        float xDiff = 0;
        float yDiff = 0;
        int xP;
        int yP;
        if(in == 1) {
            xP = this.player.stillPortals.get(0).x;
            yP = this.player.stillPortals.get(0).y;
            switch(this.player.stillPortals.get(0).direction){
                case 1:
                    this.setter(xP - 50 ,yP);
                case 2:
                    this.setter(xP, yP - 50);
                case 3:
                    this.setter(xP + 50, yP );
                case 4:
                    this.setter(xP,  yP + 50);
            }

            if(this.player.stillPortals.get(0).rotation == 0){
                change = (float)Math.abs(Math.toRadians(this.angle - (float) 90));
            }
            else{
                change = (float)Math.abs(Math.toRadians(this.angle - (float) 180));
            }
        xDiff = this.player.stillPortals.get(0).x - this.player.stillPortals.get(1).x;
            yDiff = this.player.stillPortals.get(0).y - this.player.stillPortals.get(1).y;
            if(xDiff > 0 && yDiff > 0){
                this.angle =  this.player.stillPortals.get(0).normal + (-2) * change;
            }
            else if(xDiff < 0 && yDiff > 0){
                this.angle = this.player.stillPortals.get(0).normal - (-2) * change;
            }
            else if(xDiff < 0 && yDiff < 0 ){
                this.angle =  this.player.stillPortals.get(0).normal + (-2) * change;
            }
            else if (xDiff > 0 && yDiff < 0){
                this.angle = this.player.stillPortals.get(0).normal - (-2) * change;
            }

        }else{
            xP = this.player.stillPortals.get(1).x;
            yP = this.player.stillPortals.get(1).y;
            switch(this.player.stillPortals.get(1).direction){
                case 1:
                    this.setter(xP - 50 ,yP);
                case 2:
                    this.setter(xP, yP - 50);
                case 3:
                    this.setter(xP + 50, yP );
                case 4:
                    this.setter(xP,  yP + 50);
            }

            if(this.player.stillPortals.get(1).rotation == 0){
                change = (float)(Math.abs(Math.toRadians(this.angle - (float) 90)));
            }
            else{
                change = (float)(Math.abs(Math.toRadians(this.angle - (float) 180)));
            }
            xDiff = this.player.stillPortals.get(1).x - this.player.stillPortals.get(0).x;
            yDiff = this.player.stillPortals.get(1).y - this.player.stillPortals.get(0).y;
            if(xDiff > 0 && yDiff > 0){
                this.angle = this.player.stillPortals.get(1).normal + (-2) * change;
            }
            else if(xDiff < 0 && yDiff > 0){
                this.angle = this.player.stillPortals.get(1).normal - (-2) * change;
            }
            else if(xDiff < 0 && yDiff < 0 ){
                this.angle =  this.player.stillPortals.get(1).normal + (-2) * change;
            }
            else if (xDiff > 0 && yDiff < 0){
                this.angle = this.player.stillPortals.get(1).normal - (-2) * change;
            }

        }

    }
}
