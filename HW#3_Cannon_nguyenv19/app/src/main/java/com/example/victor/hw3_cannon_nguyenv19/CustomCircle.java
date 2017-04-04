package com.example.victor.hw3_cannon_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;

/**
 * Created by Victor on 4/1/2017.
 */

public abstract class CustomCircle
{
    protected Paint paint = new Paint();
    protected int color;
    protected int radius;
    protected int x;
    protected int y;
    protected static final int targetRadius = 100;

    public CustomCircle(int initX, int initY, int initRadius, int initColor)
    {
        this.x = initX;
        this.y = initY;
        this.radius = initRadius;
        this.color = initColor;
        paint.setColor(color);
    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);
    }

    public void ondrawTarget(Canvas canvas)
    {
        canvas.drawCircle(x, y, radius, paint);

        paint.setColor(Color.BLUE);
        canvas.drawCircle(x, y, targetRadius-15, paint);

        paint.setColor(Color.BLACK);
        canvas.drawCircle(x, y, targetRadius-25, paint);

        paint.setColor(Color.rgb(100, 250, 255));
        canvas.drawCircle(x, y, targetRadius-40, paint);

        paint.setColor(Color.WHITE);
        canvas.drawCircle(x, y, targetRadius-55, paint);

        paint.setColor(Color.RED);
        canvas.drawCircle(x, y, targetRadius-95, paint);


    }

    public boolean overlapsCircle(CustomCircle otherCircle)
    {
        int x1;
        int x2;
        int y1;
        int y2;

        x1 = x;
        x2 = otherCircle.getX();
        y1 = y;
        y2 = otherCircle.getY();

        int distance = (int)Math.sqrt( (x2-x1)*(x2-x1) + (y2-y1)*(y2-y1) );
        return  (distance < (radius + otherCircle.getRadius()));
    }

    public int getX()
    {
        return this.x;
    }

    public int getY()
    {
        return this.y;
    }

    public int getRadius()
    {
        return this.radius;
    }
}
