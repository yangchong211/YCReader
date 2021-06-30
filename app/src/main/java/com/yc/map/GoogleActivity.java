package com.yc.map;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.os.Bundle;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.yun.googlemap.MyGoogleMap;
import com.yun.map.IDistrictSearch;
import com.yun.map.IGeoCoder;
import com.yun.map.ILocationService;
import com.yun.map.IMap;
import com.yun.map.IMapStatusChangeListener;
import com.yun.map.IOfflineMapService;
import com.yun.map.IOnMapLoadedCallback;
import com.yun.map.IOverlay;
import com.yun.map.IRoutePlanManager;
import com.yun.map.IRoutePlanSearch;
import com.yun.map.ISuggestionSearch;
import com.yun.map.Location;
import com.yun.map.ShowInfoWindowData;

import java.util.List;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.disposables.Disposable;
import io.reactivex.functions.Consumer;
import io.reactivex.subjects.PublishSubject;

/**
 * @author yc
 */
public class GoogleActivity extends AppCompatActivity {

    private FrameLayout rlContent;
    private MyGoogleMap myGoogleMap;
    private ILocationService iLocationService;


    @SuppressLint("CheckResult")
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_google_view);
        rlContent = findViewById(R.id.rl_content);


        myGoogleMap = new MyGoogleMap(this,
                0, "AIzaSyDfN8fhJA-MDkjaLD37-63mA0CXtCZwXjc");
        //启动定位服务
        iLocationService = myGoogleMap.getLocationService();
        iLocationService.registerLocationListener(locationListener);
        iLocationService.start();

    }

    private final ILocationService.LocationListener locationListener =
            new ILocationService.LocationListener() {
        @Override
        public void onReceiveLocation(ILocationService.LocationResult locationResult) {
            if (locationResult != null) {
                Location location = locationResult.getLocation();
                //创建地图View
                View mapView = myGoogleMap.createMapView(
                        GoogleActivity.this, location, 1);
                myGoogleMap.setShowLocation(false);
                rlContent.addView(mapView);
            }
        }
    };

    @Override
    protected void onDestroy() {
        super.onDestroy();
        iLocationService.unRegisterLocationListener(locationListener);
    }

}
