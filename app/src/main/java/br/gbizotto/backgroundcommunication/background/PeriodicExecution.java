package br.gbizotto.backgroundcommunication.background;

import android.content.Context;
import android.content.Intent;

import br.gbizotto.backgroundcommunication.R;

/**
 * Created by Gabriela on 21/02/2017.
 */

public class PeriodicExecution implements Runnable {

    private Context context;
    private BackgroundCommunication backgroundCommunication;

    public PeriodicExecution(Context context, BackgroundCommunication backgroundCommunication) {
        this.context = context;
        this.backgroundCommunication = backgroundCommunication;
    }

    @Override
    public void run() {
        Intent intent = new Intent(context, CommunicationService.class);
        intent.putExtra(context.getString(R.string.intent_communication), backgroundCommunication);
        context.startService(intent);
    }
}
