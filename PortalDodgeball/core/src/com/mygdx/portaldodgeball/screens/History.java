package com.mygdx.portaldodgeball.screens;

import com.badlogic.gdx.Game;
import com.badlogic.gdx.Gdx;
import com.badlogic.gdx.files.FileHandle;

import java.io.IOException;
import java.nio.CharBuffer;
import java.util.ArrayList;

public class History  {
    public ArrayList<Integer> minutes = new ArrayList<Integer>();
    public ArrayList<Integer> hours = new ArrayList<Integer>();
    public ArrayList<Integer> days = new ArrayList<Integer>();
    public ArrayList<Integer> months = new ArrayList<Integer>();
    public ArrayList<Integer> years = new ArrayList<Integer>();
    public ArrayList<Integer> highestScores = new ArrayList<Integer>();
    public ArrayList<Integer> ids = new ArrayList<Integer>();
    public ArrayList<String> winners = new ArrayList<String>();
    public FileHandle file = Gdx.files.local("data.json");

    public History(){}

    public void getHistory() throws IOException {

        for (int i = 0; i < file.length(); i++) {
            minutes.add(file.reader().read(CharBuffer.allocate(4)));
            hours.add(file.reader().read(CharBuffer.allocate(5)));
            days.add(file.reader().read(CharBuffer.allocate(6)));
            months.add(file.reader().read(CharBuffer.allocate(7)));
            years.add(file.reader().read(CharBuffer.allocate(8)));
            highestScores.add(file.reader().read(CharBuffer.allocate(2)));
            ids.add(file.reader().read(CharBuffer.allocate(0)));
            winners.add(String.valueOf(file.reader().read(CharBuffer.allocate(1))));

        }
    }

    public void printHistory(){
        for (int i = 0; i < winners.size(); i++) {
            System.out.println(minutes.get(i));
        }
    }

}
