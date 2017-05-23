package com.example.developer.parallax;

/**
 * Created by developer on 24/04/17.
 */

import android.content.DialogInterface;
import android.content.res.AssetManager;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.util.Log;
import android.view.View;

public class EnemyGameObject extends AnimatedImageGameObject {


    Matrix matrix = new Matrix();
    public float  angle = 0;
    public float velocity = 100;
    public float xInicial;


    public EnemyGameObject(String file, AssetManager assetManager){
        loadImages(file,assetManager,11,1);
        timeNextFrame = 700;
    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);


        x += Math.cos(angle) * deltaTime * velocity/100000;
        y += Math.sin(angle) * deltaTime * velocity/100000;

        x -=20;
        if(x <= 0){
            x = (int)xInicial;
        }



    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        matrix.reset();
        matrix.preTranslate(x-width/2,y-height/2);
        matrix.preRotate((float)angle*180.0f/(float)Math.PI,width/2,height/2);
        canvas.drawBitmap(anim[currentFrame],matrix,paint);
    }

}



