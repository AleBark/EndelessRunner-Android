package com.example.developer.parallax;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;

import java.io.InputStream;



public class AnimatedImageGameObject extends GameObject {

    Bitmap[] anim;
    int frames;
    float elapsedTime = 0;
    public int timeNextFrame = 1000;
    public int currentFrame = 0;
    public void loadImages(String file, AssetManager manager, int framesW, int framesH) {

        try {

            InputStream is = manager.open(file);
            Bitmap bitMap = BitmapFactory.decodeStream(is);
            bitMap = Bitmap.createScaledBitmap(bitMap,bitMap.getWidth()*2,bitMap.getHeight()*2,true);
            frames = framesW*framesH;
            anim = new Bitmap[frames];
            width = bitMap.getWidth()/framesW;
            height = bitMap.getHeight()/framesH;

            int indice = 0;

            for(int j = 0 ; j < framesH; j++){
                for(int i = 0; i<framesW; i++){
                    anim[indice] = Bitmap.createBitmap(bitMap,i*width,j*height,width,height);

                    indice++;
                }
            }


        }catch(Exception e){
            e.printStackTrace();
        }
    }


    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);

        elapsedTime += deltaTime;
        if(elapsedTime > timeNextFrame){
            elapsedTime = 0;
            currentFrame++;
            if(currentFrame >= frames){
                currentFrame = 0;
            }
        }

    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        canvas.drawBitmap(anim[currentFrame],x,y,paint);
    }
}
