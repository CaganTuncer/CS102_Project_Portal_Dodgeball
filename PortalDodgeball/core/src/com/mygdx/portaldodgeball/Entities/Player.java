package com.mygdx.portaldodgeball.Entities;

import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.utils.Array;
import com.mygdx.portaldodgeball.PortalDodgeball;
import com.mygdx.portaldodgeball.Tools.InputManager;

import java.util.ArrayList;

// Player class to initialize the player. Also contains related variables and methods
public class Player extends Entity {
    public float timeSinceInput;
    public int score, ballCount, powUp, number;
    public static int limit = 5, id = 0;
    //border of the screen constraining the player movement
    public int[] limits;
    public boolean isHit, isProtected, canMove;
    public String name;
    //int array to keep the numeric representation of the key pressed, for further use on player movement
    public int[] keys;
    public ArrayList<Ball> balls = new ArrayList<Ball>();

    public Player(String name) {
        super();

        this.number = id;

        switch (this.number) {
            case 0:
                this.setTexture("Players/Player 1/player0.png");
                break;
            case 1:
                this.setTexture("Players/Player 2/player0.png");
                break;
            case 2:
                this.setTexture("Players/Player 3/player0.png");
                break;
        }

        //assigned key patterns for three diff. player
        keys = new int[6];
        if(number == 0){
            keys[0] = Input.Keys.W;
            keys[1] = Input.Keys.A;
            keys[2] = Input.Keys.S;
            keys[3] = Input.Keys.D;
            keys[4] = Input.Keys.Q;
            keys[5] = Input.Keys.E;
        } else if (number == 1) {
            keys[0] = Input.Keys.UP;
            keys[1] = Input.Keys.LEFT;
            keys[2] = Input.Keys.DOWN;
            keys[3] = Input.Keys.RIGHT;
            keys[4] = Input.Keys.SPACE;
            keys[5] = Input.Keys.CONTROL_LEFT;
        } else if(number == 2) {
            keys[0] = Input.Keys.NUMPAD_8;
            keys[1] = Input.Keys.NUMPAD_4;
            keys[2] = Input.Keys.NUMPAD_5;
            keys[3] = Input.Keys.NUMPAD_6;
            keys[4] = Input.Keys.NUMPAD_7;
            keys[5] = Input.Keys.NUMPAD_9;
        }
        this.x = 0;
        this.y = 0;
        this.direction = 0;
        this.score = 0;
        this.ballCount = 0;
        this.powUp = 0;
        this.timeSinceInput = 0;
        this.speed = 4;

        this.limits = new int[] {0, 1600, 0, 900};

        this.isHit = false;
        this.isProtected = false;
        this.canMove = true;

        this.name = name;

        id++;
    }
    //general control and action methods to generate player movement
    public void move(){
        throwBall();
        this.check();
        if(this.canMove){
            this.timeSinceInput += Gdx.graphics.getDeltaTime();
            if(timeSinceInput > 0.2f){
                if(Gdx.input.isKeyPressed(keys[3])){
                    if(this.direction > 0){
                        this.direction -= 1;
                        this.checkPosMinus();
                        this.checkOrientation();
                    } else {
                        this.direction = 7;
                        this.y -= 9;
                        this.checkOrientation();
                    }
                    this.timeSinceInput = 0;
                }
                if(Gdx.input.isKeyPressed(keys[1])){
                    if(this.direction < 7){
                        this.direction += 1;
                        this.checkPosPlus();
                        this.checkOrientation();
                    } else {
                        this.direction = 0;
                        this.y += 9;
                        this.checkOrientation();
                    }
                    this.timeSinceInput = 0;
                }
            }

            if(this.direction == 0){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.x += 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.x -= 4;
                }
            }
            if(this.direction == 1){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.x += 4;
                    this.y += 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.x -= 4;
                    this.y -= 4;
                }
            }
            if(this.direction == 2){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.y += 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.y -= 4;
                }
            }
            if(this.direction == 3){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.y += 4;
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.y -= 4;
                    this.x += 4;
                }
            }
            if(this.direction == 4){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.x += 4;
                }
            }
            if(this.direction == 5){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.y -= 4;
                    this.x -= 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.y += 4;
                    this.x += 4;
                }
            }
            if(this.direction == 6){
                if(Gdx.input.isKeyPressed(keys[0])){
                    this.y -= 4;
                }
                if(Gdx.input.isKeyPressed(keys[2])){
                    this.y += 4;
                }
            }
            if(this.direction == 7) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.y -= 4;
                    this.x += 4;
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.y += 4;
                    this.x -= 4;
                }
            }
        }
    }

    public void setName(String name){
        this.name = name;
    }

    public void checkOrientation(){

        switch (this.direction) {
            case 0:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player0.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player0.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player0.png");
                        break;
                }
                break;
            case 1:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player1.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player1.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player1.png");
                        break;
                }
                break;
            case 2:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player2.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player2.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player2.png");
                        break;
                }
                break;
            case 3:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player3.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player3.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player3.png");
                        break;
                }
                break;
            case 4:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player4.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player4.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player4.png");
                        break;
                }
                break;
            case 5:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player5.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player5.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player5.png");
                        break;
                }
                break;
            case 6:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player6.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player6.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player6.png");
                        break;
                }
                break;
            case 7:
                switch (this.number) {
                    case 0:
                        this.setTexture("Players/Player 1/player7.png");
                        break;
                    case 1:
                        this.setTexture("Players/Player 2/player7.png");
                        break;
                    case 2:
                        this.setTexture("Players/Player 3/player7.png");
                        break;
                }
                break;
        }
    }

    public void checkPosPlus(){
        if(this.direction == 3){
            this.x -= 9;
        } else if (this.direction == 4){
            this.x -= 6;
        } else if (this.direction == 5){
            this.x += 6;
            this.y -= 9;
        } else if (this.direction == 6){
            this.x += 9;
            this.y -= 6;
        } else if (this.direction == 7){
            this.y += 6;
        }
    }

    public void checkPosMinus(){
        if(this.direction == 6){
            this.y -= 6;
        } else if(this.direction == 5){
            this.y += 6;
            this.x -= 9;
        } else if(this.direction == 4){
            this.x -= 6;
            this.y += 9;
        } else if(this.direction == 3){
            this.x += 6;
        } else if(this.direction == 2){
            this.x += 9;
        }
    }

    public void check(){}
    //throw method changed with throwBall* to settle the dispute with general syntax of Java.
    public void throwBall(){

        if(Gdx.input.isKeyJustPressed(this.keys[4])){
            switch (direction){

                case 0:
                    balls.add(new Ball(this,0));
                    break;
                case 1:
                    balls.add(new Ball(this,45));
                    break;
                case 2:
                    balls.add(new Ball(this,90));
                    break;
                case 3:
                    balls.add(new Ball(this,135));
                    break;
                case 4:
                    balls.add(new Ball(this,180));
                    break;
                case 5:
                    balls.add(new Ball(this,225));
                    break;
                case 6:
                    balls.add(new Ball(this,270));
                    break;
                case 7:
                    balls.add(new Ball(this,315));
                    break;

            }
        }
    }

    public void portal(){}

    public void powerUp(){}


}
