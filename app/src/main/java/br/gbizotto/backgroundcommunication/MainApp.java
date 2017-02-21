package br.gbizotto.backgroundcommunication;

import android.app.Application;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.gbizotto.backgroundcommunication.background.CommunicationService;
import br.gbizotto.backgroundcommunication.weather.WeatherCommunication;

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
                Intent intent = new Intent(mContext, CommunicationService.class);
                intent.putExtra(mContext.getString(R.string.intent_communication), new WeatherCommunication());
                startService(intent);
            }
        }

        mSearchWeatherExecutor = new ScheduledThreadPoolExecutor(1);


        if (BuildConfig.DEBUG){
            //TODO search for the difference beetween both methods
            Log.v(MainApp.class.getSimpleName(), "Agendando task");
            mSearchWeatherExecutor.scheduleAtFixedRate(new SearchWeather(), 0, 5, TimeUnit.MINUTES);
        }else{
            mSearchWeatherExecutor.scheduleWithFixedDelay(new SearchWeather(), 1, 1, TimeUnit.HOURS);
        }

    }
}
