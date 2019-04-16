package me.haowen.aspectjdemo;

import android.Manifest;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

import com.blankj.utilcode.util.ToastUtils;

import me.haowen.aspectjdemo.aop.annotation.CheckPermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    @CheckPermission(permissions = {Manifest.permission.CAMERA})
    public void jump(View view) {
        ToastUtils.showShort("Ours permission grand");
    }
}