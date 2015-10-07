package com.example.multifunctional;

import java.util.ArrayList;

import android.app.Activity;
import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class SampleAdapter extends BaseAdapter
{
	
	private Context context;
    private ArrayList<SampleItem> navDrawerItems;
    
    public SampleAdapter(Context context, ArrayList<SampleItem> array)
    {
    	this.context = context;
    	this.navDrawerItems = array;
    }
    
	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return navDrawerItems.size();
	}
	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return navDrawerItems.get(position);
	}
	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return position;
	}
	@Override
	public View getView(int position, View convertView, ViewGroup parent) {
		// TODO Auto-generated method stub
		if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.fragment_main, null);
        }

        TextView imgIcon = (TextView) convertView.findViewById(R.id.navmenuitem_icon);
        TextView txtTitle = (TextView) convertView.findViewById(R.id.section_label);

        Typeface font = Typeface.createFromAsset(context.getAssets(), "fontawesome-webfont.ttf");
        imgIcon.setText(navDrawerItems.get(position).getIcon());
        imgIcon.setTypeface(font);
        txtTitle.setText(navDrawerItems.get(position).getTitle());

        return convertView;
	}
	

}