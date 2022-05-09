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
    public Hitbox wallEdgeHb1;
    public Hitbox wallEdgeHb2;
    public int WunitSize = 3;
    // 1 if long in x direction 2 if long in y direction
    public int wallRotation;
    Texture wallUnit = new Texture("Gameplay sprites/wall unit piece.png");
    public Wall(int Wx,int Wy, int Wwidth, int Wheight){

        wallHitbox = new Hitbox(Wx-1,Wy-1,Wwidth+2,Wheight+2);
        if(Wwidth > Wheight){
            wallRotation = 1;
            wallEdgeHb1 = new Hitbox(Wx-4,Wy+2,4,4);
            wallEdgeHb2 = new Hitbox(Wx + width, Wy+2, 4,4 );
        }
        else{
            wallRotation = 2;
            wallEdgeHb1 = new Hitbox(Wx+2,Wy-4, 4,4);
            wallEdgeHb1 = new Hitbox(Wx+2,Wy+height, 4,4);
        }



    }


}
