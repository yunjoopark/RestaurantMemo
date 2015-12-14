package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.provider.BaseColumns;

/**
 * Created by yunjoo on 2015. 12. 13..
 */
public final class Databases {
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "restaurantname";
        public static final Float SCORE = (float) 0.0;
        public static final boolean CHECKED = false;

        public static final String _TABLENAME = "restaurant";
        public static final String _CREATE = "create table " + _TABLENAME + "("
                + _ID + " integer primary key autoincrement, "
                + NAME + " text not null, "
                + SCORE + " text not null, "
                + CHECKED + "INTEGER not null );";

    }
}
