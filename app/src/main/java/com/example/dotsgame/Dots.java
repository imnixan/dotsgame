package com.example.dotsgame;

import android.content.Context;

public class Dots extends androidx.appcompat.widget.AppCompatButton {
   Context context;

   boolean isrounded = false;
    //red = true = 1
    //blue = false = 2;
  public int player;
   public int y;
   public int x;
    public Dots(Context context, int horizontal, int vertical) {
        super(context);
        this.context = context;
        this.y = vertical;
        this.x = horizontal;
        createDot();
    }

    private void createDot(){
        setY(y);
        setX(x);
        setBackground(context.getDrawable(R.drawable.dot));
    }

}
