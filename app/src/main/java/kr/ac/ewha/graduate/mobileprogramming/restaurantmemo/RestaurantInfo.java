package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.os.Parcel;
import android.os.Parcelable;
import android.util.Log;

/**
 * Created by Bak on 2015. 12. 13..
 */



public class RestaurantInfo implements Parcelable{
    public String mName;
    public float mScore;
    public boolean misChecked;

    static final int MAX_SCORE = 5;
    static final String DEFAULT_NAME = "Restaurant";

    public RestaurantInfo()
    {
        mName = DEFAULT_NAME;
        mScore = 0;
        misChecked = false;
    }

    public RestaurantInfo(String name, float score)
    {
        mName = name;
        mScore = score;
        misChecked = false;
    }

    public RestaurantInfo(int id, String name, float score, int isChecked) {
        mName = name;
        mScore = score;
        if (isChecked == 1) {
            misChecked = true;
        } else {
            misChecked = false;
        }
    }

    protected RestaurantInfo(Parcel in) {
        mName = in.readString();
        mScore = in.readInt();
    }

    public static final Creator<RestaurantInfo> CREATOR = new Creator<RestaurantInfo>() {
        @Override
        public RestaurantInfo createFromParcel(Parcel in) {
            return new RestaurantInfo(in);
        }

        @Override
        public RestaurantInfo[] newArray(int size) {
            return new RestaurantInfo[size];
        }
    };

    public float getScore() {
        return mScore;
    }

    public void setScore(float newScore) {
        if(newScore > MAX_SCORE)
        {
            Log.d("INPUT_ERROR", "input invalid score");
            return;
        }

        this.mScore = newScore;
    }

    public boolean IsChecked() { return misChecked; }

    public String getName() {
        return mName;
    }

    public void setName(String newName) {
        this.mName = newName;
    }

    @Override
    public int describeContents() {
        return 0;
    }

    @Override
    public void writeToParcel(Parcel dest, int flags) {
        dest.writeString(mName);
        dest.writeFloat(mScore);
    }
}


