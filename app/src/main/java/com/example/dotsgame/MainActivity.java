package com.example.dotsgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.Display;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.RelativeLayout;

public class MainActivity extends AppCompatActivity  implements View.OnClickListener {
    //red = true;
    //blue = false;
    boolean currentPlayer;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Display display = getWindowManager().getDefaultDisplay();
        int gameFieldSize = display.getHeight() - inDp(50);

        RelativeLayout gameField = new RelativeLayout(this);
        RelativeLayout.LayoutParams gameFieldParams = new RelativeLayout.LayoutParams(gameFieldSize, gameFieldSize);
        gameFieldParams.setMargins(inDp(10), inDp(10), inDp(10), inDp(10));
        gameField.setBackgroundColor(Color.GREEN);
        this.addContentView(gameField, gameFieldParams);

        int step = gameFieldSize / 15;
        RelativeLayout.LayoutParams dotParams = new RelativeLayout.LayoutParams(inDp(10), inDp(10));

        Dots[][] dotField = new Dots[15][15];

        for (int horizontal = 0; horizontal < 15; horizontal++){
            int prevDot = 0;
            try{
                prevDot = dotField[horizontal-1][0].x;
            }catch (Exception e){
                prevDot = -step/2;
            }


            Dots dot  = new Dots(this, prevDot+step, step/2);
            dot.setOnClickListener(this);
            gameField.addView(dot, dotParams);
            dotField[horizontal][0] = dot;

            for (int vertical = 1; vertical < 15; vertical++){
                int prevDotv = 0;

                try{
                    prevDotv = dotField[0][vertical-1].y;
                }catch (Exception e){}

                Dots dotv  = new Dots(this, dot.x, prevDotv + step);
                dotv.setOnClickListener(this);
                gameField.addView(dotv, dotParams);
                dotField[horizontal][vertical] = dotv;
            }


        }





    }

    public int inDp(int inPx){
        return (int) TypedValue.applyDimension(
                TypedValue.COMPLEX_UNIT_DIP, inPx, getResources().getDisplayMetrics());
    }

        @Override
        public void onClick(View v) {
           if(getCurrentPlayer()){
               v.setBackground(this.getDrawable(R.drawable.reddot));
               v.setEnabled(false);
               switchPlayer();
           }else {
               v.setBackground(this.getDrawable(R.drawable.bluedot));
               v.setEnabled(false);
               switchPlayer();
           }
        }

        public boolean getCurrentPlayer(){
        return currentPlayer;
        }

        public void switchPlayer(){
        currentPlayer=!currentPlayer;
        }
}