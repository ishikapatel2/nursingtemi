package com.example.pagedemo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.AppCompatButton;

import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;

public class MainActivity extends AppCompatActivity {
    private AppCompatButton btn_1,btn_2,btn_3,btn_4,btn_5;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        btn_1 = findViewById(R.id.btn_1);
        btn_2 = findViewById(R.id.btn_2);
        btn_3 = findViewById(R.id.btn_3);
        btn_4 = findViewById(R.id.btn_4);
        btn_5 = findViewById(R.id.btn_5);
        upDown(btn_1);upDown(btn_2);upDown(btn_3);upDown(btn_4);upDown(btn_5);
    }
    private void upDown(AppCompatButton btn){
        btn.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                if (motionEvent.getAction() == MotionEvent.ACTION_DOWN){
                    //按下设置放大比例，比1小就是缩小
                    btn.animate().scaleX(1.05f).scaleY(1.05f).setDuration(200).start();
                }
                if (motionEvent.getAction() == MotionEvent.ACTION_UP) {
                    //重置原样
                    btn.animate().scaleX(1f).scaleY(1f).setDuration(200).start();
                }
                return true;
            }
        });
    }
}