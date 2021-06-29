package com.yc.map;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author yc
 */
public class FlutterViewActivity extends AppCompatActivity {

    private FrameLayout rlFlutter;
    private TextView tvTitle;
    private static PublishSubject<Integer> mPublish = PublishSubject.create();
    private Disposable subscribe;
    private int i = 0;

    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_flutter_view);
        rlFlutter = findViewById(R.id.rl_flutter);
        tvTitle = findViewById(R.id.tv_title);

        subscribe = mPublish.observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Consumer<Integer>() {
                    @SuppressLint("SetTextI18n")
                    @Override
                    public void accept(Integer integer) throws Exception {
                        String title = tvTitle.getText().toString().trim();
                        tvTitle.setText(title+"---"+integer);
                    }
                });
        tvTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                mPublish.onNext(i++);
            }
        });
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (subscribe!=null){
            boolean disposed = subscribe.isDisposed();
            if (!disposed){
                subscribe.dispose();
            }
        }
    }

}
