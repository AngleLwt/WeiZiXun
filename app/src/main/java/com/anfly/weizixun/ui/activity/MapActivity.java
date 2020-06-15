package com.anfly.weizixun.ui.activity;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;

import androidx.appcompat.app.AppCompatActivity;

import com.anfly.weizixun.R;
import com.anfly.weizixun.utils.PoiOverlay;
import com.anfly.weizixun.utils.WalkingRouteOverlay;
import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.location.LocationClientOption;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.baidu.mapapi.model.LatLngBounds;
import com.baidu.mapapi.search.core.SearchResult;
import com.baidu.mapapi.search.poi.OnGetPoiSearchResultListener;
import com.baidu.mapapi.search.poi.PoiBoundSearchOption;
import com.baidu.mapapi.search.poi.PoiCitySearchOption;
import com.baidu.mapapi.search.poi.PoiDetailResult;
import com.baidu.mapapi.search.poi.PoiDetailSearchResult;
import com.baidu.mapapi.search.poi.PoiIndoorResult;
import com.baidu.mapapi.search.poi.PoiNearbySearchOption;
import com.baidu.mapapi.search.poi.PoiResult;
import com.baidu.mapapi.search.poi.PoiSearch;
import com.baidu.mapapi.search.route.BikingRouteResult;
import com.baidu.mapapi.search.route.DrivingRouteResult;
import com.baidu.mapapi.search.route.IndoorRouteResult;
import com.baidu.mapapi.search.route.MassTransitRouteResult;
import com.baidu.mapapi.search.route.OnGetRoutePlanResultListener;
import com.baidu.mapapi.search.route.PlanNode;
import com.baidu.mapapi.search.route.RoutePlanSearch;
import com.baidu.mapapi.search.route.TransitRouteResult;
import com.baidu.mapapi.search.route.WalkingRoutePlanOption;
import com.baidu.mapapi.search.route.WalkingRouteResult;
import com.baidu.mapapi.walknavi.WalkNavigateHelper;
import com.baidu.mapapi.walknavi.adapter.IWEngineInitListener;
import com.baidu.mapapi.walknavi.adapter.IWRoutePlanListener;
import com.baidu.mapapi.walknavi.model.WalkRoutePlanError;
import com.baidu.mapapi.walknavi.params.WalkNaviLaunchParam;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class MapActivity extends AppCompatActivity {

    @BindView(R.id.mapview)
    MapView mapview;
    @BindView(R.id.btn_location)
    Button btnLocation;
    @BindView(R.id.btn_marker)
    Button btnMarker;
    @BindView(R.id.btn_pio)
    Button btnPio;
    @BindView(R.id.bnt_guide)
    Button bntGuide;
    @BindView(R.id.bnt_path)
    Button bntPath;
    private BaiduMap baiduMap;
    private LocationClient mLocationClient;
    private BDLocation mLocation;
    private PoiSearch mPoiSearch;
    private LatLng startPt;
    private LatLng endPt;
    private WalkNaviLaunchParam mParam;
    private RoutePlanSearch mSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
//        mapview = new MapView(this);
//        setContentView(mapview);
        setContentView(R.layout.activity_map);
        ButterKnife.bind(this);

        baiduMap = mapview.getMap();

        initLocation();
    }

    private void initLocation() {
        baiduMap.setMyLocationEnabled(true);
        //定位初始化
        mLocationClient = new LocationClient(this);

        //通过LocationClientOption设置LocationClient相关参数
        LocationClientOption option = new LocationClientOption();
        option.setOpenGps(true); // 打开gps
        option.setCoorType("bd09ll"); // 设置坐标类型
        option.setScanSpan(1000);

        //设置locationClientOption
        mLocationClient.setLocOption(option);

        //注册LocationListener监听器
        MyLocationListener myLocationListener = new MyLocationListener();
        mLocationClient.registerLocationListener(myLocationListener);
        //开启地图定位图层
        mLocationClient.start();
    }

    @OnClick({R.id.btn_location, R.id.btn_marker, R.id.btn_pio, R.id.bnt_guide, R.id.bnt_path})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btn_location:
                locationToMyPosition();
                break;
            case R.id.btn_marker:
                addMarker();
                break;
            case R.id.btn_pio:
                pio();
                break;
            case R.id.bnt_guide:
                guide();
                break;
            case R.id.bnt_path:
                path();
                break;
        }
    }

    private void path() {
        mSearch = RoutePlanSearch.newInstance();
        OnGetRoutePlanResultListener listener = new OnGetRoutePlanResultListener() {
            @Override
            public void onGetWalkingRouteResult(WalkingRouteResult walkingRouteResult) {
                //创建WalkingRouteOverlay实例
                WalkingRouteOverlay overlay = new WalkingRouteOverlay(baiduMap);
                if (walkingRouteResult.getRouteLines().size() > 0) {
                    //获取路径规划数据,(以返回的第一条数据为例)
                    //为WalkingRouteOverlay实例设置路径数据
                    overlay.setData(walkingRouteResult.getRouteLines().get(0));
                    //在地图上绘制WalkingRouteOverlay
                    overlay.addToMap();
                }
            }

            @Override
            public void onGetTransitRouteResult(TransitRouteResult transitRouteResult) {

            }

            @Override
            public void onGetMassTransitRouteResult(MassTransitRouteResult massTransitRouteResult) {

            }

            @Override
            public void onGetDrivingRouteResult(DrivingRouteResult drivingRouteResult) {

            }

            @Override
            public void onGetIndoorRouteResult(IndoorRouteResult indoorRouteResult) {

            }

            @Override
            public void onGetBikingRouteResult(BikingRouteResult bikingRouteResult) {

            }
        };
        mSearch.setOnGetRoutePlanResultListener(listener);

        PlanNode stNode = PlanNode.withCityNameAndPlaceName("北京", "西二旗地铁站");
        PlanNode enNode = PlanNode.withCityNameAndPlaceName("北京", "百度科技园");

        mSearch.walkingSearch((new WalkingRoutePlanOption())
                .from(stNode)
                .to(enNode));

    }

    private void guide() {
        WalkNavigateHelper.getInstance().initNaviEngine(this, new IWEngineInitListener() {

            @Override
            public void engineInitSuccess() {
                //引擎初始化成功的回调
                routeWalkPlanWithParam();
            }

            @Override
            public void engineInitFail() {
                Log.e("TAG", "BaiduMapActivity engineInitFail()");
                //引擎初始化失败的回调
            }
        });
    }

    private void routeWalkPlanWithParam() {
        //起终点位置
        startPt = new LatLng(40.047416, 116.312143);
        endPt = new LatLng(40.048424, 116.313513);
        //构造WalkNaviLaunchParam
        mParam = new WalkNaviLaunchParam().stPt(startPt).endPt(endPt);

        //发起算路
        WalkNavigateHelper.getInstance().routePlanWithParams(mParam, new IWRoutePlanListener() {
            @Override
            public void onRoutePlanStart() {
                //开始算路的回调
            }

            @Override
            public void onRoutePlanSuccess() {
                //算路成功
                //跳转至诱导页面
                Intent intent = new Intent(MapActivity.this, WNaviGuideActivity.class);
                startActivity(intent);
            }

            @Override
            public void onRoutePlanFail(WalkRoutePlanError walkRoutePlanError) {
                //算路失败的回调
            }
        });

    }

    private void pio() {
        mPoiSearch = PoiSearch.newInstance();
        OnGetPoiSearchResultListener listener = new OnGetPoiSearchResultListener() {
            @Override
            public void onGetPoiResult(PoiResult poiResult) {
                if (poiResult.error == SearchResult.ERRORNO.NO_ERROR) {
                    baiduMap.clear();

                    //创建PoiOverlay对象
                    PoiOverlay poiOverlay = new PoiOverlay(baiduMap);

                    //设置Poi检索数据
                    poiOverlay.setData(poiResult);

                    //将poiOverlay添加至地图并缩放至合适级别
                    poiOverlay.addToMap();
                    poiOverlay.zoomToSpan();
                }
            }

            @Override
            public void onGetPoiDetailResult(PoiDetailSearchResult poiDetailSearchResult) {

            }

            @Override
            public void onGetPoiIndoorResult(PoiIndoorResult poiIndoorResult) {

            }

            //废弃
            @Override
            public void onGetPoiDetailResult(PoiDetailResult poiDetailResult) {

            }
        };

        mPoiSearch.setOnGetPoiSearchResultListener(listener);

        //城市内检索
//        mPoiSearch.searchInCity(new PoiCitySearchOption()
//                .city("北京") //必填
//                .keyword("美食") //必填
//                .pageNum(10));

        //城市周边检索
//        mPoiSearch.searchNearby(new PoiNearbySearchOption()
//                .location(new LatLng(39.915446, 116.403869))
//                .radius(10000)
//                .keyword("餐厅")
//                .pageNum(10));

        /**
         * 设置矩形检索区域
         */
        LatLngBounds searchBounds = new LatLngBounds.Builder()
                .include(new LatLng(39.92235, 116.380338))
                .include(new LatLng(39.947246, 116.414977))
                .build();
        //在searchBounds区域内检索餐厅
        mPoiSearch.searchInBound(new PoiBoundSearchOption()
                .bound(searchBounds)
                .keyword("餐厅"));
    }

    private void addMarker() {

        BitmapDescriptor bitmapDescriptor = BitmapDescriptorFactory.fromResource(R.drawable.icon_geo);
        LatLng target = baiduMap.getMapStatus().target;
        double latitude = target.latitude;
        double longitude = target.longitude;
        LatLng latLng = new LatLng(latitude, longitude);

        MarkerOptions markerOptions = new MarkerOptions()
                .position(latLng)
                .icon(bitmapDescriptor);

        baiduMap.addOverlay(markerOptions);
    }

    private void locationToMyPosition() {
        //如果已经定位了,只需要将地图界面移动到用户所在位置即可
        //改变地图手势的中心点（地图的中心点）
        //mLocation是定位时获取到的用户位置信息对象
        LatLng latLng = new LatLng(mLocation.getLatitude(), mLocation.getLongitude());
        //改变地图手势的中心点
        baiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(latLng));
    }

    public class MyLocationListener extends BDAbstractLocationListener {
        @Override
        public void onReceiveLocation(BDLocation location) {
            //mapView 销毁后不在处理新接收的位置
            if (location == null || mapview == null) {
                return;
            }

            mLocation = location;
            MyLocationData locData = new MyLocationData.Builder()
                    .accuracy(location.getRadius())
                    // 此处设置开发者获取到的方向信息，顺时针0-360
                    .direction(location.getDirection()).latitude(location.getLatitude())
                    .longitude(location.getLongitude()).build();
            baiduMap.setMyLocationData(locData);
        }
    }

    @Override
    protected void onResume() {
        super.onResume();
        mapview.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        mapview.onPause();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mapview.onDestroy();
        mLocationClient.stop();
        mPoiSearch.destroy();
        mSearch.destroy();
    }
}
