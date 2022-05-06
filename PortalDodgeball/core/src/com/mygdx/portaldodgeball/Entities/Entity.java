package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {

    public Texture texture;
    public int x;
    public int y;
    public int width;
    public int height;
    public int speed;
    public int direction;

    public Entity() {}

    public void setTexture(String internalPath){
        this.texture = new Texture(internalPath);
    }

}
