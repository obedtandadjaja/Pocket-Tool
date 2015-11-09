package com.example.multifunctional;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.Map;

import com.app.obedtandadjaja.multifunctional.R;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.ContextMenu;
import android.view.ContextMenu.ContextMenuInfo;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class UnitConverter extends Fragment{

	EditText edit1, edit2, edit01, edit02;
	Button type, convert1, convert2;
	int num = 0;
	Map<String, Double> unit;
	boolean bool = true;
	DecimalFormat formatter = new DecimalFormat("#.00");
	DecimalFormat formatter1 = new DecimalFormat("#.000");
	static boolean flag1 = true;
	static boolean flag2 = true;
	
	@Override
	public void onActivityCreated(Bundle savedInstanceState) {
	    super.onActivityCreated(savedInstanceState);
	    setHasOptionsMenu(false);
	    setup();
	}
	
	@Override
    public void onSaveInstanceState(Bundle outState) 
	{
        super.onSaveInstanceState(outState);
    }
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		View rootView = inflater.inflate(R.layout.unitconverter, container, false);
		
		edit1 = (EditText) rootView.findViewById(R.id.editText1);
		edit2 = (EditText) rootView.findViewById(R.id.editText2);
		edit01 = (EditText) rootView.findViewById(R.id.EditText01);
		edit02 = (EditText) rootView.findViewById(R.id.EditText02);
		
		type = (Button) rootView.findViewById(R.id.button1);
		convert1 = (Button) rootView.findViewById(R.id.button2);
		convert2 = (Button) rootView.findViewById(R.id.button3);
		
		convert1.setText("Meter");
		convert2.setText("Foot");
		
		type.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				num = 0;
				registerForContextMenu(arg0); 
				getActivity().openContextMenu(arg0);
				unregisterForContextMenu(arg0);
			}
		});
		
		convert1.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				num = 1;
				registerForContextMenu(arg0); 
				getActivity().openContextMenu(arg0);
				unregisterForContextMenu(arg0);
			}
		});
		
		convert2.setOnClickListener(new View.OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				num = 2;
				registerForContextMenu(arg0); 
				getActivity().openContextMenu(arg0);
				unregisterForContextMenu(arg0);
			}
		});
		
		edit1.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(flag1)
				{
					if(!edit1.getText().toString().equals("") && !edit1.getText().toString().equals("."))
					{
						double num = Double.parseDouble(edit1.getText().toString());
						change(num, 1);
					}
				}
				else
				{
					flag1 = true;
				}
			}
		});
		
		edit2.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(flag2)
				{
					if(!edit2.getText().toString().equals("") && !edit2.getText().toString().equals("."))
					{
						double num = Double.parseDouble(edit2.getText().toString());
						change(num, 2);
					}
				}
				else
				{
					flag2 = true;
				}
			}
		});
		
		edit01.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(!edit01.getText().toString().equals("") && !edit01.getText().toString().equals("."))
				{
					try
					{
						double d = Double.valueOf(edit01.getText().toString());
						double dd = (d-32)/1.8;
						if(!edit02.getText().toString().equals(formatter.format(dd)))
						{
							if(!edit01.getText().toString().equals(formatter.format(d)))
							{
								edit02.setText(formatter.format(dd));
							}
						}
					}
					catch (Exception e)
					{
						Toast.makeText(getActivity(), "Invalid Input!", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		edit02.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
			}
			@Override
			public void afterTextChanged(Editable s) {
				if(!edit02.getText().toString().equals("") && !edit02.getText().toString().equals("."))
				{
					try
					{
						double d = Double.parseDouble(edit02.getText().toString());
						double dd = d*1.8+32;
						if(!edit01.getText().toString().equals(formatter.format(dd)))
						{
							if(!edit02.getText().toString().equals(formatter.format(d)))
							{
								edit01.setText(formatter.format(dd));
							}
						}
					}
					catch (Exception e)
					{
						Toast.makeText(getActivity(), "Invalid Input!", Toast.LENGTH_SHORT).show();
					}
				}
			}
		});
		
		return rootView;
    }
	
	public void change(double d, int n)
	{
		convert1 = (Button) getActivity().findViewById(R.id.button2);
		convert2 = (Button) getActivity().findViewById(R.id.button3);
		EditText edit;
		double a,b;
		if (n == 1)
		{
			edit = (EditText) getActivity().findViewById(R.id.editText2);
			a = unit.get(convert1.getText().toString());
			a = d*a;
			b = unit.get(convert2.getText().toString());
			b = a/b;
			if(!Double.toString(b).equals(edit.getText().toString()))
			{
				flag2 = false;
				edit.setText(formatter1.format(b));
			}
		}
		else
		{
			edit = (EditText) getActivity().findViewById(R.id.editText1);
			a = unit.get(convert2.getText().toString());
			a = d*a;
			b = unit.get(convert1.getText().toString());
			b = a/b;
			if(!Double.toString(b).equals(edit.getText().toString()))
			{
				flag1 = false;
				edit.setText(formatter1.format(b));
			}
		}
	}
	
	@Override
	public void onCreateContextMenu(ContextMenu menu, View v,
			ContextMenuInfo menuInfo) {
		super.onCreateContextMenu(menu, v, menuInfo);
		
		if(v.getId() == R.id.button1)
		{
			menu.add(0, v.getId(), 0, "Length");
			menu.add(0, v.getId(), 0, "Weight");
			menu.add(0, v.getId(), 0, "Volume");
			menu.add(0, v.getId(), 0, "Area");
		}
		else
		{
			if(type.getText() == "Weight")
			{
				menu.add(0, v.getId(), 0, "Gram");
				menu.add(0, v.getId(), 0, "Kilogram");
				menu.add(0, v.getId(), 0, "Ounce");
				menu.add(0, v.getId(), 0, "Pound");
				menu.add(0, v.getId(), 0, "Stone");
			}
			else if(type.getText() == "Volume")
			{
				menu.add(0, v.getId(), 0, "Mililiter");
				menu.add(0, v.getId(), 0, "Liter");
				menu.add(0, v.getId(), 0, "Tablespoon");
				menu.add(0, v.getId(), 0, "Teaspoon");
				menu.add(0, v.getId(), 0, "Gallon");
				menu.add(0, v.getId(), 0, "Pint");
				menu.add(0, v.getId(), 0, "Cup");
				menu.add(0, v.getId(), 0, "Dram");
				menu.add(0, v.getId(), 0, "Quart");
				menu.add(0, v.getId(), 0, "Cubic Meter");
				menu.add(0, v.getId(), 0, "Cubic Feet");
			}
			else if(type.getText() == "Area")
			{
				menu.add(0, v.getId(), 0, "Square Mile");
				menu.add(0, v.getId(), 0, "Square Yard");
				menu.add(0, v.getId(), 0, "Square Foot");
				menu.add(0, v.getId(), 0, "Square Inch");
				menu.add(0, v.getId(), 0, "Hectare");
				menu.add(0, v.getId(), 0, "Acre");
				menu.add(0, v.getId(), 0, "Square Kilometer");
				menu.add(0, v.getId(), 0, "Square Meter");
				menu.add(0, v.getId(), 0, "Square Centimeter");
				menu.add(0, v.getId(), 0, "Square Milimeter");
			}
			else
			{
				menu.add(0, v.getId(), 0, "Milimeter");
				menu.add(0, v.getId(), 0, "Meter");
				menu.add(0, v.getId(), 0, "Kilometer");
				menu.add(0, v.getId(), 0, "Mile");
				menu.add(0, v.getId(), 0, "Inch");
				menu.add(0, v.getId(), 0, "Yard");
				menu.add(0, v.getId(), 0, "Foot");
			}
		}
	}
	
	@Override
	public boolean onContextItemSelected(MenuItem item) {
		type = (Button) getView().findViewById(R.id.button1);
		convert1 = (Button) getView().findViewById(R.id.button2);
		convert2 = (Button) getView().findViewById(R.id.button3);
		edit1 = (EditText) getView().findViewById(R.id.editText1);
		edit2 = (EditText) getView().findViewById(R.id.editText2);
		edit1.setText("");
		edit2.setText("");
		if(num == 0)
		{
			type.setText(item.getTitle());
			if(item.getTitle() == "Length")
			{
				convert1.setText("Meter");
				convert2.setText("Foot");
			}
			else if(item.getTitle() == "Weight")
			{
				convert1.setText("Gram");
				convert2.setText("Ounce");
			}
			else if(item.getTitle() == "Volume")
			{
				convert1.setText("Mililiter");
				convert2.setText("Teaspoon");
			}
			else
			{
				convert1.setText("Square Mile");
				convert2.setText("Hectare");
			}
		}
		else if(num == 1)
		{
			convert1.setText(item.getTitle());
		}
		else if(num == 2)
		{
			convert2.setText(item.getTitle());
		}
		
		edit1.clearComposingText();
		edit2.clearComposingText();
		return true;
	}
	
	public void setup()
	{
		unit = new HashMap<String, Double>();
		//length
		unit.put("Meter", 1000.0);
		unit.put("Milimeter", 1.0);
		unit.put("Kilometer", 1000000.0);
		unit.put("Mile", 1609344.0);
		unit.put("Inch", 25.4);
		unit.put("Yard", 914.4);
		unit.put("Foot", 304.8);
		//weight
		unit.put("Gram", 1.0);
		unit.put("Ounce", 28.349523);
		unit.put("Kilogram", 1000.0);
		unit.put("Pound", 453.59237);
		unit.put("Stone", 6350.2932);
		//volume
		unit.put("Mililiter", 1.0);
		unit.put("Liter", 1000.0);
		unit.put("Teaspoon", 0.0049289216*1000);
		unit.put("Tablespoon", 0.014786765*1000);
		unit.put("Pint", 0.47317647*1000);
		unit.put("Gallon", 3.7854118*1000);
		unit.put("Cup", 0.23658824*1000);
		unit.put("Dram", 0.0036966912*1000);
		unit.put("Quart", 0.94635295*1000);
		unit.put("Cubic Meter", 1000000.0);
		unit.put("Cubic Feet", 28.316847*1000);
		//area
		unit.put("Square Mile", 2.5899881E12);
		unit.put("Square Yard", 836127.0);
		unit.put("Square Foot", 92903.04);
		unit.put("Square Inch", 645.16);
		unit.put("Hectare", 10000000000.0);
		unit.put("Acre", 4046856422.0);
		unit.put("Square Kilometer", 1000000000000.0);
		unit.put("Square Meter", 1000000.0);
		unit.put("Square Centimeter", 100.0);
		unit.put("Square Milimeter", 1.0);
	}
	
}
