package com.example.developer.parallax;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.Log;
import android.view.GestureDetector;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Chronometer;

import static android.view.MotionEvent.ACTION_DOWN;
import static android.view.MotionEvent.ACTION_UP;

/**
 * Created by developer on 10/04/17.
 */

public class RenderView extends View {
    Paint paint = new Paint();
    float startTime = 0.0f;
    int r = 200,g=200,b=200;

    ParallaxGameObject parallaxGameObject,chaoGameObject;
    MainCharacterObject player, obstaculo;
    GestureDetector gestureDetector;
    Chronometer chronometer;
    TextGameObject textGameObject;

    private boolean pulando = false;
    private float chao;

    public RenderView(Context context) {
        super(context);
        startTime = System.nanoTime();

        gestureDetector = new GestureDetector(context,new GestureListener());
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        if(parallaxGameObject != null)
            return;

        parallaxGameObject = new ParallaxGameObject();
        parallaxGameObject.
                loadImage("introBackground.png",
                        getContext().getAssets(),
                        getWidth(),getHeight(),
                        false,true);
        parallaxGameObject.layer = 1;
        GameResources.getInstance().getGameObjectList().add(parallaxGameObject);

        chaoGameObject = new ParallaxGameObject();
        chaoGameObject.
                loadImage("chao.png",
                        getContext().getAssets(),
                        (int)parallaxGameObject.width,
                        getHeight(),
                        true,false);
        chaoGameObject.layer = 2;
        chaoGameObject.y = getHeight()-chaoGameObject.height;
        chaoGameObject.speed = 100;
        GameResources.getInstance().getGameObjectList().add(chaoGameObject);

        player = new MainCharacterObject("runner.png",getContext().getAssets());
        player.x = 300;
        player.y = 610;
        GameResources.getInstance().getGameObjectList().add(player);

        obstaculo = new MainCharacterObject("runner.png",getContext().getAssets());
        obstaculo.x = 600;
        obstaculo.y = 610;
        GameResources.getInstance().getGameObjectList().add(obstaculo);

        textGameObject = new TextGameObject();
        textGameObject.text = "0 metros";
        textGameObject.color = Color.WHITE;
        textGameObject.x = 1450;
        textGameObject.y = 50;
        GameResources.getInstance().getGameObjectList().add(textGameObject);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
        float deltaTime = (System.nanoTime()-startTime)/
                100000.0f;
        startTime = System.nanoTime();

        canvas.drawRGB(200,200,200);
        GameResources.
                getInstance().
                updateAndDraw(deltaTime,canvas,paint);

        invalidate();

    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        return gestureDetector.onTouchEvent(event);
    }


    public final class GestureListener extends GestureDetector.SimpleOnGestureListener {

        @Override
        public boolean onDown(MotionEvent event) {
            if(event.getAction() == ACTION_DOWN ){
                if(!player.jump && player.y == 610){
                    player.jump = true;
                }

                return  false;

            }
            if(event.getAction() == ACTION_UP){
                player.y = player.y + 50;
            }
            return true;
        }


        @Override
        public boolean onSingleTapConfirmed(MotionEvent e) {
            boolean result = false;

            return result;

        }

        @Override
        public void onLongPress(MotionEvent e) {
            super.onLongPress(e);
        }

        @Override
        public boolean onFling(MotionEvent e1, MotionEvent e2, float velocityX, float velocityY) {
//            boolean result = false;

//            float diffX = e2.getX() - e1.getX();
//            float diffy = e2.getY() - e1.getY();
//            if (Math.abs(diffX) > Math.abs(diffy)) {
//                if(Math.abs(diffX) > 100 && Math.abs(velocityX)>100){
//                    if(diffX > 0){
//                        //Swipe Right
//                        player.angle = 0.0f;
//
//                    }else {
//                        //Swipe left
//                        player.angle = (float)Math.PI ;
//                    }
//                    result = true;
//                }
//            } else {
//
//            }

//            return result;
            return false;
        }
    }

}


