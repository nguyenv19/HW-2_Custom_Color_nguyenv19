package com.example.victor.hw3_cannon_nguyenv19;

import android.graphics.Color;

/**
 * Created by Victor on 4/1/2017.
 */

public class Target extends CustomCircle
{
    private int color;


    public Target(int initX, int initY)
    {
        super(initX, initY, targetRadius, Color.RED);
        color = Color.RED;
        paint.setColor(color);


    }

    public void setTargetHit(boolean isHit)
    {
        if(isHit)
        {
            color = Color.GREEN;

        }
        else
        {
            color = Color.RED;
        }
        paint.setColor(color);
    }



}
