package com.example.multifunctional;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import com.example.multifunctional.ColorPickerDialog.OnColorChangedListener;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Typeface;
import android.graphics.PorterDuff.Mode;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.DragEvent;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;
import android.widget.Toast;

public class Sketch extends Fragment {

	DrawingView dv ;   
	private Paint mPaint;
	private DrawingView mDrawingManager=null;
	LinearLayout ll;
	Button color, eraser, diameter_up, diameter_down, clear, undo, redo;
	int strokeWidth;
	SeekBar sb;

	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(false);
	}
	
	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) 
	{
		dv = new DrawingView(getActivity());
		View rootView = inflater.inflate(R.layout.sketch, container, false);
		RelativeLayout frame = (RelativeLayout) rootView.findViewById(R.id.base);
		frame.addView(dv);
		strokeWidth = 10;
		mPaint = new Paint();
		mPaint.setAntiAlias(true);
		mPaint.setDither(true);
		mPaint.setColor(Color.BLACK);
		mPaint.setStyle(Paint.Style.STROKE);
		mPaint.setStrokeJoin(Paint.Join.ROUND);
		mPaint.setStrokeCap(Paint.Cap.ROUND);
		mPaint.setStrokeWidth(strokeWidth); 
		
		color = (Button) rootView.findViewById(R.id.button1);
		eraser = (Button) rootView.findViewById(R.id.button2);
		diameter_up = (Button) rootView.findViewById(R.id.button3);
		undo = (Button) rootView.findViewById(R.id.button4);
		redo = (Button) rootView.findViewById(R.id.button5);
		sb = (SeekBar) rootView.findViewById(R.id.seekBar1);
		ll = (LinearLayout) rootView.findViewById(R.id.linearLayout1);
		
		Typeface font = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
		color.setTypeface(font);
		eraser.setTypeface(font);
		diameter_up.setTypeface(font);
		undo.setTypeface(font);
		redo.setTypeface(font);
		
		final Dialog dialog = new ColorPickerDialog(getActivity(), new OnColorChangedListener(){
			@Override
			public void colorChanged(int c) {
				// TODO Auto-generated method stub
				dv.changeColor(c);
			}
		}, Color.rgb(0, 0, 0));
		
		dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		color.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog.show();
			}
		});
		
		diameter_up.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				ll.setVisibility(View.INVISIBLE);
				sb.setVisibility(View.VISIBLE);
			}
		});
		
		undo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dv.onClickUndo();
			}
		});
		
		redo.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				dv.onClickRedo();
			}
		});
		
		clear.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v)
			{
				dv.clear();
			}
		});
		
		sb.setOnSeekBarChangeListener(new OnSeekBarChangeListener(){
			int x = 0;
			
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress,
					boolean fromUser) {
				// TODO Auto-generated method stub
				x = progress;
			}
			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {
				// TODO Auto-generated method stub
				strokeWidth = x;
				dv.changeStrokeWidth(x);
				sb.setVisibility(View.INVISIBLE);
				ll.setVisibility(View.VISIBLE);
				Toast.makeText(getActivity(), "Stroke Width: "+x, Toast.LENGTH_SHORT).show();
			}
			
		});
		
		return rootView;
	}
	
	public class DrawingView extends View implements OnTouchListener {
	    private Canvas mCanvas;
	    private Path mPath;
	    private Paint mPaint;
	    private ArrayList<Path> paths = new ArrayList<Path>();
	    private ArrayList<Path> undonePaths = new ArrayList<Path>();
	    private Map<Path, Integer> colorsMap = new HashMap<Path, Integer>();  
	    private Map<Path, Integer> strokeWidthMap = new HashMap<Path, Integer>();
	    private int selectedColor = 0xFF000000;
	    private int selectedStrokeWidth = 6;

	    private Bitmap im;
	    
	    public void changeColor(int color)
	    {
	    	selectedColor = color;
	    }
	    public void changeStrokeWidth(int width)
	    {
	    	selectedStrokeWidth = width;
	    }
	    
	    public DrawingView(Context context) 
	    {
	        super(context);
	        setFocusable(true);
	        setFocusableInTouchMode(true);      
	        this.setOnTouchListener(this);
	        mPaint = new Paint();
	        mPaint.setAntiAlias(true);
	        mPaint.setDither(true);
	        mPaint.setColor(0xFFFFFFFF);
	        mPaint.setStyle(Paint.Style.STROKE);
	        mPaint.setStrokeJoin(Paint.Join.ROUND);
	        mPaint.setStrokeCap(Paint.Cap.ROUND);
	        mPaint.setStrokeWidth(6);
	        mCanvas = new Canvas();
	        mPath = new Path();

	        im=BitmapFactory.decodeResource(context.getResources(),R.drawable.logo2);


	    }               
	        @Override
	        protected void onSizeChanged(int w, int h, int oldw, int oldh) {
	            super.onSizeChanged(w, h, oldw, oldh);
	        }

	        @Override
	        protected void onDraw(Canvas canvas) {
	        	
	        	Paint x = new Paint();
	        	x.setColor(0xFFFFFFFF);
	        	canvas.drawRect(0, 0, 1100, 2100, x);
	        	
	            for (Path p : paths)
	            {
	                mPaint.setColor(colorsMap.get(p));
	                mPaint.setStrokeWidth(strokeWidthMap.get(p));
	                canvas.drawPath(p, mPaint);
	            }
	            mPaint.setColor(selectedColor);
	            mPaint.setStrokeWidth(selectedStrokeWidth);
	            canvas.drawPath(mPath, mPaint);
	        }

	        private float mX, mY;
	        private static final float TOUCH_TOLERANCE = 4;

	        private void touch_start(float x, float y) {
	            undonePaths.clear();
	            mPath.reset();
	            mPath.moveTo(x, y);
	            mX = x;
	            mY = y;
	        }
	        private void touch_move(float x, float y) {
	            float dx = Math.abs(x - mX);
	            float dy = Math.abs(y - mY);
	            if (dx >= TOUCH_TOLERANCE || dy >= TOUCH_TOLERANCE) {
	                mPath.quadTo(mX, mY, (x + mX)/2, (y + mY)/2);
	                mX = x;
	                mY = y;
	            }
	        }
	        private void touch_up() {
	            mPath.lineTo(mX, mY);
	            // commit the path to our offscreen
	            mCanvas.drawPath(mPath, mPaint);
	            // kill this so we don't double draw
	            paths.add(mPath);
	            colorsMap.put(mPath,selectedColor);
	            strokeWidthMap.put(mPath, selectedStrokeWidth);
	            mPath = new Path();            

	        }

	        public void onClickUndo () { 
	            if (paths.size()>0) 
	            { 
	               undonePaths.add(paths.remove(paths.size()-1));
	               invalidate();
	             }
	            else
	            {

	            }
	             //toast the user 
	        }

	        public void onClickRedo (){
	           if (undonePaths.size()>0) 
	           { 
	               paths.add(undonePaths.remove(undonePaths.size()-1));
	               invalidate();
	           } 
	           else 
	           {

	           }
	             //toast the user 
	        }

	    @Override
	    public boolean onTouch(View arg0, MotionEvent event) {
	          float x = event.getX();
	          float y = event.getY();

	          switch (event.getAction()) {
	              case MotionEvent.ACTION_DOWN:
	                  touch_start(x, y);
	                  invalidate();
	                  break;
	              case MotionEvent.ACTION_MOVE:
	                  touch_move(x, y);
	                  invalidate();
	                  break;
	              case MotionEvent.ACTION_UP:
	                  touch_up();
	                  invalidate();
	                  break;
	          }
	          return true;
	    }
	    
	    public void clear()
		{
	    	mCanvas.drawColor(Color.TRANSPARENT, Mode.CLEAR);
//	    	invalidate();
		}
	}
}
