package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

public class Portal extends Entity{
    public int x;
    public int y;
    public float angle,time;
    public int SIDE_SPEED = 250;
    public Player player;
    public Texture texture;
    public Ball projectilePortal;

    private float timeSeconds = 0f;
    private float period = 5f;
    Hitbox hitbox;


    public Portal(final Player player, float angle, int x, int y){
        this.x = x;
        this.y = y;
        this.player = player;
        this.angle = angle;

        this.hitbox = new Hitbox(x, y,10,10);
        //texture = new Texture("Players/Player 1/player3.png");
        texture = new Texture("PowerUps/shield.png");
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

}
