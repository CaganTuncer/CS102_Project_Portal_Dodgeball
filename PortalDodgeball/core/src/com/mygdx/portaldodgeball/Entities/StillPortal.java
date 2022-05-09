package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;

import java.util.ArrayList;

public class StillPortal {
    public int x;
    public int y;
    public final int isEntry = 0;
    public final int isExit = 1;
    public int index = 0;
    public boolean allow = false;

    public static int counter = 0;
    public Player player;
    Hitbox hitbox;
    public Texture texture;

    public StillPortal(final Player player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
        this.hitbox = new Hitbox(x, y,10,10);
        texture = new Texture("PowerUps/speedUp.png");
    }
    public void Alternator(){
        this.player.portalLimit++;
        this.player.indexes.add(this.player.portalLimit);
        this.player.indexes.add(player.portalLimit);
        if(this.player.portalLimit >= 2){
            Selector();
        }
    }
    public void Selector(){
        /*int i = this.player.indexes.indexOf(2);
        this.player.indexes.set(i,0);
        Player.deadStill.add(this.player.stillPortals.get(i-2));
        this.player.canDispose = true;*/
        Player.deadStill.add(this.player.stillPortals.get(0));
        this.player.stillPortals.remove(0);
        this.player.canDispose = true;
    }
    public Hitbox getHitbox(){
        return this.hitbox;
    }
    public void draw(SpriteBatch spriteBatch) {
        spriteBatch.draw(texture,hitbox.x,hitbox.y,10,10);
    }

}
