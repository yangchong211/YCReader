package com.yc.mapgoogle;
import com.yun.mapapi.Location;
import com.yun.mapapi.IGeoCoder.IGeoCoderResult;

public class GeoCoderResult implements IGeoCoderResult {
    private String address;
    private Location location;
    private String city;
    private String cityCode;

    public GeoCoderResult(String address, Location location, String city, String cityCode) {
        this.address = address;
        this.location = location;
        this.city = city;
        this.cityCode = cityCode;
    }

    public String getAddress() {
        return this.address;
    }

    public Location getLocation() {
        return this.location;
    }

    public String getCity() {
        return this.city;
    }

    public String getCityCode() {
        return this.cityCode;
    }

    @Override
    public String getPostalCode() {
        return null;
    }
}

