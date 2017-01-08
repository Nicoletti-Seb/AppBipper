package app.mbds.fr.unice.appbipper.service.gms;

import android.app.IntentService;
import android.content.Intent;

import com.google.android.gms.gcm.GcmPubSub;
import com.google.android.gms.gcm.GoogleCloudMessaging;
import com.google.android.gms.iid.InstanceID;

import java.io.IOException;

import app.mbds.fr.unice.appbipper.R;

/**
 * Created by 53js-Seb on 03/01/2017.
 *
 */

public class RegistrationIntentService extends IntentService {

    private static final String TAG = "RegistrationIntentService";

    private static final String[] TOPICS = {"global"};


    public RegistrationIntentService() {
        super(TAG);
    }

    @Override
    protected void onHandleIntent(Intent intent) {

        try {
            InstanceID instanceID = InstanceID.getInstance(this);
            String token = instanceID.getToken(getString(R.string.gcm_defaultSenderId),
                    GoogleCloudMessaging.INSTANCE_ID_SCOPE, null);

            subscribeTopics(token);
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

    private void subscribeTopics(String token) throws IOException {
        GcmPubSub pubSub = GcmPubSub.getInstance(this);
        for (String topic : TOPICS) {
            pubSub.subscribe(token, "/topics/" + topic, null);
        }
    }
}
