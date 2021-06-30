package com.yun.mapapi;

/**
 * 地图监听接口
 */
public interface IMapStatusChangeListener {

    /**
     * 地图开始变动
     */
    void onMapStatusChangeStart();


    /**
     * 地图结束变动
     */
    void onMapStatusChangeFinish(Location center);

}
