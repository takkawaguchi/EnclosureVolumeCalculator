package com.example.enclosurevolumecalculator;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @SuppressLint("DefaultLocale")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        EditText qts = findViewById(R.id.editTextQts);
        EditText vas = findViewById(R.id.editTextVas);
        TextView vol = findViewById(R.id.textView);
        Button button2 = findViewById(R.id.button2);
        Button buttonSave = findViewById(R.id.buttonSave);

        // button2を押したら計算結果を表示する
        button2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                try {
                    VolumeCal volc = new VolumeCal(qts, vas);
                    vol.setText(String.format("%.3f", volc.volcal()));
                } catch (Exception e) {
                    vol.setText("E");
                }
            }
        });


        // 保存ボタンを押したら画面遷移、計算結果を渡す
        buttonSave.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(MainActivity.this, MainActivity2.class);
                i.putExtra("encVol", vol.getText().toString());
                startActivity(i);
            }
        });
    }

    // Menuを生成
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.option_menu, menu);
        return true;
    }

    // Menu選択時
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        Intent i = new Intent(MainActivity.this, MainActivity3.class);
        startActivity(i);
        return true;
    }
}