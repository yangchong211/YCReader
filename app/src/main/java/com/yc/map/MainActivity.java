package com.yc.map;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    private TextView tv1;
    private TextView tv2;
    private TextView tv3;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        tv1 = findViewById(R.id.tv_1);
        tv1.setOnClickListener(this);
        tv2 = findViewById(R.id.tv_2);
        tv2.setOnClickListener(this);
        tv3 = findViewById(R.id.tv_3);
        tv3.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.tv_1:
                startActivity(new Intent(this,FlutterViewActivity.class));
                break;
            case R.id.tv_2:
                startActivity(new Intent(this,MapsActivity.class));
                break;
            case R.id.tv_3:
                startActivity(new Intent(this,GoogleActivity.class));
                break;
        }
    }
}