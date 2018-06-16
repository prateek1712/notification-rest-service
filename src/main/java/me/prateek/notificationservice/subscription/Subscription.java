package me.prateek.notificationservice.subscription;

import java.util.Date;

public class Subscription {
    long id;
    String type = "Monthly";
    Date purchaseDate;

    public Subscription()
    {

    }

    public long getId() {
        return id;
    }

}
