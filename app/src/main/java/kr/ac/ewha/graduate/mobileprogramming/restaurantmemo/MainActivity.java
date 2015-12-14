package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.Cursor;
import android.media.Rating;
import android.os.Bundle;
import android.util.Log;
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
    SharedPreferences sharedPreferences;

    // member var. for DB
    private DbOpenHelper mDbOpenHelper;
    private Cursor mCursor;
    private ArrayList<RestaurantInfo> mRestaurantArray;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        // set the activity with no title
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
        setContentView(R.layout.activity_main);

        // DB Create and Open
        mDbOpenHelper = new DbOpenHelper(this);
        mDbOpenHelper.open();
        mRestaurantArray = new ArrayList<RestaurantInfo>();
        doWhileCursorToArray();

        restaurantListView = (ListView) findViewById(R.id.restaurantListView);

        /// adapter 생성 및 등록
        //mAdapter = new RestaurantAdapter(this);
        mAdapter = new RestaurantAdapter(this, mRestaurantArray);
        restaurantListView.setAdapter(mAdapter);



        /**
         * ListView의 Item을 롱클릭 할때 호출 ( 선택한 아이템의 DB 컬럼과 Data를 삭제 한다. )
         */
        restaurantListView.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                Log.e("POSITION: ", "position = " + position);

                boolean result = mDbOpenHelper.deleteColumn(position + 1);
                Log.e("RESULT", "result = " + result);

                if(result){
                    mRestaurantArray.remove(position);
                    mAdapter.setArrayList(mRestaurantArray);
                    mAdapter.notifyDataSetChanged();
                }else {
                    Toast.makeText(getApplicationContext(), "INDEX를 확인해 주세요.",
                            Toast.LENGTH_LONG).show();
                }

                return false;

            }
        });

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);
    }



    /**
     * DB에서 받아온 값을 ArrayList에 Add
     */
    private void doWhileCursorToArray(){

        RestaurantInfo newRes = null;

        mCursor = null;
        mCursor = mDbOpenHelper.getAllColumns();
        Log.e("Cursor", "COUNT = " + mCursor.getCount());

        while (mCursor.moveToNext()) {

            newRes = new RestaurantInfo(
                    mCursor.getInt(mCursor.getColumnIndex("_id")),
                    mCursor.getString(mCursor.getColumnIndex("name")),
                    Float.parseFloat(mCursor.getString(mCursor.getColumnIndex("score"))),
                    mCursor.getInt(mCursor.getColumnIndex("checked"))
            );

            mRestaurantArray.add(newRes);
        }

        mCursor.close();
    }

    public void addRestaurant(View view) {
//        final RestaurantInfo newRes = new RestaurantInfo();

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

                mDbOpenHelper.insertColumn(name, score);
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

    @Override
    protected void onDestroy() {
        mDbOpenHelper.close();
        super.onDestroy();
    }
}
