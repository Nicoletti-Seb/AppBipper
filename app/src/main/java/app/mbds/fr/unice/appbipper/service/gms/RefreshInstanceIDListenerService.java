package app.mbds.fr.unice.appbipper.service.gms;


import android.content.Intent;

import com.google.android.gms.iid.InstanceIDListenerService;
;

/**
 * Created by 53js-Seb on 03/01/2017.
 *
 * Refresh token
 */
public class RefreshInstanceIDListenerService extends InstanceIDListenerService {

    @Override
    public void onTokenRefresh() {
        Intent i = new Intent(this, RegistrationIntentService.class);
        startService(i);
    }
}
