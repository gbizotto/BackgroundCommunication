package br.gbizotto.backgroundcommunication.background;

import android.app.IntentService;
import android.content.Intent;

import br.gbizotto.backgroundcommunication.R;

public class CommunicationService extends IntentService {

    public CommunicationService() {
        super("CommunicationService");
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        if (!intent.hasExtra(getString(R.string.intent_communication))) {
            throw new InvalidArgumentException(getString(R.string.invalid_argument));
        }

        BackgroundCommunication backgroundCommunication = (BackgroundCommunication) intent.getSerializableExtra(getString(R.string.intent_communication));
        if (backgroundCommunication == null) {
            throw new InvalidArgumentException(getString(R.string.invalid_argument));
        }

        backgroundCommunication.doCommunication();
    }
}
