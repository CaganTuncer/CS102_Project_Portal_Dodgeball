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
    public float xChange =0;
    public int y;
    public float yChange=0;
    public float angle,time;
    public int SIDE_SPEED = 250;
    public Player player;
    public Texture texture;
    public Ball projectilePortal;

    private float timeSeconds = 0f;
    private float period = 5f;
    public Hitbox hitbox;
    public boolean isStill = false;

    public float hitX, hitY;


    public Portal(final Player player, float angle, int x, int y){
        this.x = x;
        this.y = y;
        this.player = player;
        this.angle = angle;

        this.hitbox = new Hitbox(x, y,10,10);
        switch (this.player.number){
            case 0:
                texture = new Texture("Portals/portalsThrowP1.png");
                break;
            case 1:
                texture = new Texture("Portals/portalsThrowP2.png");
                break;
            case 2:
                texture = new Texture("Portals/portalsThrowP3.png");
                break;
        }
    }

    public void update(float delta){
        hitbox.x += (SIDE_SPEED * (float)Math.cos(angle) * delta);
        hitbox.y += (SIDE_SPEED * (float)Math.sin(angle) * delta);
        xChange += (SIDE_SPEED * (float)Math.cos(angle) * delta);
        yChange += (SIDE_SPEED * (float)Math.sin(angle) * delta);
        time -= delta;
    }

    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,hitbox.x,hitbox.y,10,10);
    }

    public void setHitxAndHitY(){


    }

}
