package com.yun.mapapi;

/**
 * 离线地图
 */
public interface IOfflineMapService {
    /**
     * 开始下载离线地图
     *
     * @param cityName
     */
    void startDoawLoadCityMap(String cityName);


    /**
     * 销毁
     */
    void destroy();

}
