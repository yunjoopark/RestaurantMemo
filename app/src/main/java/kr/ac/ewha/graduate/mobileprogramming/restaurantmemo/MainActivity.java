package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.RatingBar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends Activity {
    private Toast toast;
    private long backKeyPressedTime = 0;

    ListView restaurantListView;

    RestaurantAdapter mAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the activity with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        restaurantListView = (ListView) findViewById(R.id.restaurantListView);

        /// adapter 생성 및 등록
        mAdapter = new RestaurantAdapter(this);
        restaurantListView.setAdapter(mAdapter);

        /// ListView click Listener 등록
        restaurantListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                Toast.makeText(MainActivity.this, "Clicked at " + position, Toast.LENGTH_LONG).show();

                // set onClick for the selectPicture

            }
        });

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

        dialog.setView(inputLayout);

        dialog.setPositiveButton("OK", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                String name = nameBox.getText().toString();
                float score = rate.getRating();
                mAdapter.addItem(name, score);
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

    public void selectImage(View view) {
        Intent intent = new Intent(this, SelectImageActivity.class);
        startActivity(intent);
    }


    @Override
    public void onBackPressed() {
        if (System.currentTimeMillis() > backKeyPressedTime + 2000) {
            backKeyPressedTime = System.currentTimeMillis();
            showGuide();
            return;
        }
        if (System.currentTimeMillis() <= backKeyPressedTime + 2000) {
            super.onBackPressed();
            toast.cancel();
        }
    }

    private void showGuide() {
        toast = Toast.makeText(MainActivity.this, "\'뒤로\'버튼을 한번 더 누르시면 종료됩니다.", Toast.LENGTH_SHORT);
        toast.show();
    }
}
