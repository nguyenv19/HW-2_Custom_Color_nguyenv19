package com.example.victor.hw3_cannon_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
/**
 * Created by Victor on 4/1/2017.
 */

public class CannonBall extends CustomCircle
{
    private static final int radius = 50;
    private int vx;
    private int vy;
    private int ax;
    private int ay;

    public CannonBall(int initX, int initY, int initVX, int initVY, int initAX, int initAY)
    {
        super(initX, initY, radius, Color.BLACK);
        this.vx = initVX;
        this.vy = initVY;
        this.ax = initAX;
        this.ay = initAY;
    }

    public void updatePos()
    {
        x = x + vx;
        y = y + vy;
    }

    public void accelerateBall()
    {
        vx = vx + ax;
        vy = vy + ay;
    }

    public void checkCollision(int leftBound, int rightBound, int topBound, int bottomBound)
    {
        int xLeft = x - radius;
        int xRight = x + radius;
        int yTop = y - radius;
        int yBottom = y + radius;

        if((xLeft < leftBound))// && (vx<0))
        {
            vx = -1*vx;
        }
        if((xRight > rightBound))// && (vx>0))
        {
            vx = -1*vx;
        }
        if((yTop > topBound))// && (vy<0))
        {
            vy = -1*vy;
        }
        if((yBottom < bottomBound))// && (vy>0))
        {
            vy = -1*vy;
        }
    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }
}
