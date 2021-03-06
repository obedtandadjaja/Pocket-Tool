package com.example.multifunctional;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import com.app.obedtandadjaja.multifunctional.R;

public class Blank extends Activity {

    /** Duration of wait **/
    private final int SPLASH_DISPLAY_LENGTH = 500;

    /** Called when the activity is first created. */
    @Override
    public void onCreate(Bundle icicle) {
        super.onCreate(icicle);
        setContentView(R.layout.blank);

        /* New Handler to start the Menu-Activity 
         * and close this Splash-Screen after some seconds.*/
        new Handler().postDelayed(new Runnable(){
            @Override
            public void run() {
                /* Create an Intent that will start the Menu-Activity. */
                Intent mainIntent = new Intent(Blank.this,MainActivity.class);
                Blank.this.startActivity(mainIntent);
                Blank.this.finish();
            }
        }, SPLASH_DISPLAY_LENGTH);
    }
}