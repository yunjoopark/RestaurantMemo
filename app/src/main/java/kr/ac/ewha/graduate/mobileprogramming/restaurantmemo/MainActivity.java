package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ListView;

public class MainActivity extends Activity {
//    RestaurantAdapter restaurantAdapter;
    ListView restaurantListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the activity with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        setContentView(R.layout.activity_main);

        restaurantListView = (ListView) findViewById(R.id.restaurantListView);
    }

    public void addRestaurant(View view) {

    }


}
