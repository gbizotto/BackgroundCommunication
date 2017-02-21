package br.gbizotto.backgroundcommunication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * Created by Gabriela on 20/02/2017.
 */

public class MainApp extends Application {

    private Context mContext;
    ScheduledThreadPoolExecutor mSearchWeatherExecutor;

    @Override
    public void onCreate() {

        mContext = this;

        class SearchWeather implements Runnable {

            @Override
            public void run() {
                Intent intent = new Intent(mContext, WeatherService.class);
                startService(intent);
            }
        }

        mSearchWeatherExecutor = new ScheduledThreadPoolExecutor(1);


        if (BuildConfig.DEBUG){
            //TODO search for the difference beetween both methods
            mSearchWeatherExecutor.scheduleAtFixedRate(new SearchWeather(), 5, 5, TimeUnit.MINUTES);
        }else{
            mSearchWeatherExecutor.scheduleWithFixedDelay(new SearchWeather(), 1, 1, TimeUnit.HOURS);
        }

    }
}
