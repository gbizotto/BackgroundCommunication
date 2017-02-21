package br.gbizotto.backgroundcommunication.background;

import android.content.Context;
import android.util.Log;

import java.util.concurrent.ScheduledThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

import br.gbizotto.backgroundcommunication.MainApp;

/**
 * Created by Gabriela on 21/02/2017.
 */

public abstract class BackgroundCommunicationActions {

    protected ScheduledThreadPoolExecutor schedule(Context context,
                                                   BackgroundCommunication backgroundCommunication,
                                                   long initialDelay,
                                                   long delayBetweenExecutions,
                                                   TimeUnit timeUnit) {

        ScheduledThreadPoolExecutor executor = new ScheduledThreadPoolExecutor(1);

        Log.v(MainApp.class.getSimpleName(), "Agendando task");
        executor.scheduleWithFixedDelay(new PeriodicExecution(context, backgroundCommunication), initialDelay, delayBetweenExecutions, timeUnit);

        return executor;
    }
}
