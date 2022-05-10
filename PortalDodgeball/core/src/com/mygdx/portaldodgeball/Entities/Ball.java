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
    private float lifeSpan = 5f;
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
        if (timeSeconds > lifeSpan) {
            timeSeconds -= lifeSpan;
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

        //this.player.stillPortals.indexOf(stillPortal)) == 0
        if(in == 1) {

        }else{

        }


        /*
        this.player.stillPortals.get(targetIndex).allowance = false;
        this.player.stillPortals.get(targetIndex).calcRotation();
        this.player.stillPortals.get(targetIndex).allowance = true;
        stillPortal.Assign();*/

    }
   /* public boolean portalCollision(int i){
        if(this.player.ball.getHitbox().collidesWidth(game.players[i].hitbox))

    }*/
}
