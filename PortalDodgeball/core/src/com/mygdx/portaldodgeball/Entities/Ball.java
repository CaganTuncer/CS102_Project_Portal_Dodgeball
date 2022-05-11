package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Ball extends Entity{

    public float angle,time;
    public int ballSpeed = 250;
    public Player player;
    public Texture texture;

    private float timeSeconds = 0f;
    private float period = 5f;
    Hitbox hitbox;


    public Ball(final Player player, float angle, int x, int y){
        this.player = player;
        this.x = player.x + 20 ;
        this.y = player.y + 20;
        this.hitbox = new Hitbox(this.x, this.y,10,10);

        this.angle = angle;
        texture = player.texture;

    }
    public void update(float delta){

        hitbox.x += (ballSpeed * (float)Math.cos(Math.toRadians(angle)) * delta);
        hitbox.y += (ballSpeed * (float)Math.sin(Math.toRadians(angle)) * delta);
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
        int xP;
        int yP;
        StillPortal senderPortal;
        if(in == 1){
            senderPortal = this.player.stillPortals.get(0);
        }
        else{
            senderPortal =this.player.stillPortals.get(1);
        }
        int senderAngle = senderPortal.direction;

        this.angle = (((senderAngle -1) * 90) + 180)%360;
        xP = senderPortal.x;
        yP = senderPortal.y;
        this.setter(xP,yP);
        hitbox.x += (40 * (float)Math.cos(Math.toRadians(angle)));
        hitbox.y += (40 * (float)Math.sin(Math.toRadians(angle)));


    }
}
