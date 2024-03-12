package com.example.enclosurevolumecalculator;
// データ表示用画面

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity3 extends AppCompatActivity {
    ListView listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main3);

        listView = findViewById(R.id.listView1);

        TestDataHelper testDataHelper = new TestDataHelper(this);
        SQLiteDatabase db = testDataHelper.getWritableDatabase();

        // DBのデータを取得し、listviewを使って表示
        Cursor c = db.rawQuery("SELECT * from test", null);
        String[] from = { "name", "encVol"};
        int[] to = {R.id.textView2, R.id.textView5};

        SimpleCursorAdapter adapter = new SimpleCursorAdapter(this, R.layout.content,c,from,to,0);

        listView.setAdapter(adapter);
        adapter.notifyDataSetChanged();

    }
}