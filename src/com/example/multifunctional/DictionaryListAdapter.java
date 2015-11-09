package com.example.multifunctional;
import java.util.ArrayList;

import com.app.obedtandadjaja.multifunctional.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class DictionaryListAdapter extends BaseAdapter {

	ArrayList<Definition> def_array;
	Context context;

	public DictionaryListAdapter(ArrayList<Definition> def_array, Context context){
		this.context = context;
		this.def_array = def_array;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return def_array.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return def_array.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}

	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		View v = convertView;

		if (v == null) {
			LayoutInflater mInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
			v = mInflater.inflate(R.layout.dictionary_list_item, parent, false);
		}

		TextView text1 = (TextView) v.findViewById(R.id.textView1);
		TextView text2 = (TextView) v.findViewById(R.id.textView2);
		text1.setText(""+(position+1));
		text2.setText(def_array.get(position).getType()+" "+def_array.get(position).getDef());
		return v;
	}

}
