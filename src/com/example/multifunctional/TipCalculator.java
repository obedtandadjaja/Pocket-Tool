package com.example.multifunctional;
import java.text.NumberFormat;

import com.app.obedtandadjaja.multifunctional.R;

import android.support.v4.app.Fragment;
import android.content.Context;
import android.graphics.Color;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class TipCalculator extends Fragment{

	TextView text1, text2;
	Button tip10, tip12, tip15, tip17, tip20;
	EditText edit1, edit2;
	@Override
	public void onCreate(Bundle savedInstanceState) {
	    super.onCreate(savedInstanceState);
	    setHasOptionsMenu(false);
	    
	}
	
	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.tipcalculator, container, false); 

		text1 = (TextView) rootView.findViewById(R.id.textView6);
		text2 = (TextView) rootView.findViewById(R.id.textView8);
		
		tip10 = (Button) rootView.findViewById(R.id.button1);
		tip12 = (Button) rootView.findViewById(R.id.button5);
		tip15 = (Button) rootView.findViewById(R.id.button2);
		tip17 = (Button) rootView.findViewById(R.id.button3);
		tip20 = (Button) rootView.findViewById(R.id.button4);

		edit1 = (EditText) rootView.findViewById(R.id.editText1);
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED, 0);
		edit2 = (EditText) rootView.findViewById(R.id.editText2);

		edit1.addTextChangedListener(new TextWatcher(){
			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
				try
				{
					String current = "";
					if(!s.toString().equals(current))
					{
						edit1.removeTextChangedListener(this);
						String cleanString = s.toString().replaceAll("[$, .]", "");
						double parsed = Double.parseDouble(cleanString);
						String formatted = NumberFormat.getCurrencyInstance().format(parsed/100);
						current = formatted;
						edit1.setText(formatted);
						edit1.setSelection(formatted.length());
						edit1.addTextChangedListener(this);
					}
				}
				catch(Exception e)
				{}
			}
			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
			}
		});
		
		edit2.addTextChangedListener(new TextWatcher(){

			@Override
			public void beforeTextChanged(CharSequence s, int start, int count,
					int after) {
				// TODO Auto-generated method stub
			}
			@Override
			public void onTextChanged(CharSequence s, int start, int before,
					int count) {
				// TODO Auto-generated method stub
			}

			@Override
			public void afterTextChanged(Editable s) {
				// TODO Auto-generated method stub
				if(edit1.getText().toString().equals("$"))
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
				}
				else
				{
					if(!edit2.getText().toString().equals("") || edit2.getText().toString().equals("."))
					{
						tip10.setBackgroundColor(Color.parseColor("#34495e"));
						tip12.setBackgroundColor(Color.parseColor("#34495e"));
						tip15.setBackgroundColor(Color.parseColor("#34495e"));
						tip17.setBackgroundColor(Color.parseColor("#34495e"));
						tip20.setBackgroundColor(Color.parseColor("#34495e"));
						float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
						float percentage = Float.parseFloat(edit2.getText().toString());
						float tip = prize*percentage/100;
						text1.setText(String.format("$%.2f", tip));
						text2.setText(String.format("$%.2f", tip+prize));
					}
					else
					{
						text1.setText("$0.00");
					}
				}
			}
		});

		tip10.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tip12.setBackgroundColor(Color.parseColor("#34495e"));
				tip15.setBackgroundColor(Color.parseColor("#34495e"));
				tip17.setBackgroundColor(Color.parseColor("#34495e"));
				tip20.setBackgroundColor(Color.parseColor("#34495e"));
				tip10.setBackgroundColor(Color.parseColor("#f39c12"));
				try
				{
					float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
					float tip = prize*10/100;
					text1.setText(String.format("$%.2f", tip));
					text2.setText(String.format("$%.2f", tip+prize));
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
					tip10.setBackgroundColor(Color.parseColor("#34495e"));
				}
			}
		});
		tip12.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tip10.setBackgroundColor(Color.parseColor("#34495e"));
				tip15.setBackgroundColor(Color.parseColor("#34495e"));
				tip17.setBackgroundColor(Color.parseColor("#34495e"));
				tip20.setBackgroundColor(Color.parseColor("#34495e"));
				tip12.setBackgroundColor(Color.parseColor("#f39c12"));
				try
				{
					float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
					float tip = (float) (prize*12.5/100);
					text1.setText(String.format("$%.2f", tip));
					text2.setText(String.format("$%.2f", tip+prize));
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
					tip12.setBackgroundColor(Color.parseColor("#34495e"));
				}
			}
		});
		tip15.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tip10.setBackgroundColor(Color.parseColor("#34495e"));
				tip12.setBackgroundColor(Color.parseColor("#34495e"));
				tip17.setBackgroundColor(Color.parseColor("#34495e"));
				tip20.setBackgroundColor(Color.parseColor("#34495e"));
				tip15.setBackgroundColor(Color.parseColor("#f39c12"));
				try
				{
					float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
					float tip = prize*15/100;
					text1.setText(String.format("$%.2f", tip));
					text2.setText(String.format("$%.2f", tip+prize));
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
					tip15.setBackgroundColor(Color.parseColor("#34495e"));
				}
			}
		});
		tip17.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tip10.setBackgroundColor(Color.parseColor("#34495e"));
				tip12.setBackgroundColor(Color.parseColor("#34495e"));
				tip15.setBackgroundColor(Color.parseColor("#34495e"));
				tip20.setBackgroundColor(Color.parseColor("#34495e"));
				tip17.setBackgroundColor(Color.parseColor("#f39c12"));
				try
				{
					float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
					float tip = (float) (prize*17.5/100);
					text1.setText(String.format("$%.2f", tip));
					text2.setText(String.format("$%.2f", tip+prize));
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
					tip17.setBackgroundColor(Color.parseColor("#34495e"));
				}
			}
		});
		tip20.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				tip10.setBackgroundColor(Color.parseColor("#34495e"));
				tip12.setBackgroundColor(Color.parseColor("#34495e"));
				tip15.setBackgroundColor(Color.parseColor("#34495e"));
				tip17.setBackgroundColor(Color.parseColor("#34495e"));
				tip20.setBackgroundColor(Color.parseColor("#f39c12"));
				try
				{
					float prize = Float.parseFloat(edit1.getText().toString().replaceAll("[$,]", ""));
					float tip = prize*20/100;
					text1.setText(String.format("$%.2f", tip));
					text2.setText(String.format("$%.2f", tip+prize));
				}
				catch(Exception e)
				{
					Toast.makeText(getActivity(), "Please input the price", Toast.LENGTH_SHORT).show();
					tip20.setBackgroundColor(Color.parseColor("#34495e"));
				}
			}
		});
		
		return rootView;
	}

}
