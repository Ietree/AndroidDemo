package com.ietree.animationdrawable;

import android.graphics.drawable.AnimationDrawable;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {

    private ImageView iv;
    private AnimationDrawable ivBackground;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        iv = findViewById(R.id.iv);
        // 给图片设置背景
        iv.setBackgroundResource(R.drawable.logo);

        // 获得设置的背景
        ivBackground = (AnimationDrawable) iv.getBackground();
        ivBackground.start();
    }
}
