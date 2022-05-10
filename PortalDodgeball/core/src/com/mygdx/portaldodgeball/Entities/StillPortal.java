package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.graphics.g2d.SpriteBatch;
import com.mygdx.portaldodgeball.Entities.map.MapRender;
import com.mygdx.portaldodgeball.Entities.map.Wall;

import java.util.ArrayList;

public class StillPortal {
    public int x;
    public int y;
    public final int isEntry = 0;
    public final int isExit = 1;
    public int index = 0;
    public boolean allow = false;
    public int direction;
    public int width;
    public int height;

    public static int counter = 0;
    public Player player;
    Hitbox hitbox;
    public Texture texture;
    public Wall wallHit;

    public StillPortal(final Player player, int x, int y){
        this.player = player;
        this.x = x;
        this.y = y;
        findWallHit();
        calcRotation();

        if(direction == 2 || direction == 4){
            this.hitbox = new Hitbox(this.x-11, this.y-20,width,height);
        }
        else if(direction == 1 || direction == 3){
            this.hitbox = new Hitbox(this.x-20, this.y-11,width,height);
        }
    }
    public void Alternator() {
        this.player.portalLimit++;
        this.player.indexes.add(this.player.portalLimit);
        this.player.indexes.add(player.portalLimit);
        if (this.player.portalLimit >= 2) {
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
        spriteBatch.draw(texture,hitbox.x,hitbox.y,width,height);
    }
    //    public boolean canBePlaced(){
    //}
    public void findWallHit(){
        Hitbox tester = new Hitbox(x+11,y+11,1,1);
        Hitbox tester2 = new Hitbox(x+11,y-1,1,1);
        Hitbox tester3 = new Hitbox(x-1,y+11,1,1);
        Hitbox tester4 = new Hitbox(x-1,y-1,1,1);
        for(int i = 0; i< MapRender.walls.length;i++){
            Wall testWall = MapRender.walls[i];
            if(tester.collidesWidth(testWall.wallHitbox)||tester2.collidesWidth(testWall.wallHitbox)||tester3.collidesWidth(testWall.wallHitbox)||tester4.collidesWidth(testWall.wallHitbox)){
                wallHit =testWall;
                break;
            }
        }
    }
    public void calcRotation(){
        if(wallHit.wallRotation==1){
            if(y>wallHit.wallHitbox.y){
                direction = 2;
                texture = new Texture("Portals/p1InDown.png");
                this.y = wallHit.y+29;
            }
            else{
                direction = 4;
                texture = new Texture("Portals/p1InUp.png");
                this.y = wallHit.y;
            }
            width = 39;
            height = 21;
        }
        if(wallHit.wallRotation==2){
            if(x>(wallHit.wallHitbox.x)){
                direction = 3;
                texture = new Texture("Portals/p1InLeft.png");
                this.x = wallHit.x+29;
            }
            else{
                direction = 1;
                texture = new Texture("Portals/p1InRight.png");
                this.x = wallHit.x;
            }
            width = 21;
            height = 39;
        }
    }

}
