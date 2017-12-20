package com.wipro.weather.HomePackage;

import android.content.Context;
import android.os.Environment;
import android.util.Log;

import com.google.gson.Gson;
import com.wipro.weather.Models.WeatherData;
import com.wipro.weather.Networking.Constants;
import com.wipro.weather.Networking.NetworkService;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.OutputStream;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.Consumer;
import io.reactivex.schedulers.Schedulers;

public class HomePresenter implements HomeContract.Presenter {

    private HomeContract.View mView;

    public HomeContract.View getmView() {
        return mView;
    }

    @Override
    public void refresh(double lat, double lng) {

        NetworkService.INSTANCE.getMetaWeatherApi().
                getLocationDetails(Constants.INSTANCE.getAPIKEY(), Constants.INSTANCE.getTYPE_TEXT_PLAIN(), lat, lng).
                subscribeOn(Schedulers.io()).observeOn(AndroidSchedulers.mainThread()).subscribe(new Consumer<WeatherData>() {
            @Override
            public void accept(WeatherData weather) throws Exception {
                updateData(weather);
            }
        });
    }


    private void updateData(WeatherData weatherData) {
        HomeContract.View getmView = getmView();
        if (getmView != null) {
            getmView.onDataFetched(weatherData);
        }
        storeFileToExternalStorage(weatherData);
    }

    @Override
    public void unSubscribe() {
        this.mView = null;
    }

    @Override
    public void subscribe(HomeContract.View view) {
        mView = view;
        WeatherData storedWeather = getFileFromStorage();
        if (storedWeather != null) {
            if (mView != null) {
                mView.onStoredDataFetched(storedWeather);
            }
        }

    }

    /*
* Save the data in internal file as a json structure
*/
    private void storeFileToExternalStorage(WeatherData weatherData) {
        try {
            String weatherJson = new Gson().toJson(weatherData);

            File weatherFile = new File(Environment.getExternalStorageDirectory() + File.separator + Constants.INSTANCE.getWEATHER_FILE_NAME());
            if (weatherFile.exists()) weatherFile.delete();
            weatherFile.createNewFile();

            OutputStream outputStream = mView.getContext().openFileOutput(Constants.INSTANCE.getWEATHER_FILE_NAME(), Context.MODE_PRIVATE);
            outputStream.write(weatherJson.getBytes());
            outputStream.close();
        } catch (Exception e) {

        }
    }


    private WeatherData getFileFromStorage() {
        try {
            File sdcard = Environment.getExternalStorageDirectory();

            File file = new File(sdcard, Constants.INSTANCE.getWEATHER_FILE_NAME());

            StringBuilder text = new StringBuilder();

            try {
                BufferedReader br = new BufferedReader(new FileReader(file));
                String line;

                while ((line = br.readLine()) != null) {
                    text.append(line);
                    text.append('\n');
                }
                br.close();
            } catch (Exception e) {
            }
            return new Gson().fromJson(text.toString(), WeatherData.class);
        } catch (Exception e) {
            return null;
        }
    }
}
