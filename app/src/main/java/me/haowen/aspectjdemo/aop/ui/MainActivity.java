package me.haowen.aspectjdemo.aop.ui;

import android.Manifest;
import android.content.Intent;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;


import me.haowen.aspectjdemo.R;
import me.haowen.aspectjdemo.aop.annotation.CheckNetwork;
import me.haowen.aspectjdemo.aop.annotation.CheckPermission;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * 网络检查
     */
    @CheckNetwork
    public void checkNetwork(View view) {
        startActivity(new Intent(this, LittleLadyActivity.class));
    }

    /**
     * 权限检查
     */
    @CheckPermission(permissions = {Manifest.permission.CAMERA})
    public void checkPermission(View view) {
        Intent intent = new Intent();
        intent.setAction(MediaStore.ACTION_IMAGE_CAPTURE);
        startActivityForResult(intent, 200);
    }

    public void durationCalculate(View view) {

    }
}