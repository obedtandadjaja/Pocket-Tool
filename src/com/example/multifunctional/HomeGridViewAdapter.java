package com.example.multifunctional;

import android.content.Context;
import android.graphics.Typeface;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import com.app.obedtandadjaja.multifunctional.R;

/**
 * Created by Covenant College on 3/12/15.
 */
public class HomeGridViewAdapter extends BaseAdapter {
    private Context mContext;
    private ArrayList<String> array;

    /**
    * Initialize values
    * @param  c Context object
    * @param  cat category
    * @param  layout number of layout
    */
    public HomeGridViewAdapter(Context c, ArrayList<String> array) {
        mContext = c;
        this.array = array;
    }

    /**
    * Getter
    * @return category size
    */
    public int getCount() {
        return array.size();
    }

    /**
    * Getter
    * @return null nothing
    */
    public Object getItem(int position) {
        return null;
    }

    /**
    * Getter
    * @return position position of item
    */
    public long getItemId(int position) {
        return position;
    }

    // create a new ImageView for each item referenced by the Adapter
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
    	if (convertView == null) {
            LayoutInflater mInflater = (LayoutInflater)
                    mContext.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = mInflater.inflate(R.layout.home_grid_item, null);
        }

        TextView imgIcon = (TextView) convertView.findViewById(R.id.textView1);

        Typeface font = Typeface.createFromAsset(mContext.getAssets(), "fontawesome-webfont.ttf");
        imgIcon.setText(array.get(position));
        imgIcon.setTypeface(font);

        return convertView;
    }
}
