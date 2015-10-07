package com.example.multifunctional;

import java.lang.reflect.Field;

import android.app.Activity;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Build;
import android.os.Bundle;
import android.text.TextUtils.TruncateAt;
import android.util.DisplayMetrics;
import android.view.View;
import android.view.View.MeasureSpec;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class TextViewMarquee extends Activity {
    private TextView tv;
    private ScrollTextView tv2;
    private RelativeLayout layout;
    private int background, text;
    private String string;
    private boolean animate;
    private String font;
    private int speed;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.sign2);
        
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
        	string = extras.getString("string");
            background = extras.getInt("background");
            text = extras.getInt("text");
            animate = extras.getBoolean("animate");
            font = extras.getString("font");
            speed = extras.getInt("speed");
        }
        
        layout = (RelativeLayout) this.findViewById(R.id.layout);
        layout.setBackgroundColor(background);
        
        if(animate)
        {
        	tv = (TextView) this.findViewById(R.id.tv);
        	tv2 = (ScrollTextView) this.findViewById(R.id.scrolltext);
        	tv2.setText(string);
        	tv2.setTextColor(text);
            tv2.startScroll();
            switch(speed)
            {
            case 0:
            	tv2.setSpeed(10000);
            	Toast.makeText(this, "Changing Speed... Please Wait", Toast.LENGTH_LONG).show();
            	break;
            case 1:
            	tv2.setSpeed(7500);
            	Toast.makeText(this, "Changing Speed... Please Wait", Toast.LENGTH_LONG).show();
            	break;
            case 2:
            	tv2.setSpeed(5000);
            	Toast.makeText(this, "Changing Speed... Please Wait", Toast.LENGTH_LONG).show();
            	break;
            case 3:
            	tv2.setSpeed(2000);
            	Toast.makeText(this, "Changing Speed... Please Wait", Toast.LENGTH_LONG).show();
            	break;
            case 4:
            	tv2.setSpeed(1000);
            	Toast.makeText(this, "Changing Speed... Please Wait", Toast.LENGTH_LONG).show();
            	break;
            default:
            	tv2.setSpeed(10000);
            	break;
            }
            if(!font.equals("OpenSans"))
    		{
    			final Typeface fontList = Typeface.createFromAsset(this.getAssets(), "fonts/"+font+".ttf");
    			tv2.setTypeface(fontList);
    		}
            tv.setVisibility(View.INVISIBLE);
        }
        else
        {
        	tv = (TextView) this.findViewById(R.id.tv);
            tv.setText(string);
            tv.setTextColor(text);
            int size = 100;
            tv.setTextSize(size);
            int widthSpec = MeasureSpec.makeMeasureSpec(1920, MeasureSpec.EXACTLY);
            int heightSpec = MeasureSpec.makeMeasureSpec(0, MeasureSpec.UNSPECIFIED);
            tv.measure(widthSpec, heightSpec);
            int height = tv.getMeasuredHeight(); //get height
            DisplayMetrics metrics = this.getResources().getDisplayMetrics();
            int h = metrics.heightPixels;
            if(!font.equals("OpenSans"))
    		{
    			final Typeface fontList = Typeface.createFromAsset(this.getAssets(), "fonts/"+font+".ttf");
    			tv.setTypeface(fontList);
    		}
            if(height > h)
            {
            	int x = height-h;
            	x /= 10;
            	size -= x;
            	tv.setTextSize(size);
            }
            Toast.makeText(getApplicationContext(), height+" "+h, Toast.LENGTH_SHORT).show();
        }
    }
    private void setMarqueeSpeed(TextView tv, float speed, boolean speedIsMultiplier) {

        try {
            Field f = tv.getClass().getDeclaredField("mMarquee");
            f.setAccessible(true);

            Object marquee = f.get(tv);
            if (marquee != null) {

                String scrollSpeedFieldName = "mScrollUnit";
                if(Build.VERSION.SDK_INT >= Build.VERSION_CODES.JELLY_BEAN)
                    scrollSpeedFieldName = "mPixelsPerSecond";

                Field mf = marquee.getClass().getDeclaredField(scrollSpeedFieldName);
                mf.setAccessible(true);

                float newSpeed = speed;
                if (speedIsMultiplier)
                    newSpeed = mf.getFloat(marquee) * speed;

                mf.setFloat(marquee, newSpeed);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}