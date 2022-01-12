package com.example.dotsgame;

import androidx.appcompat.app.AppCompatActivity;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.constraintlayout.widget.ConstraintSet;

import android.graphics.Canvas;
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
    private boolean currentPlayer;
    private int gameFieldSize;
    private RelativeLayout gameField;
    private Display display;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Painter painter = new Painter(this, this, getDisplay());
        setContentView(painter);

    }

    public Display getDisplay(){
       display = display = getWindowManager().getDefaultDisplay();
       return display;
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







        private boolean getCurrentPlayer(){
        return currentPlayer;
        }

        private void switchPlayer(){
        currentPlayer=!currentPlayer;
        }
}