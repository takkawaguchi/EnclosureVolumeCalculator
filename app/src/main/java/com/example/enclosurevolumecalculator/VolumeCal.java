package com.example.enclosurevolumecalculator;

import android.widget.EditText;

// エンクロージャー容積を計算する
public class VolumeCal {
    double Qts;
    double Vas;
    double Volume;

    // コンストラクタ
    VolumeCal(EditText qts, EditText vas){
        Qts = Double.parseDouble(qts.getText().toString());
        Vas = Double.parseDouble(vas.getText().toString());
    }

    // qtsとvolからvolumeを計算し結果を返す
    public double volcal() {
        Volume =  20 * (Math.pow( Qts, 3.3 )) * Vas;
        return Volume;
    }
}
