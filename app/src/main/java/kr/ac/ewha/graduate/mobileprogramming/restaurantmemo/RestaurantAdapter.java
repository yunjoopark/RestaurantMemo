package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.widget.ArrayAdapter;

import java.util.ArrayList;

/**
 * Created by yunjoo on 2015. 12. 13..
 */
public class RestaurantAdapter extends ArrayAdapter<RestaurantInfo> {
    private final Context context;
    private final ArrayList<RestaurantInfo> restaurantArrayList;

    public RestaurantAdapter(Context context, ArrayList<RestaurantInfo> restaurantArrayList) {

        super(context, R.layout.sample_restaurant_element_view, restaurantArrayList);

        this.context = context;
        this.restaurantArrayList = restaurantArrayList;
    }
}
