package com.example.multifunctional;

import java.util.ArrayList;

import com.example.multifunctional.ColorPickerDialog.OnColorChangedListener;

import android.content.DialogInterface;
import android.app.AlertDialog;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.Window;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CompoundButton;
import android.widget.CompoundButton.OnCheckedChangeListener;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.ToggleButton;

public class Sign extends Fragment {

	Button button1, button2, sign, font, speed;
	EditText et;
	int c1, c2, s;
	ToggleButton toggle;
	TextView speed_text;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(false);
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
    {
		c1 = Color.BLACK;
		c2 = Color.WHITE;
		
		View rootView = inflater.inflate(R.layout.sign1, container, false);
		et = (EditText) rootView.findViewById(R.id.editText1);
		button1 = (Button) rootView.findViewById(R.id.button1);
		button2 = (Button) rootView.findViewById(R.id.button2);
		sign = (Button) rootView.findViewById(R.id.button3);
		toggle = (ToggleButton) rootView.findViewById(R.id.toggleButton1);
		font = (Button) rootView.findViewById(R.id.button4);
		speed = (Button) rootView.findViewById(R.id.button5);
		speed_text = (TextView) rootView.findViewById(R.id.TextView04);
		
		final Dialog dialog1 = new ColorPickerDialog(getActivity(), new OnColorChangedListener(){
			@Override
			public void colorChanged(int color) {
				// TODO Auto-generated method stub
				c1 = color;
				button1.setBackgroundColor(color);
			}
		}, Color.rgb(0, 0, 0));
		
		final Dialog dialog2 = new ColorPickerDialog(getActivity(), new OnColorChangedListener(){
			@Override
			public void colorChanged(int color) {
				// TODO Auto-generated method stub
				c2 = color;
				button2.setBackgroundColor(color);
			}
		}, Color.rgb(0, 0, 0));
		
		dialog1.requestWindowFeature(Window.FEATURE_NO_TITLE);
		dialog2.requestWindowFeature(Window.FEATURE_NO_TITLE);
		
		button1.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog1.show();
			}
		});
		button2.setOnClickListener(new OnClickListener()
		{
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				dialog2.show();
			}
		});
		toggle.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			@Override
			public void onCheckedChanged(CompoundButton buttonView,
					boolean isChecked) {
				// TODO Auto-generated method stub
				if(toggle.isChecked())
				{
					toggle.setBackgroundColor(Color.parseColor("#f39c12"));
					speed.setVisibility(View.VISIBLE);
					speed_text.setVisibility(View.VISIBLE);
				}
				else
				{
					toggle.setBackgroundColor(Color.parseColor("#34495e"));
					speed.setVisibility(View.GONE);
					speed_text.setVisibility(View.GONE);
				}
			}
		});
		
		font.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
				final FontListAdapter arrayAdapter = new FontListAdapter(getActivity());
				builder.setAdapter(arrayAdapter, new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						// TODO Auto-generated method stub
						font.setText((String)arrayAdapter.getItem(which));
					}
				});
				AlertDialog alert = builder.create();
				alert.show();
			}
		});
		
		speed.setOnClickListener(new OnClickListener(){
				@Override
				public void onClick(View arg0) {
					// TODO Auto-generated method stub
					final CharSequence[] options = {"Slow", "Medium", "Fast", "Quite Fast", "Very Fast"};
					AlertDialog.Builder builder = new AlertDialog.Builder(getActivity());
					builder.setItems(options, new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int position) {
							// TODO Auto-generated method stub
							dialog.dismiss();
							speed.setText(options[position]);
							s = position;
						}
					});
					AlertDialog alert = builder.create();
					alert.show();
				};
		});
		
		sign.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				Intent i = new Intent(getActivity(), TextViewMarquee.class);
				i.putExtra("string", et.getText().toString());
				i.putExtra("background", c1);
				i.putExtra("text", c2);
				i.putExtra("animate", toggle.isChecked());
				i.putExtra("font", font.getText());
				i.putExtra("speed", s);
				Sign.this.startActivity(i);
			}
		});
		return rootView;
    }
	
}
