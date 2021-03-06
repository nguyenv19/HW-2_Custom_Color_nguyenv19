package com.example.victor.hw3_cannon_nguyenv19;

/*
 * Created by Victor on 3/31/2017.
 *
 * Draws the cannon
 *
 *
 *
 */

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;

public class Cannon
{
    protected Paint cannonPaint = new Paint();
    private int cannonColor;

    protected Paint cannonbasePaint = new Paint();
    private int cannonbaseColor;

    private static final int top = (CannonAnimator.SCREEN_HEIGHT/2-50);
    private static final int bottom = (CannonAnimator.SCREEN_HEIGHT/2+50);
    private static final int left = 0;
    private static final int right = 200;
    private static final int cannonpivotY = CannonAnimator.SCREEN_HEIGHT/2;

    private static final int maxAngle = 90;
    public static final int initVelocity = 95;
    private int rotateAngle;
    private RectF cannonBarrel;
    private Path cannonPath;

    public void initCannon()
    {
        cannonPath = new Path();
        rotateAngle = 0;
        cannonbaseColor = Color.rgb(210, 180, 50);
        cannonColor = Color.rgb(210, 180, 50);
        cannonPaint.setColor(cannonColor);


    }

    public Cannon()
    {
        initCannon();
        cannonBarrel = new RectF(left, top, right, bottom);
        cannonPath.addRect(cannonBarrel, Path.Direction.CW);


    }

    public void onDraw(Canvas canvas)
    {
        canvas.drawPath(cannonPath, cannonPaint);
    }
    public void rotate(float degrees)
    {
        if ((rotateAngle <= maxAngle && degrees > 0) || (rotateAngle >= -maxAngle && degrees <0 ) )
        {
            Matrix m = new Matrix();
            m.setRotate(degrees, 0, cannonpivotY);
            cannonPath.transform(m);
            rotateAngle += degrees;
        }
    }
    public int getRotateAngle()
    {
        return this.rotateAngle;
    }
    public int getcannonCenterX()
    {
        RectF temp = new RectF();
        cannonPath.computeBounds(temp, true);
        return (int)temp.centerX();
    }
    public int getcannonCenterY()
    {
        RectF temp = new RectF();
        cannonPath.computeBounds(temp, true);
        return (int)temp.centerY();
    }
}
