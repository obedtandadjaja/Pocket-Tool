package com.example.multifunctional;

import java.util.ArrayList;
import android.content.Context;
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
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;
import com.app.obedtandadjaja.multifunctional.R;

public class Dictionary extends Fragment {

	ListView lv;
	EditText et;
	Button find;
	TextView tv;
	DefinitionDataSource dbs;
	DictionaryListAdapter adapter;
	ProgressBar pb;
	
	@Override
	public void onCreate(Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
		imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
	}

	@Override
	public View onCreateView(LayoutInflater inflater, ViewGroup container,
			Bundle savedInstanceState) {
		// TODO Auto-generated method stub
		View rootView = inflater.inflate(R.layout.dictionary, container, false);
		
		lv = (ListView) rootView.findViewById(R.id.listView1);
		et = (EditText) rootView.findViewById(R.id.editText1);
		find = (Button) rootView.findViewById(R.id.button1);
		tv = (TextView) rootView.findViewById(R.id.textView1);
		pb = (ProgressBar) rootView.findViewById(R.id.progressBar1);

		dbs = new DefinitionDataSource(getActivity());
		
		find.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				InputMethodManager imm = (InputMethodManager) getActivity().getSystemService(Context.INPUT_METHOD_SERVICE);
				imm.toggleSoftInput(InputMethodManager.SHOW_FORCED,0);
				
				String word = et.getText().toString();
				ArrayList<Definition> def_array = dbs.getDefinition(word);
				
				if(def_array.size() == 0)
				{
					lv.setVisibility(View.INVISIBLE);
//					StringBuilder sb = new StringBuilder("Word Not Found!\n\nRelated Words:\n");
//					ArrayList<String> word_array = dbs.getRelatedWords(word);
//					if(word_array.size() == 0)
//					{
//						sb.append("None");
//					}
//					for(int i = 0; i < word_array.size(); i++)
//					{
//						sb.append(word_array.get(i)+"; ");
//					}
//					tv.setText(sb.toString());
					Toast.makeText(getActivity(), "Word Not Found!", Toast.LENGTH_SHORT).show();
				}
				else
				{
					lv.setVisibility(View.VISIBLE);
					tv.setVisibility(View.INVISIBLE);
					adapter = new DictionaryListAdapter(def_array, getActivity());
					lv.setAdapter(adapter);
				}
			}
		});
		
		return rootView;
	}
	
}
