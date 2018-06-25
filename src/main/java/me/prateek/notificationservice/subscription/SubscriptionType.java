package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.notification.NotificationType;

import java.util.Set;

public abstract class SubscriptionType {
    //TODO Complete SubscriptionType

    abstract public int getId();

    abstract public int getPrice();

    abstract public int getNotifsAllowedPerDay();

    abstract public boolean ifAllowedNotif(NotificationType n);

    abstract public Set<NotificationType> getAllowedNotifTypes();

    //Constructor; can only be called from Subclasses
    //protected SubscriptionType(){

    //}
}
