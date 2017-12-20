package com.wipro.weather.HomePackage;

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
import com.wipro.weather.HomePackage.dagger.DaggerMainComponent;


import com.wipro.weather.HomePackage.Core.MainView;
import com.wipro.weather.HomePackage.dagger.MainContextModule;
import com.wipro.weather.Models.WeatherData;

import javax.inject.Inject;

import dagger.android.DaggerActivity;

public class MainActivity extends AppCompatActivity implements HomeContract.View {


    @Inject
    MainView view;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        DaggerMainComponent.builder().MainContextModule(new MainContextModule(this)).build().inject(this);
        setContentView(view.constructView());
        if (checkAndAskForLocationPermissions()) {
            checkGpsEnabledAndPrompt();
        }
    }


    public void onStoredDataFetched(WeatherData weatherData) {
        view.updateUI(weatherData);
    }


    private void checkGpsEnabledAndPrompt() {
        boolean isLocationEnabled = view.mLocationManager.isProviderEnabled(LocationManager.GPS_PROVIDER);
        if (!isLocationEnabled) {
            new AlertDialog.Builder(this).setCancelable(false).setTitle("GPS is not enabled").setMessage("This app required GPS to get the weather information. Do you want to enable GPS?").setPositiveButton(17039370, (android.content.DialogInterface.OnClickListener) (new android.content.DialogInterface.OnClickListener() {
                public final void onClick(DialogInterface dialog, int which) {
                    Intent intent = new Intent("android.settings.LOCATION_SOURCE_SETTINGS");
                    startActivityForResult(intent, view.RC_ENABLE_LOCATION);
                    dialog.dismiss();
                }
            })).create().show();
        } else {
            requestLocationUpdates();
        }

    }

    @Override
    public Context getContext() {
        return this;
    }


    private void requestLocationUpdates() {
        String provider = "network";
        if (view.mLocationManager != null) {
            view.mLocationManager.requestLocationUpdates(provider, 0L, 0.0F, view.mLocationListener);
        }

        Location location = view.mLocationManager != null ? view.mLocationManager.getLastKnownLocation(provider) : null;
        view.mLocationListener.onLocationChanged(location);
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode == view.RC_ENABLE_LOCATION) {
            checkGpsEnabledAndPrompt();
        }

    }

    private boolean checkAndAskForLocationPermissions() {
        if (Build.VERSION.SDK_INT >= 23 && this.checkSelfPermission("android.permission.ACCESS_FINE_LOCATION") != PackageManager.PERMISSION_GRANTED) {
            Object[] elementsiv = new String[]{"android.permission.ACCESS_FINE_LOCATION"};
            requestPermissions((String[]) elementsiv, view.RC_LOCATION_PERMISSION);
            return false;
        } else {
            return true;
        }
    }

    public void onRequestPermissionsResult(int requestCode, String[] permissions, int[] grantResults) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults);
        if (requestCode == view.RC_LOCATION_PERMISSION) {
            if (grantResults[0] == 0) {
                checkGpsEnabledAndPrompt();
            } else {
                checkAndAskForLocationPermissions();
            }
        }
    }

    public void onDataFetched(WeatherData weatherData) {
        view.mSwipeRefreshLayout.setRefreshing(false);
        view.updateUI(weatherData);
    }

    @Override
    public void onError() {
        view.onError();
    }

    public void onDestroy() {
        super.onDestroy();
        view.onDestroy();
    }

}
