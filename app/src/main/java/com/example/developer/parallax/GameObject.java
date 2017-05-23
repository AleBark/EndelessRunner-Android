package com.example.developer.parallax;

import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Rect;

/**
 * Created by developer on 10/04/17.
 */

public class GameObject {
    public int x=0,y=0,width=0,height=0;
    public float angle = 0;
    public String name = "";


    public int layer = 0;

    public void update(float deltaTime){

    }
    public void draw(Canvas canvas, Paint paint){

    }


    public Rect getBoundingBox(){
        Rect r = new Rect((int)(x-width/2),(int)(y-height/2),
                (int)(x+width/2),(int)(y+height/2));
        return r;
    }

}
