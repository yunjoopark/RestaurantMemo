package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

/**
 * Created by yunjoo on 2015. 12. 13..
 */
public class RestaurantAdapter extends ArrayAdapter<RestaurantInfo> {
    private Context context;
    private LayoutInflater mInflater;
    private ArrayList<RestaurantInfo> mData;

    public RestaurantAdapter(Context context) {

        super(context, R.layout.sample_restaurant_element_view);
        this.context = context;
        this.mData = new ArrayList<RestaurantInfo>();
        mInflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    public void addItem()
    {
        RestaurantInfo info = new RestaurantInfo();
        mData.add(info);
        // Adapter에 데이터가 바뀐걸 알리고 리스트뷰에 다시 그린다.
        notifyDataSetChanged();
    }
    
    public void addItem(String name, float rate)
    {
        RestaurantInfo info = new RestaurantInfo(name, rate);
        mData.add(info);
        // Adapter에 데이터가 바뀐걸 알리고 리스트뷰에 다시 그린다.
        notifyDataSetChanged();
    }

    //--------------------------------------------------------
    @Override
    public int getCount() {
        return mData.size();
    }


    //--------------------------------------------------------
    @Override
    public RestaurantInfo getItem(int position) {
        return mData.get(position);
    }


    //--------------------------------------------------------
    @Override
    public long getItemId(int position) {
        return position;
    }
    //--------------------------------------------------------
    @Override
    public View getView(int pos, View convertView, ViewGroup parent)
    {
        TextView textView = null;
        RatingBar ratingBar = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.sample_restaurant_element_view, parent, false);
            textView = (TextView)convertView.findViewById(R.id.restaurantNameEditText);
            // ratingBar = (RatingBar)convertView.findViewById(R.id.restaurantRating);

            convertView.setTag(textView);

        }
        else
        {
            textView = (TextView)convertView.getTag();
            // ratingBar = (RatingBar)convertView.getTag();
        }

        textView.setText(mData.get(pos).getName());

        return convertView;
    }
}
