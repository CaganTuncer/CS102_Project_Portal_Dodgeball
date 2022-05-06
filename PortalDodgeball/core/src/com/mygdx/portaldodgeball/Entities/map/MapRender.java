package com.mygdx.portaldodgeball.Entities.map;

import com.badlogic.gdx.Game;

public class MapRender {
    public int mapChoice;
    // x , y , height , width
    public int[][] map1 = {
                            {50,50,9,1500},
                            {50,50,700,9},
                            {1541,50,700,9},
                            {50,741,9,1500},
                            {1441,50,100,9}};
    public MapRender(int aChoice){
        mapChoice = aChoice;

    }
    public int[][] returnMap(){
        return map1;
    }
}
