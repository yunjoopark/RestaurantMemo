package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.provider.BaseColumns;

/**
 * Created by yunjoo on 2015. 12. 13..
 */
public final class Databases {
    public static final class CreateDB implements BaseColumns {
        public static final String NAME = "name";
        public static final String SCORE = "score";
        public static final String CHECKED = "checked";

        public static final String _TABLENAME = "restaurant";
        public static final String _CREATE = "create table " + _TABLENAME + "("
                + _ID + " integer primary key autoincrement, "
                + NAME + " text not null, "
                + SCORE + " text not null, "
                + CHECKED + " integer not null );";

    }
}
