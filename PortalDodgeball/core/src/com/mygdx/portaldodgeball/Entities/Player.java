package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;

public class Player extends Entity{
    public float x, y, timeSinceInput;
    public int direction, score, ballCount, powUp;
    public static int limit = 5, id = 0;
    public int[] limits;
    public boolean isHit, isProtected, canMove;
    public String name;


    public Player(String internalPath, String name) {
        super(internalPath);

        this.x = 0;
        this.y = 0;
        this.direction = 0;
        this.score = 0;
        this.ballCount = 0;
        this.powUp = 0;
        this.timeSinceInput = 0;

        this.limits = new int[] {0, 1600, 0, 900};

        this.isHit = false;
        this.isProtected = false;
        this.canMove = true;

        this.name = name;

        id++;
    }

    public void move(){
        this.check();
        if(this.canMove){
            this.timeSinceInput += Gdx.graphics.getDeltaTime();
            if(timeSinceInput > 0.2f){
                if(Gdx.input.isKeyPressed(Input.Keys.D)){
                    if(this.direction > 0){
                        this.direction -= 1;
                    } else {
                        this.direction = 7;
                    }
                    this.timeSinceInput = 0;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.A)){
                    if(this.direction < 7){
                        this.direction += 1;
                    } else {
                        this.direction = 0;
                    }
                    this.timeSinceInput = 0;
                }
            }

            if(this.direction == 0){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.x += 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.x -= 4;
                }
            }
            if(this.direction == 1){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.x += 4;
                    this.y += 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.x -= 4;
                    this.y -= 4;
                }
            }
            if(this.direction == 2){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.y += 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.y -= 4;
                }
            }
            if(this.direction == 3){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.y += 4;
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.y -= 4;
                    this.x += 4;
                }
            }
            if(this.direction == 4){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.x += 4;
                }
            }
            if(this.direction == 5){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.y -= 4;
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.y += 4;
                    this.x += 4;
                }
            }
            if(this.direction == 6){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.y -= 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.y += 4;
                }
            }
            if(this.direction == 7){
                if(Gdx.input.isKeyPressed(Input.Keys.W)){
                    this.y -= 4;
                    this.x += 4;
                }
                if(Gdx.input.isKeyPressed(Input.Keys.S)){
                    this.y += 4;
                    this.x -= 4;
                }
            }
        }
    }

    public void check(){}

    public void thow(){}

    public void portal(){}

    public void powerUp(){}
}
