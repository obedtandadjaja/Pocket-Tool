package com.example.multifunctional;

import com.example.multifunctional.R;

import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.ViewFlipper;

public class Ruler extends Fragment{

	boolean bool;
	private ViewFlipper vf;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(true);
	    bool = false;
	    
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
            View rootView = inflater.inflate(R.layout.flipper, container, false);
            vf = (ViewFlipper) rootView.findViewById(R.id.vf);
            
            vf.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View v) {
					// TODO Auto-generated method stub
					if(bool)
					{
						vf.setDisplayedChild(0);
						bool = false;
					}
					else
					{
						vf.setDisplayedChild(1);
						bool = true;
					}
				}
            });
            return rootView;
    }
}
