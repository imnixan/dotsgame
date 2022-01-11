package com.example.dotsgame;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.CpuUsageInfo;
import android.util.DisplayMetrics;
import android.util.TypedValue;
import android.view.Display;
import android.view.View;
import android.widget.RelativeLayout;

public class Painter extends View {
    Context context;
    MainActivity mainActivity;
    private Dots[][] dotField;
    private int step;
    RelativeLayout gameField;
    private Paint paint = new Paint();
    private int gameFieldSize;

    Painter (Context context, MainActivity mainActivity, RelativeLayout gameField, int gameFieldSize){
        super(context);
        this.context = context;
        this.mainActivity = mainActivity;
        this.gameField = gameField;
        this.gameFieldSize = gameFieldSize;
    }

    @Override
    protected void onDraw(Canvas canvas){}


    private int inDp(int inPx){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, inPx, getResources().getDisplayMetrics());
    }



    public void drawDots(){

        step = gameFieldSize / 15;
        RelativeLayout.LayoutParams dotParams = new RelativeLayout.LayoutParams(inDp(10), inDp(10));
        dotField = new Dots[15][15];

        for (int horizontal = 0; horizontal < 15; horizontal++){
            int prevDot = 0;
            try{
                prevDot = dotField[horizontal-1][0].x;
            }catch (Exception e){
                prevDot = -step/2;
            }


            Dots dot  = new Dots(context, prevDot+step, step/2);
            dot.setOnClickListener(mainActivity);
            gameField.addView(dot, dotParams);
            dotField[horizontal][0] = dot;

            for (int vertical = 1; vertical < 15; vertical++){
                int prevDotv = 0;

                try{
                    prevDotv = dotField[0][vertical-1].y;
                }catch (Exception e){}

                Dots dotv  = new Dots(context, dot.x, prevDotv + step);
                dotv.setOnClickListener(mainActivity);
                gameField.addView(dotv, dotParams);
                dotField[horizontal][vertical] = dotv;
            }


        }

    }







}

