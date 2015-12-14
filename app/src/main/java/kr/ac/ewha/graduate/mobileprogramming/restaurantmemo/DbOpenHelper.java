package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by yunjoo on 2015. 12. 14..
 */
public class DbOpenHelper {
    private static final String DATABASE_NAME = "restaurantlist.db";
    private static final int DATABASE_VERSION = 1;
    public static SQLiteDatabase mDB;
    private DatabaseHelper mDBHelper;
    private Context mContext;

    private class DatabaseHelper extends SQLiteOpenHelper {

        // 생성자
        public DatabaseHelper(Context context, String name,
                              SQLiteDatabase.CursorFactory factory, int version) {
            super(context, name, factory, version);
        }

        // 최초 DB를 만들때 한번만 호출된다.
        @Override
        public void onCreate(SQLiteDatabase db) {
            db.execSQL(Databases.CreateDB._CREATE);

        }

        // 버전이 업데이트 되었을 경우 DB를 다시 만들어 준다.
        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            db.execSQL("DROP TABLE IF EXISTS " + Databases.CreateDB._TABLENAME);
            onCreate(db);
        }
    }
    public DbOpenHelper(Context context){
        this.mContext = context;
    }

    public DbOpenHelper open() throws SQLException {
        mDBHelper = new DatabaseHelper(mContext, DATABASE_NAME, null, DATABASE_VERSION);
        mDB = mDBHelper.getWritableDatabase();
        return this;
    }

    public void close(){
        mDB.close();
    }

    public Cursor getAllColumns() {
        Cursor cursor = mDB.rawQuery("select * from restaurant", null);
        return cursor;
    }

    public void insertColumn(String name, float score) {
        String resName = name;
        String resScore = Float.toString(score);
        String sql = "insert into restaurant values(null, '" + resName + "', " + resScore + ", 0);";

        mDB.execSQL(sql);
    }
}
