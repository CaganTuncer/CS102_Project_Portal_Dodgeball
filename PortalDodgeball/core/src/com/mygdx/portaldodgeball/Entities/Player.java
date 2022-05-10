package com.mygdx.portaldodgeball.Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.mygdx.portaldodgeball.Entities.map.Wall;
import com.mygdx.portaldodgeball.PortalDodgeball;
import java.util.ArrayList;



// Player class to initialize the player. Also contains related variables and methods
public class Player extends Entity {
    public float timeSinceInput;
    public int score, ballCount, powUp, number, absMove;
    public static int limit = 5, id = 0;
    //border of the screen constraining the player movement
    public int[] limits;
    public boolean isHit, isProtected, canIncreaseX, canIncreaseY, canDecreaseX, canDecreaseY;
    public String name;
    //int array to keep the numeric representation of the key pressed, for further use on player movement
    public int[] keys;
    public ArrayList<Ball> balls = new ArrayList<Ball>();
    public ArrayList<Portal> portals = new ArrayList<Portal>();
    public static ArrayList<Ball> deadBalls = new ArrayList<Ball>();
    public static ArrayList<Portal> thrownPortals = new ArrayList<Portal>();
    public ArrayList<StillPortal> stillPortals = new ArrayList<StillPortal>();
    public ArrayList<Ball> transports = new ArrayList<Ball>();
    public static ArrayList<StillPortal > deadStill = new ArrayList<StillPortal>();
    ArrayList<Integer> indexes  = new ArrayList<>();
    public Hitbox hitbox, up, right, left, down;
    public PortalDodgeball game;
    public int portalLimit = -1;
    public boolean canDispose = false;
    public boolean indictor = false;

    public Player(String name, PortalDodgeball game) {
        super();

        this.number = id;

        this.game = game;

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

        switch (this.number){
            case 0:
                this.x = 180;
                this.y = 180;
                break;
            case 1:
                this.x = 100;
                this.y = 100;
                break;
            case 2:
                this.x = 240;
                this.y = 240;
        }

        this.direction = 0;
        this.absMove = 0;
        this.score = 0;
        this.ballCount = 0;
        this.powUp = 0;
        this.timeSinceInput = 0;
        this.speed = 4;
        this.hitbox = new Hitbox(this.x - 1, this.y -1 , 40, 40);
        this.up = new Hitbox(this.x, this.y + 39, 39, 1);
        this.right = new Hitbox(this.x + 39, this.y, 1, 39);
        this.down = new Hitbox(this.x, this.y - 1, 39, 1);
        this.left = new Hitbox(this.x - 1, this.y, 1, 39);

        this.limits = new int[] {0, 1600, 0, 900};

        this.isHit = false;
        this.isProtected = false;
        this.canIncreaseX = true;
        this.canIncreaseY = true;
        this.canDecreaseY = true;
        this.canDecreaseX = true;


        this.name = name;
        id++;
    }
    //general control and action methods to generate player movement
    public void move(){
        throwBall();
        portal();
        this.timeSinceInput += Gdx.graphics.getDeltaTime();
        if(timeSinceInput > 0.2f){
            if(Gdx.input.isKeyPressed(keys[3])){
                if(this.direction > 0){
                    this.direction -= 1;
                } else {
                    this.direction = 7;
                }
                this.checkOrientation();
                this.timeSinceInput = 0;
            }
            if(Gdx.input.isKeyPressed(keys[1])){
                if(this.direction < 7){
                    this.direction += 1;
                } else {
                    this.direction = 0;
                }
                this.checkOrientation();
                this.timeSinceInput = 0;
            }
        }

        if(this.direction == 0){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canIncreaseX){
                    this.x += speed;
                    this.absMove = 0;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canDecreaseX){
                    this.x -= speed;
                    this.absMove = 4;
                }
            }
        }
        if(this.direction == 1){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canIncreaseX){
                    this.x += speed - 1;
                    this.absMove = 1;
                }
                this.check();
                if(this.canIncreaseY){
                    this.y += speed - 1;
                    this.absMove = 1;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canDecreaseX){
                    this.x -= speed - 1;
                    this.absMove = 5;
                }
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed - 1;
                    this.absMove = 5;
                }
            }
        }
        if(this.direction == 2){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canIncreaseY){
                    this.y += speed;
                    this.absMove = 2;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed;
                    this.absMove = 6;
                }
            }
        }
        if(this.direction == 3){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canIncreaseY){
                    this.y += speed - 1;
                    this.absMove = 3;
                }
                if(this.canDecreaseX){
                    this.x -= speed - 1;
                    this.absMove = 3;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed - 1;
                    this.absMove = 7;
                }
                if(this.canIncreaseX){
                    this.x += speed - 1;
                    this.absMove = 7;
                }
            }
        }
        if(this.direction == 4){
            this.check();
            if(Gdx.input.isKeyPressed(keys[0])){
                if(this.canDecreaseX){
                    this.x -= speed;
                    this.absMove = 4;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canIncreaseX){
                    this.x += speed;
                    this.absMove = 0;
                }
            }
        }
        if(this.direction == 5){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed - 1;
                    this.absMove = 5;
                }
                if(this.canDecreaseX){
                    this.x -= speed - 1;
                    this.absMove = 5;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canIncreaseY){
                    this.y += speed - 1;
                    this.absMove = 1;
                }
                if(this.canIncreaseX){
                    this.x += speed - 1;
                    this.absMove = 1;
                }
            }
        }
        if(this.direction == 6){
            if(Gdx.input.isKeyPressed(keys[0])){
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed;
                    this.absMove = 6;
                }
            }
            if(Gdx.input.isKeyPressed(keys[2])){
                this.check();
                if(this.canIncreaseY){
                    this.y += speed;
                    this.absMove = 2;
                }
            }
        }
        if(this.direction == 7) {
            if (Gdx.input.isKeyPressed(keys[0])) {
                this.check();
                if(this.canDecreaseY){
                    this.y -= speed - 1;
                    this.absMove = 7;
                }
                if(this.canIncreaseX){
                    this.x += speed - 1;
                    this.absMove = 7;
                }
            }
            if (Gdx.input.isKeyPressed(keys[2])) {
                this.check();
                if(this.canIncreaseY){
                    this.y += speed - 1;
                    this.absMove = 3;
                }
                if(this.canDecreaseX){
                    this.x -= speed - 1;
                    this.absMove = 3;
                }
            }
        }
        this.hitbox.move(this.x - 1, this.y - 1);
        this.right.move(this.x + 39, this.y);
        this.left.move(this.x - 1, this.y);
        this.up.move(this.x, this.y + 39);
        this.down.move(this.x, this.y - 1);
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

    public void check(){

        for(int j = 0; j < game.walls.length; j++) {
            boolean hit = this.hitbox.collidesWidth((game.walls[j].wallHitbox));

            if(hit){
                Wall aWall = game.walls[j];
                int Rotat = aWall.wallRotation;
/*                if((this.hitbox.collidesWidth(aWall.wallEdgeHb1)||this.hitbox.collidesWidth(aWall.wallEdgeHb2)) && !this.hitbox.collidesWidth(aWall.wallHitbox)){
                    if(Rotat==1){
                        Rotat = 2;
                    }
                    else{
                        Rotat = 1;
                    }
                }
*/

                if(this.absMove == 0){
                    this.canIncreaseX = false;
                    this.x -= 4;
                } else if (this.absMove == 1){
                    if(right.collidesWidth(aWall.wallHitbox)){
                        this.x -= 4;
                        this.canIncreaseX = false;
                    } else if (up.collidesWidth(aWall.wallHitbox)){
                        this.y -= 4;
                        this.canIncreaseY = false;
                    }
                } else if (this.absMove == 2) {
                    this.canIncreaseY = false;
                    this.y -= 4;
                } else if (this.absMove == 3) {
                    if(left.collidesWidth(aWall.wallHitbox)){
                        this.x += 4;
                        this.canDecreaseX = false;
                    } else if(up.collidesWidth(aWall.wallHitbox)){
                        this.y -= 4;
                        this.canIncreaseY = false;
                    }
                } else if (this.absMove == 4) {
                    this.canDecreaseX = false;
                    this.x += 4;
                } else if (this.absMove == 5) {
                    if(down.collidesWidth(aWall.wallHitbox)){
                        this.y += 4;
                        this.canDecreaseY = false;
                    } else if(left.collidesWidth(aWall.wallHitbox)){
                        this.x += 4;
                        this.canDecreaseX = false;
                    }
                }else if(this.absMove == 6) {
                    this.canDecreaseY = false;
                    this.y += 4;
                } else if (this.absMove == 7){
                    if(down.collidesWidth(aWall.wallHitbox)){
                        this.y += 4;
                        this.canDecreaseY = false;
                    } else if(right.collidesWidth(aWall.wallHitbox)){
                        this.x -= 4;
                        this.canIncreaseX = false;
                    }
                }
            } else {
                this.canIncreaseX = true;
                this.canDecreaseX = true;
                this.canIncreaseY = true;
                this.canDecreaseY = true;
            }
        }

        for(int i = 0; i < game.players.length; i++){
            if(game.players[i] != this){
                boolean hit = this.hitbox.collidesWidth(game.players[i].hitbox);
                if(hit){
                    if(this.absMove == 0){
                        this.canIncreaseX = false;
                        this.x -= 4;
                    } else if (this.absMove == 1){
                        if(this.hitbox.collidesWidth(game.players[i].right)){
                            this.x -= 4;
                        } if (this.hitbox.collidesWidth(game.players[i].up)){
                            this.y -= 4;
                        }
                    } else if (this.absMove == 2) {
                        this.canIncreaseY = false;
                        this.y -= 4;
                    } else if (this.absMove == 3) {
                        if(this.hitbox.collidesWidth(game.players[i].left)){
                            this.x += 4;
                        } if(this.hitbox.collidesWidth(game.players[i].up)){
                            this.y -= 4;
                        }
                    } else if (this.absMove == 4) {
                        this.canDecreaseX = false;
                        this.x += 4;
                    } else if (this.absMove == 5) {
                        if(this.hitbox.collidesWidth(game.players[i].down)){
                            this.y += 4;
                        } if(this.hitbox.collidesWidth(game.players[i].left)){
                            this.x += 4;
                        }
                    }else if(this.absMove == 6) {
                        this.canDecreaseX = false;
                        this.y += 4;
                    } else if (this.absMove == 7){
                        if(this.hitbox.collidesWidth(game.players[i].down)){
                            this.y += 4;
                        } if(this.hitbox.collidesWidth(game.players[i].right)){
                            this.x -= 4;
                        }
                    }
                } else {
                    this.canIncreaseX = true;
                    this.canDecreaseX = true;
                    this.canIncreaseY = true;
                    this.canDecreaseY = true;
                }
                System.out.println(hit + " " + this.number);
            }
        }
    }
    //throw method changed with throwBall* to settle the dispute with general syntax of Java.
    /*
    public void transportBall(){
        Ball ball5 = new Ball(this, (float) Math.toRadians(180), this.x - 11,this.y + 15);
        balls.add(ball5);
    }*/

    public void throwBall(){

        if(Gdx.input.isKeyJustPressed(this.keys[4])){
            switch (direction){

                case 0:
                    Ball ball1 = new Ball(this, (float) Math.toRadians(0), this.x + 40,this.y + 15);
                    balls.add(ball1);
                    break;
                case 1:
                    Ball ball2 = new Ball(this, (float) Math.toRadians(45), this.x + 40,this.y + 40);
                    balls.add(ball2);
                    break;
                case 2:
                    Ball ball3 = new Ball(this, (float) Math.toRadians(90), this.x + 10,this.y + 40);
                    balls.add(ball3);
                    break;
                case 3:
                    Ball ball4 = new Ball(this, (float) Math.toRadians(135), this.x,this.y + 40);
                    balls.add(ball4);
                    break;
                case 4:
                    Ball ball5 = new Ball(this, (float) Math.toRadians(180), this.x - 11,this.y + 15);
                    balls.add(ball5);
                    break;
                case 5:
                    Ball ball6 = new Ball(this, (float) Math.toRadians(225), this.x - 11,this.y - 10);
                    balls.add(ball6);
                    break;
                case 6:
                    Ball ball7 = new Ball(this, (float) Math.toRadians(270), this.x + 10,this.y - 11);
                    balls.add(ball7);
                    break;
                case 7:
                    Ball ball8 = new Ball(this, (float) Math.toRadians(315), this.x + 40,this.y);
                    balls.add(ball8);
                    break;

            }
            indictor = false;
        }
    }

    public void portal(){
        if(Gdx.input.isKeyJustPressed(this.keys[5])){
            switch (direction){

                case 0:
                    Portal p1 = new Portal(this, (float) Math.toRadians(0), this.x + 40,this.y + 15);
                    portals.add(p1);
                    break;
                case 1:
                    Portal p2 = new Portal(this, (float) Math.toRadians(45), this.x + 40,this.y + 40);
                    portals.add(p2);
                    break;
                case 2:
                    Portal p3 = new Portal(this, (float) Math.toRadians(90), this.x + 10,this.y + 40);
                    portals.add(p3);
                    break;
                case 3:
                    Portal p4 = new Portal(this, (float) Math.toRadians(135), this.x,this.y + 40);
                    portals.add(p4);
                    break;
                case 4:
                    Portal p5 = new Portal(this, (float) Math.toRadians(180), this.x - 11,this.y + 15);
                    portals.add(p5);
                    break;
                case 5:
                    Portal p6 = new Portal(this, (float) Math.toRadians(225), this.x - 11,this.y - 10);
                    portals.add(p6);
                    break;
                case 6:
                    Portal p7 = new Portal(this, (float) Math.toRadians(270), this.x + 10,this.y - 11);
                    portals.add(p7);
                    break;
                case 7:
                    Portal p8 = new Portal(this, (float) Math.toRadians(315), this.x + 40,this.y);
                    portals.add(p8);
                    break;

            }
        }
    }

    public void powerUp(){}


}

