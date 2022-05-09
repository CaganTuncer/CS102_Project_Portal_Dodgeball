package com.mygdx.portaldodgeball.Entities.map;

import com.badlogic.gdx.Game;

public class MapRender {
    public int mapChoice;
    public Wall[] walls = new Wall[20];
    // x , y , height , width
    public int[][] map1 = {
            {50,50,9,1500},
            {50,50,700,9},
            {1541,50,700,9},
            {50,741,9,1500},
            {200,50,100,9},
            {150,100,200,9},
            {100,450,9,180},
            {500,150,9,250},
            {420,350,9,180},
            {480,530,200,9},
            {450,550,141,9},
            {150,600,9,150},
            {650,600,9,150},
            {700,400,100,9},
            {950,150,200,9},
            {950,450,200,9},
            {1350,50,150,9},
            {1200,200,350,9},
            {1200,441,9,300},
            {1350,650,9,150}};
    public int[][] map2 = {
            {50,50,9,1500},
            {50,50,700,9},
            {1541,50,700,9},
            {50,741,9,1500},
            {50,550,9,500},
            {350,50,350,9},
            {650,50,450,9},
            {650,491,9,350},
            {991,550,200,9},
            {700,591,150,9},
            {950,50,250,9},
            {950,391,9,300},
            {1241,200,200,9},
            {1150,200,9,100},
            {1250,441,300,9},
            {1250,441,9,150},
            {1250,441,9,150},
            {1391,241,200,9},
            {1391,180,9,109},
            {1400,650,91,9}};
    public int[][] map3 = {
            {50,50,9,1500},
            {50,50,700,9},
            {1541,50,700,9},
            {50,741,9,1500},
            {150,50,150,9},
            {600,50,150,9},
            {450,120,9,300},
            {650,500,241,9},
            {150,200,9,300},
            {441,200,300,9},
            {150,491,250,9},
            {1150,50,150,9},
            {1191,291,350,9},
            {991,291,9,200},
            {550,300,9,300},
            {500,661,9,150},
            {941,461,200,9},
            {1241,200,9,300},
            {1391,400,9,150},
            {1391,600,9,150}};
    public MapRender(int aChoice){
        mapChoice = aChoice;
        int[][] map = map1;
        for(int i = 0; i < map.length; i++) {
            int x = map[i][0];
            int y = map[i][1];
            int height = map[i][2];
            int width = map[i][3];
            walls[i] = new Wall(x,y,width,height);
        }
    }
    public int[][] returnMap(){
        return map1;
    }
}
