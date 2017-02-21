package br.gbizotto.backgroundcommunication.background;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.gbizotto.backgroundcommunication.BuildConfig;
import br.gbizotto.backgroundcommunication.MainApp;

/**
 * Created by Gabriela on 21/02/2017.
 */

public abstract class BackgroundCommunicationActions {

    protected void schedule(Context context, BackgroundCommunication backgroundCommunication) {
        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        if (BuildConfig.DEBUG){
            Log.v(MainApp.class.getSimpleName(), "Agendando task");
            executor.scheduleWithFixedDelay(new PeriodicExecution(context, backgroundCommunication), 0, 5, TimeUnit.MINUTES);
        }else{
            executor.scheduleWithFixedDelay(new PeriodicExecution(context, backgroundCommunication), 1, 1, TimeUnit.HOURS);
        }
    }
}
