package com.mygdx.portaldodgeball.Entities;

public class Hitbox {
    float x, y;
    int width, height;


    public Hitbox(float x, float y, int width, int height){
        this.x = x;
        this.y = y;
        this.width = width;
        this.height = height;

    }

    public void move(float x, float y){
        this.x = x;
        this.y = y;
    }

    public boolean collidesWidth(Hitbox hitbox){
        return this.x < hitbox.x + hitbox.width && this.y < hitbox.y + hitbox.height && this.x + width > hitbox.x && this.y + height > hitbox.y;
    }
}
