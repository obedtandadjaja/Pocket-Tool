package com.example.multifunctional;
import java.util.ArrayList;

import com.example.multifunctional.R;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class ListViewAdapter extends BaseAdapter {

	ArrayList<String> list;
	Context context;

	public ListViewAdapter(ArrayList<String> list, Context context){
		this.context = context;
		this.list = list;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return list.size();
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return list.get(position);
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
			v = mInflater.inflate(R.layout.list_items, null);
		}

		final TextView text = (TextView) v.findViewById(R.id.textView1);
		text.setText(list.get(position));

		return v;
	}

}
