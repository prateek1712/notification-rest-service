package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

abstract public class SubscriptionType {
    //TODO Complete SubscriptionType

    abstract public int getId();

    abstract public int getPrice();

    abstract public int getNotifsAllowedPerDay();

    abstract public boolean ifAllowedNotif(Notification n);

    //Constructor; can only be called from Subclasses
    protected SubscriptionType(){

    }
}
