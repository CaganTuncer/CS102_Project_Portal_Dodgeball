package com.mygdx.portaldodgeball.Entities;


import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.Input;
import com.badlogic.gdx.graphics.Texture;
import com.badlogic.gdx.utils.Timer;
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
    public boolean hasShield;
    public boolean hasSpeed;
    public int portalLimit = -1;
    public boolean canDispose = false;
    public boolean indictor = false;

    public float THROW_INTERVAL = 5f;
    public int BALL_AMMO = 6;
    public float RELOAD = 5f;

    public float DEATH_TIME = 5f;
    Timer throwTimer;
    public boolean isDead = false;



    public Player(String name, PortalDodgeball game) {
        super();

        this.throwTimer = new Timer();
        throwTimer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                BALL_AMMO = 6;

            }
        },THROW_INTERVAL,RELOAD);

        hasShield = false;
        hasSpeed = false;

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
                this.x = 250;
                this.y = 250;
                break;

            case 1:
                this.x = 180;
                this.y = 180;
                break;

            case 2:
                this.x = 300;
                this.y = 350;
                break;
        }

        this.direction = 0;
        this.absMove = 0;
        this.score = 0;
        this.ballCount = 0;
        this.powUp = 0;
        this.timeSinceInput = 0;
        this.speed = 4;
        this.hitbox = new Hitbox(this.x - 1, this.y -1 , 40, 40);
        this.up = new Hitbox(this.x + 3, this.y + 39, 30, 1);
        this.right = new Hitbox(this.x + 39, this.y + 3, 1, 30);
        this.down = new Hitbox(this.x + 3, this.y - 1, 30, 1);
        this.left = new Hitbox(this.x - 1, this.y + 3, 1, 30);

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
        if(!isDead) {
            if (timeSinceInput > 0.2f) {
                if (Gdx.input.isKeyPressed(keys[3])) {
                    if (this.direction > 0) {
                        this.direction -= 1;
                    } else {
                        this.direction = 7;
                    }
                    this.checkOrientation();
                    this.timeSinceInput = 0;
                }
                if (Gdx.input.isKeyPressed(keys[1])) {
                    if (this.direction < 7) {
                        this.direction += 1;
                    } else {
                        this.direction = 0;
                    }
                    this.checkOrientation();
                    this.timeSinceInput = 0;
                }
            }

            if (this.direction == 0) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 0;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canDecreaseX) {
                        this.x -= speed;
                        this.absMove = 4;
                    }
                }
            }
            if (this.direction == 1) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 1;
                    }
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 1;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canDecreaseX) {
                        this.x -= speed;
                        this.absMove = 5;
                    }
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 5;
                    }
                }
            }
            if (this.direction == 2) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 2;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 6;
                    }
                }
            }
            if (this.direction == 3) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 3;
                    }
                    if (this.canDecreaseX) {
                        this.x -= speed;
                        this.absMove = 3;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 7;
                    }
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 7;
                    }
                }
            }
            if (this.direction == 4) {
                this.check();
                if (Gdx.input.isKeyPressed(keys[0])) {
                    if (this.canDecreaseX) {
                        this.x -= speed;
                        this.absMove = 4;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 0;
                    }
                }
            }
            if (this.direction == 5) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 5;
                    }
                    if (this.canDecreaseX) {
                        this.x -= speed;
                        this.absMove = 5;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 1;
                    }
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 1;
                    }
                }
            }
            if (this.direction == 6) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 6;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 2;
                    }
                }
            }
            if (this.direction == 7) {
                if (Gdx.input.isKeyPressed(keys[0])) {
                    this.check();
                    if (this.canDecreaseY) {
                        this.y -= speed;
                        this.absMove = 7;
                    }
                    if (this.canIncreaseX) {
                        this.x += speed;
                        this.absMove = 7;
                    }
                }
                if (Gdx.input.isKeyPressed(keys[2])) {
                    this.check();
                    if (this.canIncreaseY) {
                        this.y += speed;
                        this.absMove = 3;
                    }
                    if (this.canDecreaseX) {
                        this.x -= speed;
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
    }

    public void setName(String name){
        this.name = name;
    }

    public void checkOrientation(){

       if(hasShield){
           if(this.BALL_AMMO > 0){
               switch (this.direction) {
                   case 0:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeRightShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeRightShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeRightShieldOn.png");
                               break;
                       }
                       break;
                   case 1:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeRightUpperShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeRightUpperShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeRightUpperShieldOn.png");
                               break;
                       }
                       break;
                   case 2:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1BackShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2BackShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3BackShieldOn.png");
                               break;
                       }
                       break;
                   case 3:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeLeftUpperShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeLeftUpperShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeLeftUpperShieldOn.png");
                               break;
                       }
                       break;
                   case 4:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeLeftShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeLeftShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeLeftShieldOn.png");
                               break;
                       }
                       break;
                   case 5:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeLeftUnderShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeLeftUnderShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeLeftUnderShieldOn.png");
                               break;
                       }
                       break;
                   case 6:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeMiddleShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeMiddleShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeMiddleShieldOn.png");
                               break;
                       }
                       break;
                   case 7:
                       switch (this.number) {
                           case 0:
                               this.setTexture("p1Shield/p1ThrowBeforeRightUnderShieldOn.png");
                               break;
                           case 1:
                               this.setTexture("p2Shield/p2ThrowBeforeRightUnderShieldOn.png");
                               break;
                           case 2:
                               this.setTexture("p3Shield/p3ThrowBeforeRightUnderShieldOn.png");
                               break;
                       }
                       break;
               }
           }
           else{
               switch (this.direction) {
                   case 0:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRight.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRight.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRight.png");
                               break;
                       }
                       break;
                   case 1:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRightUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRightUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRightUpper.png");
                               break;
                       }
                       break;
                   case 2:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1Back.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2Back.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3Back.png");
                               break;
                       }
                       break;
                   case 3:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeftUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeftUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeftUpper.png");
                               break;
                       }
                       break;
                   case 4:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeft.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeft.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeft.png");
                               break;
                       }
                       break;
                   case 5:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeftUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeftUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeftUnder.png");
                               break;
                       }
                       break;
                   case 6:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterMiddle.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterMiddle.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterMiddle.png");
                               break;
                       }
                       break;
                   case 7:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRightUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRightUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRightUnder.png");
                               break;
                       }
                       break;
               }
           }
       } else {
           if(this.BALL_AMMO > 0){
               switch (this.direction) {
                   case 0:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeRight.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeRight.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeRight.png");
                               break;
                       }
                       break;
                   case 1:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeRightUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeRightUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeRightUpper.png");
                               break;
                       }
                       break;
                   case 2:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1Back.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2Back.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3Back.png");
                               break;
                       }
                       break;
                   case 3:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeLeftUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeLeftUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeLeftUpper.png");
                               break;
                       }
                       break;
                   case 4:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeLeft.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeLeft.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeLeft.png");
                               break;
                       }
                       break;
                   case 5:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeLeftUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeLeftUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeLeftUnder.png");
                               break;
                       }
                       break;
                   case 6:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeMiddle.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeMiddle.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeMiddle.png");
                               break;
                       }
                       break;
                   case 7:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowBeforeRightUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowBeforeRightUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowBeforeRightUnder.png");
                               break;
                       }
                       break;
               }
           }
           else{
               switch (this.direction) {
                   case 0:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRight.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRight.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRight.png");
                               break;
                       }
                       break;
                   case 1:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRightUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRightUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRightUpper.png");
                               break;
                       }
                       break;
                   case 2:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1Back.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2Back.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3Back.png");
                               break;
                       }
                       break;
                   case 3:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeftUpper.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeftUpper.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeftUpper.png");
                               break;
                       }
                       break;
                   case 4:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeft.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeft.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeft.png");
                               break;
                       }
                       break;
                   case 5:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterLeftUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterLeftUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterLeftUnder.png");
                               break;
                       }
                       break;
                   case 6:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterMiddle.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterMiddle.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterMiddle.png");
                               break;
                       }
                       break;
                   case 7:
                       switch (this.number) {
                           case 0:
                               this.setTexture("Players/Player1edited/p1ThrowAfterRightUnder.png");
                               break;
                           case 1:
                               this.setTexture("Players/Player2edited/p2ThrowAfterRightUnder.png");
                               break;
                           case 2:
                               this.setTexture("Players/Player3edited/p3ThrowAfterRightUnder.png");
                               break;
                       }
                       break;
               }
           }
       }
    }



    public void check(){

        for(int j = 0; j < game.walls.length; j++) {
            boolean hit = this.hitbox.collidesWith((game.walls[j].wallHitbox));
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
                    this.x -= speed;
                } else if (this.absMove == 1){
                    if(game.walls[j].wallRotation == 2){
                        this.x -= speed + 5;
                    } if (game.walls[j].wallRotation == 1){
                        this.y -= speed + 5;
                    }
                } else if (this.absMove == 2) {
                    this.canIncreaseY = false;
                    this.y -= speed;
                } else if (this.absMove == 3) {
                    if(game.walls[j].wallRotation == 2){
                        this.x += speed;
                    } if(game.walls[j].wallRotation == 1){
                        this.y -= speed;
                    }
                } else if (this.absMove == 4) {
                    this.canDecreaseX = false;
                    this.x += speed;
                } else if (this.absMove == 5) {
                    if(game.walls[j].wallRotation == 1){
                        this.y += speed;
                    } if(game.walls[j].wallRotation == 2){
                        this.x += speed;
                    }
                }else if(this.absMove == 6) {
                    this.canDecreaseX = false;
                    this.y += speed;
                } else if (this.absMove == 7){
                    if(game.walls[j].wallRotation == 1){
                        this.y += speed;
                    } if(game.walls[j].wallRotation == 2){
                        this.x -= speed;
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
                boolean hit = this.hitbox.collidesWith(game.players[i].hitbox);
                if(hit){
                    if(this.absMove == 0){
                        this.canIncreaseX = false;
                        this.x -= speed;
                    } else if (this.absMove == 1){
                        if(this.hitbox.collidesWith(game.players[i].left)){
                            this.x -= speed;
                        } if (this.hitbox.collidesWith(game.players[i].down)){
                            this.y -= speed;
                        }
                    } else if (this.absMove == 2) {
                        this.canIncreaseY = false;
                        this.y -= speed;
                    } else if (this.absMove == 3) {
                        if(this.hitbox.collidesWith(game.players[i].right)){
                            this.x += speed;
                        } if(this.hitbox.collidesWith(game.players[i].down)){
                            this.y -= speed;
                        }
                    } else if (this.absMove == 4) {
                        this.canDecreaseX = false;
                        this.x += speed;
                    } else if (this.absMove == 5) {
                        if(this.hitbox.collidesWith(game.players[i].up)){
                            this.y += speed;
                        } if(this.hitbox.collidesWith(game.players[i].right)){
                            this.x += speed;
                        }
                    }else if(this.absMove == 6) {
                        this.canDecreaseX = false;
                        this.y += speed;
                    } else if (this.absMove == 7){
                        if(this.hitbox.collidesWith(game.players[i].up)){
                            this.y += speed;
                        } if(this.hitbox.collidesWith(game.players[i].left)){
                            this.x -= speed;
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
        if(BALL_AMMO > 0) {
            if (Gdx.input.isKeyJustPressed(this.keys[4])) {
                switch (direction) {

                    case 0:
                        Ball ball1 = new Ball(this, 0, this.x, this.y);
                        balls.add(ball1);
                        break;
                    case 1:
                        Ball ball2 = new Ball(this, 45, this.x , this.y );
                        balls.add(ball2);
                        break;
                    case 2:
                        Ball ball3 = new Ball(this, 90, this.x , this.y );
                        balls.add(ball3);
                        break;
                    case 3:
                        Ball ball4 = new Ball(this, 135, this.x, this.y );
                        balls.add(ball4);
                        break;
                    case 4:
                        Ball ball5 = new Ball(this, 180, this.x , this.y );
                        balls.add(ball5);
                        break;
                    case 5:
                        Ball ball6 = new Ball(this, 225, this.x , this.y );
                        balls.add(ball6);
                        break;
                    case 6:
                        Ball ball7 = new Ball(this, 270, this.x , this.y );
                        balls.add(ball7);
                        break;
                    case 7:
                        Ball ball8 = new Ball(this, 315, this.x , this.y);
                        balls.add(ball8);
                        break;
                }
                BALL_AMMO --;
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

    public void die(){
        isDead = true;
        final int tempHBWidth = this.hitbox.width;
        final int tempHBHeight = this.hitbox.height;
        final Texture tempTexture = this.texture;
        final int tempSpeed = this.speed;

        //x = 62;
        //y = 818;

        x = 4000;
        y = 4000;
        this.hitbox.x = 4000;
        this.hitbox.y = 4000;


        //this.hitbox.width = 0;
        //this.hitbox.height = 0;
        //this.texture = new Texture("Players/death.jpg");


        //this.speed = 0;

        Timer timer = new Timer();
        timer.scheduleTask(new Timer.Task() {
            @Override
            public void run() {
                isDead = false;
                spawn();

                //hitbox.width = tempHBWidth;
                //hitbox.height = tempHBHeight;

                texture = tempTexture;
                speed = tempSpeed;
            }

        }, DEATH_TIME);
    }

    public void spawn(){
        this.x = 100;
        this.y = 500;
    }

}

