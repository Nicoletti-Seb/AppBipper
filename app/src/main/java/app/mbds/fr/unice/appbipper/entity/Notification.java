package app.mbds.fr.unice.appbipper.entity;

import java.io.Serializable;

/**
 * Created by 53js-Seb on 27/01/2017.
 */

public class Notification implements Serializable {

    private String id;
    private Person sender;
    private Person receiver;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public Person getSender() {
        return sender;
    }

    public void setSender(Person sender) {
        this.sender = sender;
    }

    public Person getReceiver() {
        return receiver;
    }

    public void setReceiver(Person receiver) {
        this.receiver = receiver;
    }
}
