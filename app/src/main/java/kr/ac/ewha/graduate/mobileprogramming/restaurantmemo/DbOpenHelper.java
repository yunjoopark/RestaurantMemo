package kr.ac.ewha.graduate.mobileprogramming.restaurantmemo;

import android.content.ContentValues;
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

    // 이름 검색 하기 (rawQuery)
    public Cursor getMatchName(String name){
        Cursor cursor = mDB.rawQuery( "select * from restaurant where name=" + "'" + name + "'" , null);
        return cursor;
    }

    // Insert
    public void Insert(String name, float score) {
        String resName = name;
        String resScore = Float.toString(score);
        String sql = "insert into restaurant values(null, '" + resName + "', " + resScore + ", 0);";

        mDB.execSQL(sql);
    }

    // Insert DB
//    public long Insert(String name, float score){
//        ContentValues values = new ContentValues();
//        values.put(Databases.CreateDB.NAME, name);
//        values.put(Databases.CreateDB.SCORE, Float.toString(score));
//        values.put(Databases.CreateDB.CHECKED, "0");
//        return mDB.insert(Databases.CreateDB._TABLENAME, null, values); /// table, ColumnName, Data
//    }

    // Delete
    public boolean delete(long id){
        return mDB.delete(Databases.CreateDB._TABLENAME, "_id = " + id, null) > 0;
    }

    // Delete
    public boolean delete(String name){
        return mDB.delete(Databases.CreateDB._TABLENAME, "name = " + name, null) > 0;
    }

    // Update DB
    public boolean Update(long id , String name, float score){
        ContentValues values = new ContentValues();
        values.put(Databases.CreateDB.NAME, name);
        values.put(Databases.CreateDB.SCORE, Float.toString(score));
        return mDB.update(Databases.CreateDB._TABLENAME, values, "_id=" + id, null) > 0;
    }

    // Delete All data
    public void deleteAll() {
        mDB.execSQL("delete from restaurant");
    }
}
