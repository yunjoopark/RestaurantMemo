package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;
import android.widget.Toast;

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

    public void addItem(String name, float rate, int isChecked)
    {
        RestaurantInfo info = new RestaurantInfo(name, rate, isChecked);
        mData.add(info);
        // Adapter에 데이터가 바뀐걸 알리고 리스트뷰에 다시 그린다.
        notifyDataSetChanged();
    }

    public void delItem(int pos)
    {
        // Todo del item
        mData.remove(pos);
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
        CheckBox checkBox = null;
        TextView textView = null;
        RatingBar ratingBar = null;
        ImageView imageView = null;
        CustomHolder holder = null;

        if(convertView == null){
            convertView = mInflater.inflate(R.layout.sample_restaurant_element_view, parent, false);
            checkBox = (CheckBox)convertView.findViewById(R.id.restaurantCheckbox);
            textView = (TextView)convertView.findViewById(R.id.restaurantNameEditText);
            ratingBar = (RatingBar)convertView.findViewById(R.id.myRatingBar);
            ratingBar.setIsIndicator(false);
            ratingBar.setOnRatingBarChangeListener(new RatingBar.OnRatingBarChangeListener() {

                @Override
                public void onRatingChanged(RatingBar ratingBar, float rating, boolean fromUser) {
                    // Todo update Database with the new rating
                }

            });

            imageView = (ImageView) convertView.findViewById(R.id.selectImage);
            imageView.setTag(new Integer(pos));

            holder = new CustomHolder();
            holder.mCheckBox = checkBox;
            holder.mTextView = textView;
            holder.mRatingBar = ratingBar;

            holder.mImageView = imageView;

            convertView.setTag(holder);

        }
        else
        {
            holder = new CustomHolder();
            holder = (CustomHolder)convertView.getTag();
            checkBox = holder.mCheckBox;
            textView = holder.mTextView;
            ratingBar = holder.mRatingBar;
            imageView = holder.mImageView;
        }

        checkBox.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if (isChecked) {
                    // perform logic
                }

            }
        });


        checkBox.setChecked(mData.get(pos).IsChecked());
        textView.setText(mData.get(pos).getName());
        ratingBar.setRating(mData.get(pos).getScore());


        // TODO do not work... for newly added element
        imageView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
              Toast.makeText(context, "ImageView clicked for the row = " + view.getTag().toString(), Toast.LENGTH_SHORT).show();

                Log.d("onItemClick", "ImageView clicked for the row = " + view.getTag().toString());
                Intent intent = new Intent(context, SelectImageActivity.class);
                String restaurantName = mData.get(Integer.parseInt(view.getTag().toString())).getName();
                String restaurantScore = Float.toString(mData.get(Integer.parseInt(view.getTag().toString())).getScore());
                intent.putExtra("name", restaurantName);
                intent.putExtra("score", restaurantScore);
                context.startActivity(intent);
            }
        });


        return convertView;
    }

    public void setArrayList(ArrayList<RestaurantInfo> arrays){
        this.mData = arrays;
    }
}
