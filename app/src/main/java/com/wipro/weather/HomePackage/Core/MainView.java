package com.wipro.weather.HomePackage.Core;


import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.os.Bundle;
import android.support.design.widget.CoordinatorLayout;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

import com.wipro.weather.HomePackage.HomeContract;
import com.wipro.weather.HomePackage.HomePresenter;
import com.wipro.weather.HomePackage.MainActivity;
import com.wipro.weather.Models.WeatherData;
import com.wipro.weather.R;
import com.wipro.weather.Utils.UiUtils;
import com.wipro.weather.Utils.WeatherToImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainView {

    private View view;

    public final int RC_ENABLE_LOCATION = 1;
    public final int RC_LOCATION_PERMISSION = 2;

    private HomeContract.Presenter mPresenter;

    public LocationManager mLocationManager;


    @BindView(R.id.swipe_refresh_layout)
    public SwipeRefreshLayout mSwipeRefreshLayout;

    @BindView(R.id.temperature_text_view)
    TextView temperatureTextView;
    @BindView(R.id.wind_speed_text_view)
    TextView windSpeedTextView;
    @BindView(R.id.humidity_text_view)
    TextView humidityTextView;
    @BindView(R.id.weather_image_view)
    ImageView weatherImageView;
    @BindView(R.id.weather_condition_text_view)
    TextView weatherConditionTextView;
    @BindView(R.id.city_name_text_view)
    TextView cityNameTextView;

    @BindView(R.id.second_day)
    View secondDayView;
    @BindView(R.id.third_day)
    View thirdDayView;
    @BindView(R.id.fourth_day)
    View fourthDayView;
    @BindView(R.id.fifth_day)
    View fifthDayView;
    private Location mLocation;

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    MainActivity context;

    public MainView(MainActivity context) {
        FrameLayout parent = new FrameLayout(context);
        parent.setLayoutParams(new FrameLayout.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.MATCH_PARENT));
        view = LayoutInflater.from(context).inflate(R.layout.activity_main, parent, true);
        ButterKnife.bind(view, context);
        mLocationManager = (LocationManager) context.getSystemService(Context.LOCATION_SERVICE);

        this.context = context;
        mPresenter = new HomePresenter();
        mPresenter.subscribe(context);
        initViews();
    }

    public View constructView() {
        return view;
    }

    private void initViews() {
        mSwipeRefreshLayout.setOnRefreshListener((new SwipeRefreshLayout.OnRefreshListener() {
            public final void onRefresh() {
                if (mLocation != null) {
                    if (mPresenter != null) {
                        double longitude = mLocation != null ? mLocation.getLatitude() : 0.0D;
                        mPresenter.refresh(longitude, mLocation!= null ? mLocation.getLongitude() : 0.0D);
                    }
                } else {
                    if (mSwipeRefreshLayout != null) {
                        mSwipeRefreshLayout.setRefreshing(false);
                    }
                }

            }
        }));


    }

    public LocationListener mLocationListener = (LocationListener) (new LocationListener() {
        public void onLocationChanged(Location location) {
            if (mSwipeRefreshLayout != null) {
                mSwipeRefreshLayout.setRefreshing(true);
            }
            if (mPresenter != null) {
                mPresenter.refresh(location != null ? location.getLatitude() : 0.0D, location != null ? location.getLongitude() : 0.0D);
            }

            if ((location != null ? Double.valueOf(location.getLatitude()) : null) != null && location.getLatitude() != 0.0D && location.getLongitude() != 0.0D) {
                mLocation = location;
                if (mLocationManager != null) {
                    mLocationManager.removeUpdates(this);
                }
            }

        }


        public void onStatusChanged(String provider, int status, Bundle extras) {
        }

        public void onProviderEnabled(String provider) {
        }

        public void onProviderDisabled(String provider) {
        }
    });


    public void updateUI(WeatherData weather) {

        cityNameTextView.setText(weather.getQuery().getResults().getChannel().getLocation().getCity());
        temperatureTextView.setText(weather.getQuery().getResults().getChannel().getItem().getCondition().getTemp() + " " + weather.getQuery().getResults().getChannel().getUnits().getTemperature());
        windSpeedTextView.setText(weather.getQuery().getResults().getChannel().getWind().getSpeed());
        humidityTextView.setText(weather.getQuery().getResults().getChannel().getAtmosphere().getHumidity());
        weatherConditionTextView.setText(weather.getQuery().getResults().getChannel().getItem().getForecast().get(0).getText());
        weatherImageView.setImageResource(WeatherToImage.getImageForCode(weather.getQuery().getResults().getChannel().getItem().getForecast().get(0).getCode()));

        setValue(secondDayView, 1, weather);
        setValue(thirdDayView, 2, weather);
        setValue(fourthDayView, 3, weather);
        setValue(fifthDayView, 4, weather);

    }

    public void onError() {
        mSwipeRefreshLayout.setRefreshing(false);
        UiUtils.showSnackbar(coordinatorLayout,"Unable to fetch weather data.",-2);
    }

    public void onDestroy() {
        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }
        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }

    }


    private void setValue(View view, int position, WeatherData weather) {
        TextView otherDays = view.findViewById(R.id.day_text_view);
        TextView otherTemperature = view.findViewById(R.id.temperature_text_view);
        ImageView otherTempImage = view.findViewById(R.id.weather_image_view);
        otherDays.setText(weather.getQuery().getResults().getChannel().getItem().getForecast().get(position).getDay());
        otherTemperature.setText(weather.getQuery().getResults().getChannel().getItem().getForecast().get(position).getHigh());
        otherTempImage.setImageResource(WeatherToImage.getImageForCode(weather.getQuery().getResults().getChannel().getItem().getForecast().get(position).getCode()));
    }


}
