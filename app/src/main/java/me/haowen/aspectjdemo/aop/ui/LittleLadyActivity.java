package me.haowen.aspectjdemo.aop.ui;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.widget.ImageView;

import com.bumptech.glide.Glide;

import me.haowen.aspectjdemo.R;


public class LittleLadyActivity extends AppCompatActivity {

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_little_lady);

        ImageView imageView = findViewById(R.id.imageView);

        Glide.with(this)
                .load("http://www.xnnews.com.cn/wenyu/lxsj/201805/W020180509487719750421.jpg")
                .into(imageView);
    }
}
