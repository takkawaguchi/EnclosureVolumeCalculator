package com.example.enclosurevolumecalculator;

import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity2 extends AppCompatActivity {
    private TestDataHelper helper;
    private SQLiteDatabase db;
    private TextView textViewVol;
    private EditText editTextName;
    private TextView textView3;
    Button button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        // ウィジェットの情報取得
        textViewVol = findViewById(R.id.textViewVol);
        editTextName = findViewById(R.id.editTextName);
        textView3 = findViewById(R.id.textView3);


        // インテントの取得,計算結果をtextViewVolに入れる
        String vol = getIntent().getStringExtra("encVol") + " L";
        textViewVol.setText(vol);

        // DB用クラス呼び出し？
        helper = new TestDataHelper(getApplicationContext());

        // DB表示
        readData();

        button = findViewById(R.id.btnSave);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(helper == null){
                    helper = new TestDataHelper(getApplicationContext());
                }

                if(db == null){
                    db = helper.getWritableDatabase();
                }

                // DBにデータを追加
                //insertData(db, editTextName.getText().toString(), textViewVol.getText().toString());
                helper.saveData(db, editTextName.getText().toString(), textViewVol.getText().toString());
                readData();
            }
        });
    }

    // データの読み出し
    private void readData(){
        if(helper == null){
            helper = new TestDataHelper(getApplicationContext());
        }

        if(db == null){
            db = helper.getReadableDatabase();
        }
        Log.d("debug","**********Cursor");

        Cursor cursor = db.query(
                "test",
                new String[] { "name", "encVol" },
                null,
                null,
                null,
                null,
                null
        );

        cursor.moveToFirst();

        StringBuilder sbuilder = new StringBuilder();

        for (int i = 0; i < cursor.getCount(); i++) {
            sbuilder.append(cursor.getString(0));
            sbuilder.append(": ");
            sbuilder.append(cursor.getString(1));
            sbuilder.append("\n");
            cursor.moveToNext();
        }

        // 忘れずに！
        cursor.close();

        Log.d("debug","**********"+sbuilder);
        textView3.setText(sbuilder.toString());
    }


}