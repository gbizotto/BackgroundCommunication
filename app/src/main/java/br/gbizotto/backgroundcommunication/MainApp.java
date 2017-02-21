package br.gbizotto.backgroundcommunication;

import android.app.Application;
import br.gbizotto.backgroundcommunication.weather.WeatherCommunication;

/**
 * Created by Gabriela on 20/02/2017.
 */

public class MainApp extends Application {

    @Override
    public void onCreate() {

        new WeatherCommunication().schedule(this);
    }
}
