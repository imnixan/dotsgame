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
    private Context context;
    private MainActivity mainActivity;
    private Dots[][] dotField;
    private int step;
    private RelativeLayout gameField;
    private Paint paint = new Paint();
    private Display display;
    private int gameFieldSize;

    Painter (Context context, MainActivity mainActivity, Display display){
        super(context);
        this.context = context;
        this.mainActivity = mainActivity;
        this.display = display;
        paint.setStrokeWidth(inDp(2));

    }

    @Override
    protected void onDraw(Canvas canvas){
        gameFieldSize = display.getHeight() - inDp(50);
        step = gameFieldSize / 14;
        fillScreen(canvas);
        drawDots(step);
        drawField(canvas);

    }

    private void fillScreen(Canvas canvas){
        paint.setColor(Color.WHITE);
        canvas.drawPaint(paint);
    }

    private void drawField(Canvas canvas){
        int lineStepY = (dotField[0][0].y - dotField[0][1].y)/5;
        paint.setColor(Color.BLUE);
        for (int i = 0; i < 15; i++) {
            canvas.drawLine(dotField[0][0].x - step, dotField[0][i].y - lineStepY, dotField[14][0].x + step, dotField[0][i].y - lineStepY, paint);
            canvas.drawLine(dotField[i][0].x - lineStepY, dotField[0][0].y - step , dotField[i][0].x - lineStepY, dotField[0][14].y + step, paint );
        }
    }


    private int inDp(int inPx){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, inPx, getResources().getDisplayMetrics());
    }

    public void drawDots(int step){

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
           mainActivity.addContentView(dot, dotParams);
            dotField[horizontal][0] = dot;

            for (int vertical = 1; vertical < 15; vertical++){
                int prevDotv = 0;

                try{
                    prevDotv = dotField[0][vertical-1].y;
                }catch (Exception e){
//                    prevDotv += 150;
                }

                Dots dotv  = new Dots(context, dot.x, prevDotv + step);
                dotv.setOnClickListener(mainActivity);
                mainActivity.addContentView(dotv, dotParams);
                dotField[horizontal][vertical] = dotv;
            }


        }

    }







}

