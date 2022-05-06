package com.mygdx.portaldodgeball.Entities.map;


import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.graphics.GL20;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.ScreenUtils;
import com.mygdx.portaldodgeball.Entities.Entity;
import com.mygdx.portaldodgeball.Entities.Hitbox;
import com.mygdx.portaldodgeball.PortalDodgeball;

public class Wall extends Entity {

    PortalDodgeball game;
    public Hitbox wallHitbox;
    public int WunitSize = 3;
    // 1 if long in x direction 2 if long in y direction
    public int wallRotation;
    Texture wallUnit = new Texture("Gameplay sprites/wall unit piece.png");
    public Wall(int Wx,int Wy, int Wwidth, int Wheight, Game agame){

        wallHitbox = new Hitbox(x,y,width,height,this);
        if(Wwidth > Wheight){
            wallRotation = 1;
        }
        else{
            wallRotation = 2;
        }



    }

}
