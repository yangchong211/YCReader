package com.yun.mapapi;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Point;
import android.view.View;

import java.util.List;

/**
 * 地图抽象接口
 */
public interface IMap {
    /**
     * 创建地图view
     *
     * @param context
     * @param location
     * @param zoom
     * @return
     */
    View createMapView(Context context, Location location, int zoom);

    /**
     * 创建地图
     *
     * @param context
     * @param iOnMapLoadedCallback
     * @param location
     * @param zoom
     * @return
     */
    View createMapView(Context context, IOnMapLoadedCallback iOnMapLoadedCallback, Location location, int zoom);

    /**
     * 生命周期
     */
    void onResume();

    /**
     * 生命周期
     */
    void onPause();

    /**
     * 生命周期
     */
    void onDestroy();


    /**
     * 增加覆盖物
     *
     * @param location
     * @param icon
     */
    IOverlay addOverlay(Location location, Bitmap icon);

    /**
     * 增加覆盖物
     *
     * @param location
     * @param resId
     * @return
     */
    IOverlay addOverlay(Location location, int resId);


    /**
     * 增加覆盖物
     *
     * @param location
     * @param view
     * @return
     */
    IOverlay addOverlay(Location location, View view);

    /**
     * 设置地图上选中的位置
     *
     * @param location
     */
    void setLocationData(Location location);

    /**
     * 设置地图上选中的位置
     *
     * @param location
     */
    void setLocationData(Location location, int zoom);

    /**
     * 设置地图上选中的位置
     * 并刷新地图
     *
     * @param location
     * @param zoom
     */
    void setLocationDataAndMapStatus(Location location, int zoom);


    /**
     * 设置地图状态监听
     *
     * @param iMapStatusChangeListener
     */
    void setOnMapStatusChangeListener(IMapStatusChangeListener iMapStatusChangeListener);


    /**
     * 获取路线搜索
     *
     * @return
     */
    IRoutePlanSearch getRoutePlanSearch(IRoutePlanSearch.IRouteLineStyle iRouteLineStyle);


    /**
     * 获取定位服务
     *
     * @return
     */
    ILocationService getLocationService();


    /**
     * 绘制路线
     *
     * @param locations
     */
    void drawRoute(List<Location> locations, IRoutePlanSearch.IRouteLineStyle iRouteLineStyle);


    /**
     * 添加覆盖物
     *
     * @param showInfoWindowDataList
     */
    void showInfoWindow(List<ShowInfoWindowData> showInfoWindowDataList);

    /**
     * 添加覆盖物
     *
     * @param showInfoWindowData
     */
    void showInfoWindow(ShowInfoWindowData showInfoWindowData);

    /**
     * 地图动画
     *
     * @param locations
     * @param time
     * @param width
     * @param height
     */
    void animateMapStatus(List<Location> locations, int time, int width, int height);


    /**
     * 地图动画
     *
     * @param locations
     * @param time
     * @param var1
     * @param var2
     * @param var3
     * @param var4
     */
    void animateMapStatus(List<Location> locations, int time, int var1, int var2, int var3, int var4);

    /**
     * 隐藏所有添加的window
     */
    void hideAllInfoWidow();


    /**
     * 获取屏幕上点的位置
     *
     * @param point
     * @return
     */
    Location getFromScreenLocation(Point point);


    /**
     * 获取反地理编码
     *
     * @return
     */
    IGeoCoder getGeoCoder();


    /**
     * 清除所有数据
     */
    void clearAllData();


    /**
     * 从坐标获取位置
     *
     * @param location
     * @return
     */
    Point toScreenLocation(Location location);


    /**
     * 绘制路线
     *
     * @param locations
     */
    void drawTask(List<Location> locations, IRoutePlanSearch.IRouteLineStyle iRouteLineStyle);


    /**
     * 获取两点间的距离
     *
     * @param startLocation
     * @param endLocation
     */
    double getDistance(Location startLocation, Location endLocation);


    /**
     * 是否显示当前位置
     */
    void setMyLocationEnabled(boolean isEnable);


    /**
     * 创建view layoutparams
     *
     * @param height
     * @param width
     * @param yOffset
     * @param location
     * @param view
     * @return
     */
    void addView(int height, int width, int yOffset, Location location, View view);


    /**
     * 获取位置搜索
     *
     * @return
     */
    ISuggestionSearch getSuggestionSearch();


    /**
     * 获取行政区划搜索
     *
     * @return
     */
    IDistrictSearch getDistrictSearch();

    /**
     * 获取离线地图服务
     *
     * @return
     */
    IOfflineMapService getOfflineMapService();


    /**
     * 设置是否显示个性化地图
     *
     * @param isMapCustomEnable
     */
    void setMapCustomEnable(boolean isMapCustomEnable);


    /**
     * 获取导航
     *
     * @return
     */
    IRoutePlanManager getRoutePlanManager();

    /**
     * 是否显示坐标点
     *
     * @param isShow
     */
    void setShowLocation(boolean isShow);


    /**
     * 设置离线地图路径
     *
     * @param path
     */
    void setCustomMapStylePath(String path);


    /**
     * 加载必要资源
     * 初始化sdk
     */
    void initMapSdk();


    /**
     * 是否能滑动
     *
     * @param canGesture
     */
    void setGesturesEnabled(boolean canGesture);


    /**
     * 绘制附近上车点
     *
     * @param showInfoWindowDataList
     */
    void drawNearPoint(List<ShowInfoWindowData> showInfoWindowDataList);

    /**
     * 清空附近上车点
     *
     * @param showInfoWindowDataList
     */
    void clearNearPoint(List<ShowInfoWindowData> showInfoWindowDataList);
}
