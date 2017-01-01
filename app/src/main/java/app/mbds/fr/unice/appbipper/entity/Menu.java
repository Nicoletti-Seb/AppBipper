package app.mbds.fr.unice.appbipper.entity;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by 53js-Seb on 01/01/2017.
 */

public class Menu implements Serializable {

    private String id;

    private float price;

    private float discount;

    private Person server;

    private Person cooker;

    private List<Product> items = new ArrayList<>();


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public float getPrice() {
        return price;
    }

    public void setPrice(float price) {
        this.price = price;
    }

    public float getDiscount() {
        return discount;
    }

    public void setDiscount(float discount) {
        this.discount = discount;
    }

    public Person getServer() {
        return server;
    }

    public void setServer(Person server) {
        this.server = server;
    }

    public Person getCooker() {
        return cooker;
    }

    public void setCooker(Person cooker) {
        this.cooker = cooker;
    }

    public List<Product> getItems() {
        return items;
    }

    public void setItems(List<Product> items) {
        this.items = items;
    }
}
