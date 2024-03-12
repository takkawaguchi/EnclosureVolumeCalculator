package com.example.enclosurevolumecalculator;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

// データベース用クラス
public class TestDataHelper extends SQLiteOpenHelper {
    static final private String DBNAME = "test.sqlite";
    static final private int VERSION = 1;

    public TestDataHelper(Context context) {

        super(context, DBNAME, null, VERSION);
    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        if (db != null) {
            db.execSQL("CREATE TABLE test (_id INTEGER PRIMARY KEY, name TEXT, encVol TEXT)");
            // 初回はテスト用データを入れてみる
            saveData(db, "TestSub", "88.8 L");
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        if (db != null) {
            db.execSQL("DROP TABLE IF EXISTS test");
            onCreate(db);
        }
    }

    public void onOpen(SQLiteDatabase db) {
        super.onOpen(db);
    }

    // DBにデータを追加する
    public void saveData(SQLiteDatabase db, String name, String encVol) {
        ContentValues values = new ContentValues();
        values.put("name", name);
        values.put("encVol", encVol);
        db.insert("test", null, values);
    }

}
