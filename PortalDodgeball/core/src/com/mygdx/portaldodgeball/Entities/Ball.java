package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.assets.AssetManager;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.badlogic.gdx.utils.Timer;

import java.awt.*;

public class Ball extends Entity{

    Rectangle hitbox;
    public float angle,time;
    public int SIDE_SPEED = 250;
    public Player player;
    public Texture texture;

    private float timeSeconds = 0f;
    private float period = 5f;



    public Ball(final Player player, float angle){
        this.player = player;
        this.x = player.x+15;
        this.y = player.y+15;
        hitbox = new Rectangle(x,y,10,10);
        this.angle = angle;
        texture = new Texture("Players/Player 1/player3.png");

    }

    public void update(float delta){
        hitbox.x += (SIDE_SPEED * (float)Math.cos(angle) * delta);
        hitbox.y += (SIDE_SPEED * (float)Math.sin(angle) * delta);
        time -= delta;
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
