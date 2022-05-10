package com.mygdx.portaldodgeball.Entities.map;

import com.badlogic.gdx.Game;

public class MapRender {
    public int mapChoice;
    public static Wall[] walls = new Wall[5];
    // x , y , height , width
    public int[][] map1 = {
                            {50,50,700,9},
                            {1541,50,700,9},
                            {50,741,9,1500},{50,50,9,1500},
                            {1441,50,100,9}};
    public MapRender(int aChoice){
        mapChoice = aChoice;
        int[][] map = map1;
        for(int i = 0; i < map.length; i++) {
            int x = map[i][0];
            int y = map[i][1];
            int height = map[i][2];
            int width = map[i][3];
            walls[i] = new Wall( x , y ,width,height);
        }
    }
    public int[][] returnMap(){
        return map1;
    }
}
