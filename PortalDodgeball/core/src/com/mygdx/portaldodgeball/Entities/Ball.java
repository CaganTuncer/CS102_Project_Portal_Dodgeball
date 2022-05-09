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
        this.x = player.x ;
        this.y = player.y ;
        this.hitbox = new Hitbox(x, y,10,10);

        this.angle = angle;
        texture = new Texture("Players/Player 1/player3.png");

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
}
