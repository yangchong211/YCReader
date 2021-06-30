//
//package com.yc.mapgoogle;
//
//import android.app.Application;
//import android.content.Context;
//import android.content.Intent;
//import android.content.pm.PackageInfo;
//import android.content.pm.PackageManager;
//import android.graphics.Bitmap;
//import android.graphics.Point;
//import android.graphics.Bitmap.Config;
//import android.net.Uri;
//import android.os.Bundle;
//import android.os.Handler;
//import android.view.View;
//import android.view.ViewGroup;
//import android.view.View.MeasureSpec;
//import android.widget.FrameLayout;
//import android.widget.TextView;
//import android.widget.Toast;
//import android.widget.FrameLayout.LayoutParams;
//import com.google.android.gms.maps.CameraUpdate;
//import com.google.android.gms.maps.CameraUpdateFactory;
//import com.google.android.gms.maps.GoogleMap;
//import com.google.android.gms.maps.MapView;
//import com.google.android.gms.maps.OnMapReadyCallback;
//import com.google.android.gms.maps.GoogleMap.CancelableCallback;
//import com.google.android.gms.maps.GoogleMap.OnCameraChangeListener;
//import com.google.android.gms.maps.GoogleMap.OnCameraMoveListener;
//import com.google.android.gms.maps.model.BitmapDescriptor;
//import com.google.android.gms.maps.model.BitmapDescriptorFactory;
//import com.google.android.gms.maps.model.CameraPosition;
//import com.google.android.gms.maps.model.LatLng;
//import com.google.android.gms.maps.model.LatLngBounds;
//import com.google.android.gms.maps.model.Marker;
//import com.google.android.gms.maps.model.MarkerOptions;
//import com.google.android.gms.maps.model.Polyline;
//import com.google.android.gms.maps.model.PolylineOptions;
//import com.yun.api.ApiConfig;
//import com.yun.api.ApiConfig.Builder;
//import com.yun.map.IDistrictSearch;
//import com.yun.map.IGeoCoder;
//import com.yun.map.ILocationService;
//import com.yun.map.IMap;
//import com.yun.map.IMapStatusChangeListener;
//import com.yun.map.IOfflineMapService;
//import com.yun.map.IOnMapLoadedCallback;
//import com.yun.map.IOverlay;
//import com.yun.map.IRoutePlanManager;
//import com.yun.map.IRoutePlanSearch;
//import com.yun.map.ISuggestionSearch;
//import com.yun.map.Location;
//import com.yun.map.ShowInfoWindowData;
//import com.yun.map.IRoutePlanSearch.IRouteLineStyle;
//import java.util.ArrayList;
//import java.util.Iterator;
//import java.util.List;
//
//public class MyGoogleMap implements IMap, OnCameraMoveListener, OnCameraChangeListener {
//    private MapView mapView;
//    private GoogleMap googleMap;
//    private IOnMapLoadedCallback iOnMapLoadedCallback;
//    private Context context;
//    private int zoom;
//    private int index = 1;
//    private List<Marker> markers = new ArrayList();
//    private List<IMapStatusChangeListener> iMapStatusChangeListeners;
//    private int mapPadding;
//    private List<View> viewList = new ArrayList();
//    private List<Marker> nearPoint = new ArrayList();
//    private List<Polyline> polylines = new ArrayList();
//    private GoogleRoutePlanSearch googleRoutePlanSearch;
//    private ApiServiceImpl apiService;
//    private MyGeoCoder myGeoCoder;
//    private GoogleSuggestionSearch googleSuggestionSearch;
//
//    public MyGoogleMap(Context context, int mapPadding, String key) {
//        this.context = context;
//        this.mapPadding = mapPadding;
//        Constant.getInstance().setKey(key, context);
//        this.apiService = new ApiServiceImpl();
//    }
//
//    public View createMapView(Context context, final Location location, final int zoom) {
//        this.zoom = zoom;
//        if (this.iMapStatusChangeListeners != null) {
//            this.iMapStatusChangeListeners.clear();
//        }
//
//        this.viewList.clear();
//        this.mapView = new MapView(context);
//        this.mapView.onCreate(new Bundle());
//        this.mapView.getMapAsync(new OnMapReadyCallback() {
//            public void onMapReady(GoogleMap map) {
//                MyGoogleMap.this.googleMap = map;
//                MyGoogleMap.this.googleMap.setIndoorEnabled(false);
//                MyGoogleMap.this.googleMap.getUiSettings().setCompassEnabled(false);
//                MyGoogleMap.this.googleMap.getUiSettings().setRotateGesturesEnabled(false);
//                MyGoogleMap.this.googleMap.setMyLocationEnabled(true);
//                MyGoogleMap.this.googleMap.getUiSettings().setMyLocationButtonEnabled(false);
//                MyGoogleMap.this.googleMap.setOnCameraMoveListener(MyGoogleMap.this);
//                MyGoogleMap.this.googleMap.setOnCameraChangeListener(MyGoogleMap.this);
//                if (MyGoogleMap.this.iOnMapLoadedCallback != null) {
//                    MyGoogleMap.this.iOnMapLoadedCallback.onLoad();
//                }
//
//                CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()), (float)zoom - 2.5F);
//                MyGoogleMap.this.googleMap.moveCamera(cameraPosition);
//                if (MyGoogleMap.this.googleRoutePlanSearch != null) {
//                    MyGoogleMap.this.googleRoutePlanSearch.setGoogleMap(MyGoogleMap.this.googleMap);
//                    MyGoogleMap.this.googleRoutePlanSearch.setMapView(MyGoogleMap.this.mapView);
//                }
//
//            }
//        });
//        this.context = context;
//        ApiConfig.init((new Builder()).readTimeout(30000L).writeTimeout(30000L).baseUrl("https://maps.googleapis.com/").retryOnConnectionFailure(false).build());
//        return this.mapView;
//    }
//
//    public View createMapView(Context context, IOnMapLoadedCallback iOnMapLoadedCallback, Location location, int zoom) {
//        this.iOnMapLoadedCallback = iOnMapLoadedCallback;
//        return this.createMapView(context, location, zoom);
//    }
//
//    public void onResume() {
//        if (this.mapView != null) {
//            this.mapView.onResume();
//        }
//
//    }
//
//    public void onPause() {
//        if (this.mapView != null) {
//            this.mapView.onPause();
//        }
//
//    }
//
//    public void onDestroy() {
//        this.index = 0;
//        if (this.mapView != null) {
//            this.hideAllInfoWidow();
//            Iterator var1 = this.polylines.iterator();
//
//            while(var1.hasNext()) {
//                Polyline polyline = (Polyline)var1.next();
//                polyline.remove();
//            }
//
//            this.clearNearPoint((List)null);
//            this.nearPoint.clear();
//            this.markers.clear();
//            this.polylines.clear();
//            this.mapView.removeAllViews();
//            this.mapView.onDestroy();
//            this.googleMap = null;
//            this.mapView = null;
//        }
//
//        if (this.googleRoutePlanSearch != null) {
//            this.googleRoutePlanSearch.clearRoute();
//        }
//
//    }
//
//    public IOverlay addOverlay(Location location, Bitmap icon) {
//        if (this.mapView != null) {
//            MarkerOptions option = null;
//            if (location.getDirection() > 0.0F) {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).rotation(location.getDirection()).icon(BitmapDescriptorFactory.fromBitmap(icon));
//            } else {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).icon(BitmapDescriptorFactory.fromBitmap(icon));
//            }
//
//            if (this.googleMap != null) {
//                Marker overlay = this.googleMap.addMarker(option);
//                return new MyOverlay(overlay);
//            }
//        }
//
//        return null;
//    }
//
//    public IOverlay addOverlay(Location location, int resId) {
//        if (this.mapView != null) {
//            MarkerOptions option = null;
//            if (location.getDirection() > 0.0F) {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).rotation(location.getDirection()).icon(BitmapDescriptorFactory.fromResource(resId));
//            } else {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).icon(BitmapDescriptorFactory.fromResource(resId));
//            }
//
//            if (this.googleMap != null) {
//                Marker overlay = this.googleMap.addMarker(option);
//                return new MyOverlay(overlay);
//            }
//        }
//
//        return null;
//    }
//
//    public IOverlay addOverlay(Location location, View view) {
//        if (this.mapView != null) {
//            MarkerOptions option = null;
//            if (location.getDirection() > 0.0F) {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).rotation(location.getDirection()).icon(this.fromView(view));
//            } else {
//                option = (new MarkerOptions()).position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())).icon(this.fromView(view));
//            }
//
//            if (this.googleMap != null) {
//                Marker overlay = this.googleMap.addMarker(option);
//                return new MyOverlay(overlay);
//            }
//        }
//
//        return null;
//    }
//
//    public void setLocationData(Location location) {
//        if (this.googleMap != null) {
//            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()), (float)this.zoom - 2.5F);
//            this.googleMap.moveCamera(cameraPosition);
//        }
//
//    }
//
//    public void setLocationData(Location location, int zoom) {
//        if (this.googleMap != null) {
//            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngZoom(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()), (float)zoom - 2.5F);
//            this.googleMap.moveCamera(cameraPosition);
//        }
//
//    }
//
//    public void setLocationDataAndMapStatus(Location location, int zoom) {
//        if (this.googleMap != null) {
//            CameraUpdate cameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()), (float)zoom - 2.5F, location.getDirection() % 90.0F, 0.0F));
//            this.googleMap.moveCamera(cameraPosition);
//        }
//
//    }
//
//    public void setOnMapStatusChangeListener(IMapStatusChangeListener iMapStatusChangeListener) {
//        if (this.iMapStatusChangeListeners == null) {
//            this.iMapStatusChangeListeners = new ArrayList();
//        }
//
//        this.iMapStatusChangeListeners.add(iMapStatusChangeListener);
//    }
//
//    public IRoutePlanSearch getRoutePlanSearch(IRouteLineStyle iRouteLineStyle) {
//        if (this.googleRoutePlanSearch == null) {
//            this.googleRoutePlanSearch = new GoogleRoutePlanSearch(this.context, this.googleMap, iRouteLineStyle, this.mapPadding, this.mapView, this.apiService);
//        }
//
//        return this.googleRoutePlanSearch;
//    }
//
//    public ILocationService getLocationService() {
//        return new LocationService(this.context, this.apiService);
//    }
//
//    public void drawRoute(List<Location> locations, IRouteLineStyle iRouteLineStyle) {
//        if (this.googleMap != null) {
//            List<LatLng> latLngs = new ArrayList();
//            Iterator var4 = locations.iterator();
//
//            while(var4.hasNext()) {
//                Location location = (Location)var4.next();
//                latLngs.add(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()));
//            }
//
//            Polyline polyline = this.googleMap.addPolyline((new PolylineOptions()).width((float)iRouteLineStyle.getLineWidth()).visible(true).addAll(latLngs));
//            polyline.setColor(iRouteLineStyle.getLineColor());
//            this.polylines.add(polyline);
//        }
//    }
//
//    public void showInfoWindow(List<ShowInfoWindowData> showInfoWindowDataList) {
//        if (this.googleMap != null) {
//            synchronized(this.googleMap) {
//                Iterator var3 = showInfoWindowDataList.iterator();
//
//                while(var3.hasNext()) {
//                    ShowInfoWindowData showInfoWindowData = (ShowInfoWindowData)var3.next();
//                    Location location = showInfoWindowData.getLocation();
//                    if (showInfoWindowData.getHeight() != 0) {
//                        Point point = this.toScreenLocation(location);
//                        point.set(point.x, point.y + showInfoWindowData.getHeight());
//                        location = this.getFromScreenLocation(point);
//                    }
//
//                    MarkerOptions markerOption = new MarkerOptions();
//                    markerOption.position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()));
//                    markerOption.icon(this.fromView(showInfoWindowData.getView()));
//                    Marker marker = this.googleMap.addMarker(markerOption);
//                    marker.setZIndex((float)this.index);
//                    ++this.index;
//                    marker.setTag(showInfoWindowData);
//                    this.removeOldInfoWindow(showInfoWindowData);
//                    this.markers.add(marker);
//                }
//            }
//        }
//
//    }
//
//    public void showInfoWindow(ShowInfoWindowData showInfoWindowData) {
//        if (this.googleMap != null) {
//            synchronized(this.googleMap) {
//                Location location = showInfoWindowData.getLocation();
//                if (showInfoWindowData.getHeight() != 0) {
//                    Point point = this.toScreenLocation(location);
//                    point.set(point.x, point.y + showInfoWindowData.getHeight());
//                    location = this.getFromScreenLocation(point);
//                }
//
//                MarkerOptions markerOption = new MarkerOptions();
//                markerOption.position(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()));
//                markerOption.icon(this.fromView(showInfoWindowData.getView()));
//                Marker marker = this.googleMap.addMarker(markerOption);
//                marker.setTag(showInfoWindowData);
//                marker.setZIndex((float)this.index);
//                ++this.index;
//                this.removeOldInfoWindow(showInfoWindowData);
//                this.markers.add(marker);
//            }
//        }
//
//    }
//
//    public void animateMapStatus(List<Location> locations, int time, int width, int height) {
//        if (this.googleMap != null) {
//            if (locations.size() > 1) {
//                com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
//
//                Location location;
//                for(Iterator var6 = locations.iterator(); var6.hasNext(); builder = builder.include(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()))) {
//                    location = (Location)var6.next();
//                }
//
//                LatLngBounds bounds = builder.build();
//                CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngBounds(bounds, width, height, this.mapPadding);
//                this.googleMap.animateCamera(cameraPosition, time, (CancelableCallback)null);
//            } else if (locations.size() == 1) {
//                CameraUpdate cameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(((Location)locations.get(0)).getGoogleLatitude(), ((Location)locations.get(0)).getGoogleLongitude()), (float)this.zoom, ((Location)locations.get(0)).getDirection() % 90.0F, 0.0F));
//                this.googleMap.animateCamera(cameraPosition);
//            }
//        }
//
//    }
//
//    public void animateMapStatus(List<Location> locations, int time, int var1, int var2, int var3, int var4) {
//        if (this.googleMap != null) {
//            if (locations.size() > 1) {
//                com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
//
//                Location location;
//                for(Iterator var8 = locations.iterator(); var8.hasNext(); builder = builder.include(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()))) {
//                    location = (Location)var8.next();
//                }
//
//                LatLngBounds bounds = builder.build();
//                CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngBounds(bounds, var1);
//                this.googleMap.animateCamera(cameraPosition, time, (CancelableCallback)null);
//            } else if (locations.size() == 1) {
//                CameraUpdate cameraPosition = CameraUpdateFactory.newCameraPosition(new CameraPosition(new LatLng(((Location)locations.get(0)).getGoogleLatitude(), ((Location)locations.get(0)).getGoogleLongitude()), (float)this.zoom, ((Location)locations.get(0)).getDirection() % 90.0F, 0.0F));
//                this.googleMap.animateCamera(cameraPosition);
//            }
//
//        }
//    }
//
//    public void hideAllInfoWidow() {
//        if (this.googleMap != null) {
//            Iterator var1 = this.markers.iterator();
//
//            while(var1.hasNext()) {
//                Marker marker = (Marker)var1.next();
//                marker.remove();
//                if (marker.getTag() != null) {
//                    ShowInfoWindowData itemData = (ShowInfoWindowData)marker.getTag();
//                    if (itemData.getView().getParent() != null) {
//                        ViewGroup viewGroup = (ViewGroup)itemData.getView().getParent();
//                        viewGroup.removeView(itemData.getView());
//                    }
//                }
//            }
//
//            this.markers.clear();
//        }
//
//    }
//
//    public Location getFromScreenLocation(Point point) {
//        if (this.googleMap != null && this.googleMap.getProjection() != null) {
//            LatLng latLng = this.googleMap.getProjection().fromScreenLocation(point);
//            Location location = new Location(0.0D, 0.0D);
//            location.setGoogleLatitude(latLng.latitude);
//            location.setGoogleLongitude(latLng.longitude);
//            return location;
//        } else {
//            return null;
//        }
//    }
//
//    public IGeoCoder getGeoCoder() {
//        if (this.myGeoCoder == null) {
//            this.myGeoCoder = new MyGeoCoder(this.apiService, this.context);
//        }
//
//        return this.myGeoCoder;
//    }
//
//    public void clearAllData() {
//        this.onDestroy();
//    }
//
//    public Point toScreenLocation(Location location) {
//        return this.googleMap != null && this.googleMap.getProjection() != null ? this.googleMap.getProjection().toScreenLocation(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude())) : null;
//    }
//
//    public void drawTask(List<Location> locations, IRouteLineStyle iRouteLineStyle) {
//        if (this.googleMap != null) {
//            Iterator var3 = this.polylines.iterator();
//
//            while(var3.hasNext()) {
//                Polyline polyline = (Polyline)var3.next();
//                polyline.remove();
//            }
//
//            this.polylines.clear();
//            ArrayList<LatLng> latLngs = new ArrayList();
//            Iterator var7 = locations.iterator();
//
//            while(var7.hasNext()) {
//                Location location = (Location)var7.next();
//                latLngs.add(new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude()));
//            }
//
//            PolylineOptions mPolylineOptions = new PolylineOptions();
//            mPolylineOptions.geodesic(false);
//            mPolylineOptions.visible(true);
//            mPolylineOptions.width(15.0F);
//            mPolylineOptions.color(iRouteLineStyle.getLineColor());
//            this.googleMap.addPolyline(mPolylineOptions);
//            ++this.index;
//            mPolylineOptions.zIndex((float)this.index);
//            this.zoomToSpan(latLngs);
//        }
//
//    }
//
//    public double getDistance(Location startLocation, Location endLocation) {
//        float[] results = new float[3];
//        android.location.Location.distanceBetween(startLocation.getGoogleLatitude(), startLocation.getGoogleLongitude(), endLocation.getGoogleLatitude(), endLocation.getGoogleLongitude(), results);
//        return (double)results[0];
//    }
//
//    public void setMyLocationEnabled(boolean isEnable) {
//    }
//
//    public void addView(int height, int width, int yOffset, Location location, View view) {
//        if (this.mapView != null) {
//            Point point = this.toScreenLocation(location);
//            int left = (this.mapView.getWidth() == 0 ? 500 : this.mapView.getWidth()) - width / 2 - point.x;
//            int top = (this.mapView.getHeight() == 0 ? 1000 : this.mapView.getHeight()) - height / 2 - point.y;
//            LayoutParams layoutParams = new LayoutParams(width, height);
//            layoutParams.setMargins(left, top, 0, 0);
//            this.mapView.addView(view, layoutParams);
//            view.layout(left, top, left + width, top + height);
//            view.setTag(location);
//            this.viewList.add(view);
//            this.mapView.postInvalidate();
//            this.googleMap.getUiSettings().setTiltGesturesEnabled(false);
//            this.googleMap.getUiSettings().setZoomControlsEnabled(false);
//            this.googleMap.getUiSettings().setZoomGesturesEnabled(false);
//            this.googleMap.getUiSettings().setScrollGesturesEnabled(false);
//        }
//
//    }
//
//    public ISuggestionSearch getSuggestionSearch() {
//        if (this.googleSuggestionSearch == null) {
//            this.googleSuggestionSearch = new GoogleSuggestionSearch(this.apiService, this.context);
//        }
//
//        return this.googleSuggestionSearch;
//    }
//
//    public IDistrictSearch getDistrictSearch() {
//        return new IDistrictSearch() {
//            public void setOnDistrictSearchListener(IOnGetDistrictSearchResultListener iOnGetDistrictSearchResultListener) {
//            }
//
//            public void searchDistrict(String city) {
//            }
//        };
//    }
//
//    public IOfflineMapService getOfflineMapService() {
//        return new IOfflineMapService() {
//            public void startDoawLoadCityMap(String cityName) {
//            }
//
//            public void destroy() {
//            }
//        };
//    }
//
//    public void setMapCustomEnable(boolean isMapCustomEnable) {
//    }
//
//    public IRoutePlanManager getRoutePlanManager() {
//        return new IRoutePlanManager() {
//            public void routeplanToNavi(List<Location> locations, Handler handler, int tactics) {
//                Uri gmmIntentUri;
//                Intent mapIntent;
//                if (MyGoogleMap.isInstall(MyGoogleMap.this.context, "com.google.android.apps.maps")) {
//                    gmmIntentUri = Uri.parse("google.navigation:q=" + ((Location)locations.get(1)).getGoogleLatitude() + "," + ((Location)locations.get(1)).getGoogleLongitude() + ", + " + ((Location)locations.get(1)).getAddrStr());
//                    mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
//                    mapIntent.setPackage("com.google.android.apps.maps");
//                    MyGoogleMap.this.context.startActivity(mapIntent);
//                } else {
//                    Toast.makeText(MyGoogleMap.this.context, "您尚未安装谷歌地图", 1).show();
//                    gmmIntentUri = Uri.parse("market://details?id=com.google.android.apps.maps");
//                    mapIntent = new Intent("android.intent.action.VIEW", gmmIntentUri);
//                    MyGoogleMap.this.context.startActivity(mapIntent);
//                }
//
//            }
//
//            public void init(Application context, LoadCallBack loadCallBack) {
//            }
//
//            public void changeRoutePlan(List<Location> locations, int tactics) {
//            }
//        };
//    }
//
//    public static boolean isInstall(Context context, String packageName) {
//        PackageManager packageManager = context.getPackageManager();
//        List<PackageInfo> packageInfos = packageManager.getInstalledPackages(0);
//        List<String> packageNames = new ArrayList();
//        if (packageInfos != null) {
//            for(int i = 0; i < packageInfos.size(); ++i) {
//                String packName = ((PackageInfo)packageInfos.get(i)).packageName;
//                packageNames.add(packName);
//            }
//        }
//
//        return packageNames.contains(packageName);
//    }
//
//    public void setShowLocation(boolean isShow) {
//        if (this.googleMap != null) {
//            this.googleMap.setMyLocationEnabled(isShow);
//        }
//
//    }
//
//    public void setCustomMapStylePath(String path) {
//    }
//
//    public void initMapSdk() {
//    }
//
//    public void setGesturesEnabled(boolean canGesture) {
//        if (this.googleMap != null) {
//            this.googleMap.getUiSettings().setZoomGesturesEnabled(canGesture);
//            this.googleMap.getUiSettings().setScrollGesturesEnabled(canGesture);
//        }
//
//    }
//
//    public void drawNearPoint(List<ShowInfoWindowData> showInfoWindowDataList) {
//        if (this.googleMap != null) {
//            Iterator var2 = showInfoWindowDataList.iterator();
//
//            while(var2.hasNext()) {
//                ShowInfoWindowData showInfoWindowData = (ShowInfoWindowData)var2.next();
//                MarkerOptions markerOption = new MarkerOptions();
//                markerOption.position(new LatLng(showInfoWindowData.getLocation().getGoogleLatitude(), showInfoWindowData.getLocation().getGoogleLongitude()));
//                markerOption.icon(this.fromView(showInfoWindowData.getView()));
//                Marker marker = this.googleMap.addMarker(markerOption);
//                marker.setRotation(showInfoWindowData.getLocation().getDirection());
//                this.nearPoint.add(marker);
//            }
//        }
//
//    }
//
//    public void clearNearPoint(List<ShowInfoWindowData> showInfoWindowDataList) {
//        Iterator var2 = this.nearPoint.iterator();
//
//        while(var2.hasNext()) {
//            Marker marker = (Marker)var2.next();
//            marker.remove();
//        }
//
//        this.nearPoint.clear();
//    }
//
//    public void onCameraMove() {
//        Iterator var1;
//        if (this.iMapStatusChangeListeners != null) {
//            var1 = this.iMapStatusChangeListeners.iterator();
//
//            while(var1.hasNext()) {
//                IMapStatusChangeListener iMapStatusChangeListener = (IMapStatusChangeListener)var1.next();
//                iMapStatusChangeListener.onMapStatusChangeStart();
//            }
//        }
//
//        var1 = this.markers.iterator();
//
//        while(var1.hasNext()) {
//            Marker marker = (Marker)var1.next();
//            ShowInfoWindowData showInfoWindowData = (ShowInfoWindowData)marker.getTag();
//            Location location = showInfoWindowData.getLocation();
//            if (showInfoWindowData.getHeight() != 0) {
//                Point point = this.toScreenLocation(location);
//                point.set(point.x, point.y + showInfoWindowData.getHeight());
//                location = this.getFromScreenLocation(point);
//            }
//
//            LatLng latLng = new LatLng(location.getGoogleLatitude(), location.getGoogleLongitude());
//            marker.setPosition(latLng);
//        }
//
//    }
//
//    public BitmapDescriptor fromView(View view) {
//        Bitmap bitmap;
//        if (view.getParent() == null) {
//            FrameLayout frameLayout = new FrameLayout(this.context);
//            frameLayout.addView(view);
//            frameLayout.setDrawingCacheEnabled(true);
//            bitmap = this.getBitmapFromView(frameLayout);
//            frameLayout.removeView(view);
//        } else {
//            view.setDrawingCacheEnabled(true);
//            bitmap = this.getBitmapFromView(view);
//        }
//
//        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromBitmap(bitmap);
//        bitmap.recycle();
//        return bitmapDescriptor;
//    }
//
//    public void zoomToSpan(List<LatLng> latLngs) {
//        if (latLngs != null && latLngs.size() > 0 && this.mapView != null) {
//            com.google.android.gms.maps.model.LatLngBounds.Builder builder = new com.google.android.gms.maps.model.LatLngBounds.Builder();
//
//            LatLng location;
//            for(Iterator var3 = latLngs.iterator(); var3.hasNext(); builder = builder.include(location)) {
//                location = (LatLng)var3.next();
//            }
//
//            LatLngBounds bounds = builder.build();
//            CameraUpdate cameraPosition = CameraUpdateFactory.newLatLngBounds(bounds, this.mapView.getWidth() == 0 ? 500 : this.mapView.getWidth(), this.mapView.getHeight() == 0 ? 1000 : this.mapView.getHeight(), this.mapPadding);
//            this.googleMap.animateCamera(cameraPosition, 250, (CancelableCallback)null);
//        }
//
//    }
//
//    private void removeOldInfoWindow(ShowInfoWindowData showInfoWindowData) {
//        Marker marker = null;
//        Iterator var3 = this.markers.iterator();
//
//        while(true) {
//            Marker item;
//            ShowInfoWindowData itemData;
//            do {
//                do {
//                    if (!var3.hasNext()) {
//                        this.markers.remove(marker);
//                        return;
//                    }
//
//                    item = (Marker)var3.next();
//                } while(item.getTag() == null);
//
//                itemData = (ShowInfoWindowData)item.getTag();
//            } while(showInfoWindowData.getView() != itemData.getView() && (showInfoWindowData.getView().getTag() == null || !showInfoWindowData.getView().getTag().toString().equals(itemData.getView().getTag().toString())));
//
//            if (itemData.getView().getParent() != null) {
//                ViewGroup viewGroup = (ViewGroup)itemData.getView().getParent();
//                viewGroup.removeView(showInfoWindowData.getView());
//            }
//
//            item.remove();
//            marker = item;
//        }
//    }
//
//    public Bitmap getBitmapFromView(View view) {
//        try {
//            this.banTextViewHorizontallyScrolling(view);
//            view.destroyDrawingCache();
//            view.measure(MeasureSpec.makeMeasureSpec(0, 0), MeasureSpec.makeMeasureSpec(0, 0));
//            view.layout(0, 0, view.getMeasuredWidth(), view.getMeasuredHeight());
//            Bitmap bitmap = view.getDrawingCache();
//            return bitmap != null ? bitmap.copy(Config.ARGB_8888, false) : null;
//        } catch (Throwable var3) {
//            return null;
//        }
//    }
//
//    private void banTextViewHorizontallyScrolling(View view) {
//        if (view instanceof ViewGroup) {
//            for(int index = 0; index < ((ViewGroup)view).getChildCount(); ++index) {
//                this.banTextViewHorizontallyScrolling(((ViewGroup)view).getChildAt(index));
//            }
//        } else if (view instanceof TextView) {
//            ((TextView)view).setHorizontallyScrolling(false);
//        }
//
//    }
//
//    public void onCameraChange(CameraPosition cameraPosition) {
//        if (this.iMapStatusChangeListeners != null) {
//            Iterator var2 = this.iMapStatusChangeListeners.iterator();
//
//            while(var2.hasNext()) {
//                IMapStatusChangeListener iMapStatusChangeListener = (IMapStatusChangeListener)var2.next();
//                Location location = new Location(0.0D, 0.0D);
//                location.setGoogleLongitude(this.googleMap.getCameraPosition().target.longitude);
//                location.setGoogleLatitude(this.googleMap.getCameraPosition().target.latitude);
//                iMapStatusChangeListener.onMapStatusChangeFinish(location);
//            }
//        }
//
//    }
//}
