package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RatingBar;
import android.widget.TextView;

import java.util.ArrayList;

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

    public RestaurantAdapter(Context context, ArrayList<RestaurantInfo> restaurantInfoArrayList) {
        super(context, R.layout.sample_restaurant_element_view);
        this.context = context;
        this.mData = restaurantInfoArrayList;
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
        CustomHolder holder = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.sample_restaurant_element_view, parent, false);
            textView = (TextView)convertView.findViewById(R.id.restaurantNameEditText);
            ratingBar = (RatingBar)convertView.findViewById(R.id.myRatingBar);

            holder = new CustomHolder();
            holder.mTextView = textView;
            holder.mRatingBar = ratingBar;

            convertView.setTag(holder);

        }
        else
        {
            holder = new CustomHolder();
            holder = (CustomHolder)convertView.getTag();
            textView = holder.mTextView;
            ratingBar = holder.mRatingBar;
        }

        textView.setText(mData.get(pos).getName());
        ratingBar.setRating(mData.get(pos).getScore());

        return convertView;
    }

    public void setArrayList(ArrayList<RestaurantInfo> arrays){
        this.mData = arrays;
    }
}
