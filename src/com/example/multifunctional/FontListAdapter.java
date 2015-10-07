package com.example.multifunctional;
import java.util.ArrayList;

import com.example.multifunctional.R;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

public class FontListAdapter extends BaseAdapter {

	final CharSequence[] fonts = {"airstrike", "airstrike3d", "airstrikeacad", "airstrikeb3d", "DJBStraightUpNowBold",
			"DJBStraightUpNowBounce", "MotionPicture", "OrangeJuice", "PWKool", "RemachineScript", "SouthernAire",
			"waltographUI", "WeddingChardonnay", "OpenSans"};
	Context context;

	public FontListAdapter(Context context){
		this.context = context;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return fonts.length;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return fonts[position];
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
			v = mInflater.inflate(R.layout.font_list_items, parent, false);
		}

		final TextView text = (TextView) v.findViewById(R.id.textView1);
		text.setText(fonts[position]);
		if(!fonts[position].equals("OpenSans"))
		{
			final Typeface fontList = Typeface.createFromAsset(context.getAssets(), "fonts/"+fonts[position]+".ttf");
			text.setTypeface(fontList);
		}
		return v;
	}

}
