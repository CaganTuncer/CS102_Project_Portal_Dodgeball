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
        int xP;
        int yP;
        int gathererPortalIndex;
        int senderPortalIndex;
        StillPortal gathererPortal;
        StillPortal senderPortal;
        double relativeangle;
        int check = 0;
        if(in == 1){
            gathererPortal =this.player.stillPortals.get(1);
            senderPortal =this.player.stillPortals.get(0);
        }
        else{
            gathererPortal =this.player.stillPortals.get(0);
            senderPortal =this.player.stillPortals.get(1);
        }
        int gathererAngle = gathererPortal.direction;
        int senderAngle = senderPortal.direction;
        int angleChange;
        double angleindegrees = Math.toDegrees(this.angle);
        int anglerounded = Math.round(this.angle);

        if(anglerounded == 45 && gathererAngle == 2){
            relativeangle = 135;
        }
        else if(anglerounded == 135 && gathererAngle == 3){
            relativeangle = 135;
        }
        else if(anglerounded == 225 && gathererAngle == 4){
            relativeangle = 135;
        }
        else if(anglerounded == 315 && gathererAngle == 1){
            relativeangle = 135;
        }
        else if((anglerounded % 90) == 0){
            relativeangle = 90;
        }
        else{
            relativeangle = 45;
        }



        if(senderAngle<gathererAngle){
            angleChange = senderAngle+4 - gathererAngle;
        }
        else{
            angleChange = senderAngle - gathererAngle;
        }

        if (relativeangle ==90){
            this.angle = (float) Math.toRadians(((senderAngle -1) * 90) + 180);
        }
        else if(relativeangle > 90){
            this.angle = (float) Math.toRadians((relativeangle - 90 + (senderAngle-1)*90) %360 );
        }
        else {
            this.angle = (float) Math.toRadians((relativeangle +90 + (senderAngle-1)*90) %360);
        }
        hitbox.x += (60 * (float)Math.cos(angle));
        hitbox.y += (60 * (float)Math.sin(angle));




        xP = senderPortal.x;
        yP = senderPortal.y;
        this.setter(xP,yP);




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
