package com.example.developer.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;


public class GameResources {

    private static GameResources ourInstance = new GameResources();
    List<GameObject> gameObjectList = new ArrayList<>();
    public HashMap<String,String> jogadores = new HashMap<>();



    private GameResources() {
    }

    public static GameResources getInstance() {
        return ourInstance;
    }


    public List<GameObject> getGameObjectList() {
        return gameObjectList;
    }

    public void setGameObjectList(List<GameObject> gameObjectList) {
        this.gameObjectList = gameObjectList;
    }

    public void updateAndDraw(float deltaTime, Canvas canvas, Paint paint){
        for(GameObject gameObject : gameObjectList){
            gameObject.update(deltaTime);
            gameObject.draw(canvas,paint);
        }
    }
}