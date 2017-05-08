package com.example.developer.parallax;

import android.content.res.AssetManager;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Matrix;
import android.graphics.Paint;

import java.io.InputStream;

/**
 * Created by developer on 10/04/17.
 */

public class ParallaxGameObject extends GameObject {
    Bitmap bitmap,reversedBitmap;
    float speed = 100;

    public void loadImage(String filename,
                          AssetManager manager,int w,int h,
                          boolean matchW,boolean matchH){
        try{
            InputStream is = manager.open(filename);
            bitmap = BitmapFactory.decodeStream(is);
            width = bitmap.getWidth();
            height = bitmap.getHeight();
            is.close();

            if(matchH){
                int newWidth = (int)(width*h/height);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        newWidth,h,true);
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                Matrix mirrorMatrix = new Matrix();
                mirrorMatrix.setScale(-1,1);
                reversedBitmap = Bitmap.createBitmap(bitmap,
                        0,0,(int)width,(int)height,
                        mirrorMatrix,true);
            }else{
                int newHeight = (int)(w*height/width);
                bitmap = Bitmap.createScaledBitmap(bitmap,
                        w,newHeight,true);
                width = bitmap.getWidth();
                height = bitmap.getHeight();
                Matrix mirrorMatrix = new Matrix();
                mirrorMatrix.setScale(-1,1);
                reversedBitmap = Bitmap.createBitmap(bitmap,
                        0,0,(int)width,(int)height,
                        mirrorMatrix,true);
            }

        }catch (Exception e){
            e.printStackTrace();
        }

    }

    @Override
    public void update(float deltaTime) {
        super.update(deltaTime);
        x -= speed*deltaTime/1000.0f;
        if(x <= (-2*width)){
            x = 0;
        }
    }

    @Override
    public void draw(Canvas canvas, Paint paint) {
        super.draw(canvas, paint);
        if(x > -width)
            canvas.drawBitmap(bitmap,x,y,paint);
        canvas.drawBitmap(reversedBitmap,x+width,y,paint);
        canvas.drawBitmap(bitmap,x+2*width,y,paint);
        if(x < -width)
            canvas.drawBitmap(reversedBitmap,x+3*width,y,paint);
    }
}
