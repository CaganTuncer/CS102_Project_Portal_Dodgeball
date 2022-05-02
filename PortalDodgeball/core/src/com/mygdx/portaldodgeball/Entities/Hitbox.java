package com.mygdx.portaldodgeball.Entities;

public class Hitbox {
    float x, y;
    int width, height;
    Entity entity;

    public Hitbox(float x, float y, int width, int height, Entity entity){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;
        this.entity = entity;
    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWidth(Hitbox hitbox){
        return x < hitbox.x + hitbox.width && y < hitbox.y + hitbox.height && x + width > hitbox.x && y + height > hitbox.y;
    }
}
