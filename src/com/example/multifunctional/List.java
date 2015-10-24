package com.example.multifunctional;

import java.util.ArrayList;

import com.example.multifunctional.R;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.DialogInterface.OnShowListener;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.graphics.Color;
import android.graphics.Typeface;
import android.graphics.drawable.ColorDrawable;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.ToggleButton;

public class List extends Fragment {

	ListView listview;
	SharedPreferences storage;
	Editor editor;
	ListViewAdapter adapter;
	ArrayList<String> list;
	ArrayList<Integer> index;
	ToggleButton toggle;
	Button create;
	boolean bool = false;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setHasOptionsMenu(false);

		index = new ArrayList<Integer>();
		storage = getActivity().getSharedPreferences("storage", Context.MODE_PRIVATE);
		editor = storage.edit();

		list = new ArrayList<String>();
		for(int i = 0; i < 100; i++)
		{
			if(storage.contains(""+i))
			{
				String s = storage.getString(""+i, "");
				if(!s.equalsIgnoreCase(""))
				{
					list.add(s);
					index.add(i);
				}
			}
		}

		adapter = new ListViewAdapter(list, getActivity());

	}

	@Override public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState)
	{
		View rootView = inflater.inflate(R.layout.list, container, false);
		listview = (ListView) rootView.findViewById(R.id.listView1);
		listview.setAdapter(adapter);
		toggle = (ToggleButton) rootView.findViewById(R.id.toggleButton1);

		final Typeface fontList = Typeface.createFromAsset(getActivity().getAssets(), "fontawesome-webfont.ttf");
		create = (Button) rootView.findViewById(R.id.button);
		create.setTypeface(fontList);

		toggle.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				if(toggle.isChecked())
				{
					bool = true;
					toggle.setBackgroundColor(Color.parseColor("#f39c12"));
				}
				else
				{
					bool = false;
					toggle.setBackgroundColor(Color.parseColor("#e74c3c"));
				}
			}

		});

		create.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
				alert.setMessage("Memo:");

				// Set an EditText view to get user input 
				final EditText input = new EditText(getActivity());
				alert.setView(input);

				alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						// Do something with value!
						if(input.getText().toString().trim().length()>0)
						{
							for(int i = 0; i < 100; i++)
							{
								if(!index.contains(i))
								{
									list.add(input.getText().toString());
									index.add(i);
									editor.putString(""+i, input.getText().toString());
									editor.commit();
									break;
								}
							}
						}
					}
				});

				alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int whichButton) {
						// Canceled.
					}
				});

				AlertDialog dialog = alert.create();
				dialog.setOnShowListener(new OnShowListener() 
				{
					@Override
					public void onShow(DialogInterface dialog) 
					{
						InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
						imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
					}
				});

				dialog.show();
			}

		});

		listview.setOnItemClickListener(new android.widget.AdapterView.OnItemClickListener(){

			@Override
			public void onItemClick(android.widget.AdapterView<?> parent,
					View view, int position, long id) {
				// TODO Auto-generated method stub
				final int pos = position;
				if(bool)
				{
					list.remove(pos);
					editor.putString(""+index.get(pos), "");
					editor.commit();
					index.remove(pos);
					adapter.notifyDataSetChanged();
				}
				else
				{
					AlertDialog.Builder alert = new AlertDialog.Builder(getActivity());
					alert.setMessage("Memo:");

					// Set an EditText view to get user input 
					final EditText input = new EditText(getActivity());
					input.setText(list.get(pos));
					alert.setView(input);

					alert.setPositiveButton("Ok", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							// Do something with value!
							if(input.getText().toString().trim().length()>0)
							{
								list.set(pos, input.getText().toString());
								editor.putString(""+index.get(pos), input.getText().toString());
								editor.commit();
							}
							else
							{
								list.remove(pos);
								editor.putString(""+index.get(pos), "");
								editor.commit();
								adapter.notifyDataSetChanged();
							}
						}
					});

					alert.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
						@Override
						public void onClick(DialogInterface dialog, int whichButton) {
							// Canceled.
						}
					});

					AlertDialog dialog = alert.create();
					dialog.setOnShowListener(new OnShowListener() {

						@Override
						public void onShow(DialogInterface dialog) {
							InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
							imm.showSoftInput(input, InputMethodManager.SHOW_IMPLICIT);
						}
					});

					dialog.show();
				}
			}
		});

		return rootView;
	}

}
