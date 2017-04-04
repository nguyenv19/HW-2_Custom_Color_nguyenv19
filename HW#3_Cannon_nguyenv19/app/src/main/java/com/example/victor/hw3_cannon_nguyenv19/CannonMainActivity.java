package com.example.victor.hw3_cannon_nguyenv19;

import android.app.Activity;
import android.content.pm.ActivityInfo;
import android.os.Bundle;
import android.widget.LinearLayout;


/**
 * CannonMainActivity
 *
 * This is the activity for the cannon animation. It creates a AnimationCanvas
 * containing a particular Animator object
 *
 * @author Andrew Nuxoll
 * @version September 2012
 *
 */
public class CannonMainActivity extends Activity //AppCompatActivity
{

    /**
     * creates an AnimationCanvas containing a TestAnimator.
     */
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cannon_main);
        super.setRequestedOrientation(ActivityInfo.SCREEN_ORIENTATION_SENSOR_LANDSCAPE);

        // Create an animation canvas and place it in the main layout
        Animator cannonAnim = new CannonAnimator();
        AnimationCanvas myCannon = new AnimationCanvas(this, cannonAnim);
        LinearLayout mainLayout = (LinearLayout) this.findViewById(R.id.topLevel);
        mainLayout.addView(myCannon);


    }

    /**
     * This is the default behavior (empty menu)
     */
   /* @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.cannon_main, menu);
        return true;
    }*/
}
