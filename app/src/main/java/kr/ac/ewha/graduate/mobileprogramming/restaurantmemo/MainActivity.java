package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;

import java.util.ArrayList;

public class MainActivity extends Activity {
    ListView restaurantListView;
    private ArrayList<RestaurantInfo> mRestaurantList;
    private ArrayAdapter<RestaurantInfo> mRestaurantAdapterList;

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
        final RestaurantInfo newRes = new RestaurantInfo();

        AlertDialog.Builder dialog = new AlertDialog.Builder(this);
        dialog.setTitle("Set Restaurant Name & Score");
        dialog.setMessage("Input Restaurant Name and Score");

        LinearLayout inputLayout = new LinearLayout(this);
        inputLayout.setOrientation(LinearLayout.VERTICAL);

        // 이름 입력
        final EditText nameBox = new EditText(this);
        nameBox.setHint("restaurantName");
        inputLayout.addView(nameBox);

        // 점수
        final RatingBar rate = new RatingBar(this);
        rate.setLayoutParams(new ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT
                , ViewGroup.LayoutParams.WRAP_CONTENT));
        rate.setNumStars(5);
        rate.setStepSize((float) 0.5);   // 별 색을 몇개씩 올릴 것인가
        rate.setRating((float) 3);       // 처음 별을 몇개로 표시할 것인가
        inputLayout.addView(rate);

        //final EditText scoreBox = new EditText(this);
        //scoreBox.setHint("restaurantScore");
        //layout.addView(scoreBox);

        dialog.setView(inputLayout);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                newRes.setName(nameBox.getText().toString());
                newRes.setScore(rate.getRating());
                //newRes.setScore(Integer.parseInt(scoreBox.getText().toString()));
            }
        });
        dialog.setNegativeButton("Cancel", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });

        dialog.show();
    }


}
