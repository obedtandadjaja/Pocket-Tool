package com.example.multifunctional;

import java.util.Calendar;

import com.example.multifunctional.R;

import android.graphics.Typeface;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class Home extends Fragment{

	TextView tv, date;
	final String[] MONTHS = {"January", "Febuary", "March", "April", "May", "June", "July", "August", "September",
			"October", "November", "December"};
	final String[] DAYS = {"Sunday", "Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday"};
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(false);
	    
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View rootView = inflater.inflate(R.layout.home, container, false);
		
		return rootView;
    }
	
}
