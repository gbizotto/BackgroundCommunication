package br.gbizotto.backgroundcommunication;

import android.app.Application;

import java.util.concurrent.ScheduledThreadPoolExecutor;

import br.gbizotto.backgroundcommunication.weather.WeatherCommunication;

/**
 * Created by Gabriela on 20/02/2017.
 */

public class MainApp extends Application {

    ScheduledThreadPoolExecutor mWeatherExecutor;

    @Override
    public void onCreate() {
        mWeatherExecutor = new WeatherCommunication().schedule(this);
    }

    @Override
    public void onTerminate() {
        super.onTerminate();

        if (mWeatherExecutor != null) {
            mWeatherExecutor.shutdownNow();
        }
    }
}
