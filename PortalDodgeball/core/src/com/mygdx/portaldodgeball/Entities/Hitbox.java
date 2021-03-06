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

    public boolean collidesWith(Hitbox hitbox){
        return this.x < hitbox.x + hitbox.width && this.y < hitbox.y + hitbox.height && this.x + this.width > hitbox.x && this.y + this.height > hitbox.y;
    }
}