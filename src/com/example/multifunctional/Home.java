package com.example.multifunctional;

import java.util.ArrayList;

import com.app.obedtandadjaja.multifunctional.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.GridView;

public class Home extends Fragment{

	GridView grid;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(false);
	    
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View rootView = inflater.inflate(R.layout.home, container, false);
		grid = (GridView) rootView.findViewById(R.id.gridView);
		
		ArrayList<String> array = new ArrayList<String>();
		array.add(getString(R.string.Ruler));
		array.add(getString(R.string.TipCalculator));
		array.add(getString(R.string.UnitConverter));
		array.add(getString(R.string.List));
		array.add(getString(R.string.Flashlight));
		array.add(getString(R.string.Sign));
		array.add(getString(R.string.Sketch));
		array.add(getString(R.string.Dictionary));
		
		HomeGridViewAdapter adapter = new HomeGridViewAdapter(getActivity(), array);
		grid.setAdapter(adapter);
		
		grid.setOnItemClickListener(new OnItemClickListener()
		{
			@Override
			public void onItemClick(AdapterView<?> arg0, View arg1, int arg2,
					long arg3) 
			{
				Fragment fragment;

				switch (arg2) {
				case 0:
					fragment = new Ruler();
					break;
				case 1:
					fragment = new TipCalculator();
					break;
				case 2:
					fragment = new UnitConverter();
					break;
				case 3:
					fragment = new List();
					break;
				case 4:
					fragment = new FlashLight();
					break;
				case 5:
					fragment = new Sign();
					break;
				case 6:
					fragment = new Sketch();
					break;
				case 7:
					fragment = new Dictionary();
					break;
				default:
					fragment = new Home();
					break;
				}
				
				FragmentManager fragmentManager = getActivity().getSupportFragmentManager();
				fragmentManager
						.beginTransaction()
						.replace(R.id.container,
								fragment).commit();
			}
		});
		
		return rootView;
    }
	
}
