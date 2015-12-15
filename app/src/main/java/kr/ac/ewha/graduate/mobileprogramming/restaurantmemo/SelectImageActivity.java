package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.RatingBar;
import android.widget.TextView;

import java.io.BufferedInputStream;
import java.io.InputStream;

public class SelectImageActivity extends Activity {

    private static final int SELECT_IMAGE = 1;

    private TextView restaurantName;
    private ImageView restaurantImageView;
    private RatingBar restaurantRatingBar;
    String restaurantNameStr;

    SharedPreferences sharedPreferences;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        requestWindowFeature(Window.FEATURE_NO_TITLE);
        getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);

        setContentView(R.layout.activity_select_image);

        sharedPreferences = getPreferences(Context.MODE_PRIVATE);

        restaurantName = (TextView) findViewById(R.id.restaurantName);
        restaurantImageView = (ImageView) findViewById(R.id.restaurantImageView);
        restaurantRatingBar = (RatingBar) findViewById(R.id.selectImageView_restaurantRating);

        // get object and set the restaurant name and image view
        init();
    }

    private void init() {
        Bundle bundle = getIntent().getExtras();
        restaurantRatingBar.setRating(Float.parseFloat(bundle.getString("score")));

        restaurantNameStr = bundle.getString("name");
        restaurantName.setText(restaurantNameStr);
        restaurantName.setGravity(Gravity.CENTER);

//        Log.e("restaurantName", restaurantNameStr);
        String path = sharedPreferences.getString("uri_" + restaurantNameStr, "");
//        Bitmap bm = MediaStore.Images.Media.getBitmap(getContentResolver(), Uri.parse(path));
//        restaurantImageView.setImageBitmap(bm);

        restaurantImageView.setImageURI(Uri.parse(path));
        Log.d("restaurant_image uri", restaurantNameStr + path);

    }

    public void selectRestaurantImage(View view) {
        Intent intent = new Intent(Intent.ACTION_PICK);

        intent.setType("image/*");
        startActivityForResult(intent, SELECT_IMAGE);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == SELECT_IMAGE && resultCode == RESULT_OK) {
            Uri selectedImageUri = data.getData();
            restaurantImageView.setImageURI(selectedImageUri);

            SharedPreferences.Editor editor = sharedPreferences.edit();
            editor.putString("uri_" + restaurantNameStr, selectedImageUri.toString());
            editor.commit();

            Log.d("restaurant name", restaurantNameStr);
            Log.d("restaurant_image uri:", selectedImageUri.toString());
        }
    }
}
