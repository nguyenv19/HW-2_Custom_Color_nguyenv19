package com.example.victor.hw3_cannon_nguyenv19;

import android.graphics.Canvas;
import android.graphics.Color;
import android.view.MotionEvent;

import java.util.ArrayList;

/**
 * Created by Victor on 3/31/2017.
 */

public class CannonAnimator implements Animator
{
    public static final int SCREEN_WIDTH = 2048;
    public static final int SCREEN_HEIGHT = 1392;

    private int gravity = 5;
    private int controlColors = Color.rgb(210, 180, 50);
    private Cannon myCannon;
    private ArrayList<CannonBall> cannonBalls;
    private ArrayList<Target> targets;
    private CannonControls[] cannonControls = null;

    public CannonAnimator()
    {
        cannonBalls = new ArrayList<>();
        targets = new ArrayList<>();
        myCannon = new Cannon();
        initializeTargets();
        initializeCannonControls();
    }

    private void initializeTargets()
    {
        Target t1 = new Target(1500, 300);
        Target t2 = new Target(1600, 1200);
        Target t3 = new Target(1550, 775);
        targets.add(t1);
        targets.add(t2);
        targets.add(t3);
    }

    private void initializeCannonControls()
    {
        cannonControls = new CannonControls[3];
        cannonControls[0] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-405, SCREEN_WIDTH, SCREEN_HEIGHT-270, "UP", controlColors, CannonControls.cannonUp);
        cannonControls[1] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-270, SCREEN_WIDTH, SCREEN_HEIGHT-135, "FIRE", Color.RED, CannonControls.cannonFire);
        cannonControls[2] = new CannonControls(SCREEN_WIDTH-175, SCREEN_HEIGHT-135, SCREEN_WIDTH, SCREEN_HEIGHT, "DOWN", controlColors, CannonControls.cannonDown);

    }



    @Override
    public int interval()
    {
        return 30;
    }

    @Override
    public int backgroundColor()
    {
        return Color.rgb(255, 255, 255);
    }

    @Override
    public boolean doPause()
    {
        return false;
    }

    @Override
    public boolean doQuit()
    {
        return false;
    }

    @Override
    public void tick(Canvas canvas)
    {
        for (Target t : targets)
        {
            t.onDraw(canvas);
            t.ondrawTarget(canvas);
        }
        for(CannonBall cb : cannonBalls)
        {
            if(cb.getX() > SCREEN_WIDTH)
            {
                cb = null;
                cannonBalls.remove(cb);
            }
            else
            {
                adjustVectors(cb);
                cb.onDraw(canvas);
                for(Target t : targets)
                {
                    if (cb.overlapsCircle(t))
                    {
                        t.setTargetHit(true);
                    }
                }
            }
        }
        myCannon.onDraw(canvas);
        for(CannonControls c : cannonControls)
        {
            c.onDraw(canvas);
        }
    }

    @Override
    public void onTouch(MotionEvent event)
    {
        int x = (int) event.getX();
        int y = (int) event.getY();
        for (CannonControls c: cannonControls)
        {
            if (c.containsPoint(x,y))
            {
                doAction(event, c.getAction());
                break;
            }
        }
    }

    public void doAction(MotionEvent event, int action)
    {
        switch (action)
        {
            case CannonControls.cannonUp:
                myCannon.rotate(-1);
                break;

            case CannonControls.cannonFire:
                if (event.getAction() == MotionEvent.ACTION_DOWN)
                    fireCannon();
                break;

            case CannonControls.cannonDown:
                myCannon.rotate(1);
                break;
        }
    }

    public void fireCannon()
    {
        double angle = Math.toRadians(myCannon.getRotateAngle());
        double vix = Math.cos(angle) * Cannon.initVelocity;
        double viy = Math.sin(angle) * Cannon.initVelocity;
        CannonBall newBall = new CannonBall(myCannon.getcannonCenterX(), myCannon.getcannonCenterY(), (int)vix, (int)viy, 0, gravity);
        cannonBalls.add(newBall);
    }

    public void adjustVectors(CannonBall cb)
    {
        cb.checkCollision(0, SCREEN_WIDTH*2, 0, SCREEN_HEIGHT);
        cb.accelerateBall();
        cb.updatePos();
    }
}
