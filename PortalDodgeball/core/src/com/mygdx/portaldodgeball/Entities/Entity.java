package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.graphics.Texture;

public class Entity {

    public Texture texture;

    public Entity() {}

    public void setTexture(String internalPath){
        this.texture = new Texture(internalPath);
    }

}
