package app.mbds.fr.unice.appbipper;

import android.app.Application;

import app.mbds.fr.unice.appbipper.entity.Person;

/**
 * Created by 53js-Seb on 05/01/2017.
 */

public class BipperApplication extends Application {

    private Person user;

    public void setUser(Person user) {
        this.user = user;
    }

    public Person getUser() {
        return user;
    }
}
