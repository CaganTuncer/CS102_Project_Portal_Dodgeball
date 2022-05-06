package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.graphics.Texture;

public abstract class Entity {

    public Texture texture;
    public int x;
    public int y;
    public int speed;
    public int direction;

    public Entity() {}

    public void setTexture(String internalPath){
        this.texture = new Texture(internalPath);
    }

    public int getX() {
        return x;
    }
    public void setX(int x) {
        this.x = x;
    }
    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }
    public int getSpeed() {
        return speed;
    }
    public void setSpeed(int speed) {
        this.speed = speed;
    }
}
