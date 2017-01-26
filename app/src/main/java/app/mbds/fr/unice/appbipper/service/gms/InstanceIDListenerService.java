package app.mbds.fr.unice.appbipper.service.gms;

import com.google.firebase.iid.FirebaseInstanceId;
import com.google.firebase.iid.FirebaseInstanceIdService;

import app.mbds.fr.unice.appbipper.BipperApplication;
import app.mbds.fr.unice.appbipper.R;
import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by 53js-Seb on 03/01/2017.
 *
 * Refresh token
 */
public class InstanceIDListenerService extends FirebaseInstanceIdService {

    @Override
    public void onTokenRefresh() {
        String token = FirebaseInstanceId.getInstance().getToken();

        //register to server
        Person user = ((BipperApplication)getApplication()).getUser();
        if(user == null){
            return;
        }

        user.setGcmKey(token);
        new AddKey(this).doInBackground(user.getId(),
                token, getResources().getString(R.string.api_key_firebase));
    }

}
