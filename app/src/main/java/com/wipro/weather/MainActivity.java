package com.wipro.weather;

import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Build;
import android.support.design.widget.CoordinatorLayout;
import android.support.design.widget.Snackbar;
import android.support.v4.content.ContextCompat;
import android.support.v4.widget.SwipeRefreshLayout;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;


import com.wipro.weather.HomePackage.HomeContract;
import com.wipro.weather.HomePackage.HomePresenter;
import com.wipro.weather.Models.WeatherData;
import com.wipro.weather.Utils.WeatherToImage;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity implements HomeContract.View {

    private final int RC_ENABLE_LOCATION = 1;
    private final int RC_LOCATION_PERMISSION = 2;

    private HomeContract.Presenter mPresenter;

    private LocationManager mLocationManager;

    @BindView(R.id.swipe_refresh_layout)
    SwipeRefreshLayout mSwipeRefreshLayout;

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

    @BindView(R.id.coordinator_layout)
    CoordinatorLayout coordinatorLayout;

    private Location mLocation;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        mLocationManager = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        if (checkAndAskForLocationPermissions()) {
            checkGpsEnabledAndPrompt();
        }
        mPresenter = new HomePresenter();
        mPresenter.subscribe(this);
        initViews();

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

    private LocationListener mLocationListener = (LocationListener) (new LocationListener() {
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

    public void onStoredDataFetched(WeatherData weatherData) {
        updateUI(weatherData);
    }

    public void onDataFetched(WeatherData weatherData) {
        mSwipeRefreshLayout.setRefreshing(false);
        updateUI(weatherData);
    }

    private void updateUI(WeatherData weather) {

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
        final Snackbar retrySnackBar = Snackbar.make(coordinatorLayout, "Unable to fetch weather data.", -2);
        retrySnackBar.setAction("Retry", (new View.OnClickListener() {
            public final void onClick(android.view.View v) {
                if (mPresenter != null) {
                    double latitude = mLocation != null ? mLocation.getLatitude() : 0.0D;
                    mPresenter.refresh(latitude, mLocation != null ? mLocation.getLongitude() : 0.0D);
                }
                mSwipeRefreshLayout.setRefreshing(true);
                retrySnackBar.dismiss();
            }
        }));
        retrySnackBar.setActionTextColor(ContextCompat.getColor((Context) this, R.color.colorPrimary));
        retrySnackBar.show();
    }

    protected void onDestroy() {
        super.onDestroy();

        if (mPresenter != null) {
            mPresenter.unSubscribe();
        }

        if (mLocationManager != null) {
            mLocationManager.removeUpdates(mLocationListener);
        }

    }

    private void checkGpsEnabledAndPrompt() {
        boolean isLocationEnabled = mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isLocationEnabled) {
            new AlertDialog.Builder(this).setCancelable(false).setTitle("GPS is not enabled").setMessage("This app required GPS to get the weather information. Do you want to enable GPS?").setPositiveButton(17039370, (android.content.DialogInterface.OnClickListener) (new android.content.DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    startActivityForResult(intent, RC_ENABLE_LOCATION);
                    dialog.dismiss();
                }
            })).create().show();
        } else {
            this.requestLocationUpdates();
        }

    }

    private void requestLocationUpdates() {
        String provider = "network";
        if (this.mLocationManager != null) {
            mLocationManager.requestLocationUpdates(provider, 0L, 0.0F, mLocationListener);
        }

        Location location = mLocationManager != null ? mLocationManager.getLastKnownLocation(provider) : null;
        mLocationListener.onLocationChanged(location);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == RC_ENABLE_LOCATION) {
            checkGpsEnabledAndPrompt();
        }

    }

    private boolean checkAndAskForLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23 && this.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED) {
            Object[] elementsiv = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
            requestPermissions((String[]) elementsiv, this.RC_LOCATION_PERMISSION);
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == RC_LOCATION_PERMISSION) {
            if (grantResults[0] == 0) {
                checkGpsEnabledAndPrompt();
            } else {
                checkAndAskForLocationPermissions();
            }
        }
    }

    @Override
    public Context getContext() {
        return this;
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
