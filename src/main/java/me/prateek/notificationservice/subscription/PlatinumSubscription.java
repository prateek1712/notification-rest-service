package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class PlatinumSubscription extends SubscriptionType{

    //subscription ID
    public final int ID = 3;

    //Price of subscription
    public final int PRICE = 500;

    //Number of Notifications Allowed in a Day
    public final int NOTIFS_ALLOWED_PER_DAY = Integer.MAX_VALUE; //Practically Unlimited

    public int getId() {
        return ID;
    }

    public int getPrice() {
        return PRICE;
    }

    public int getNotifsAllowedPerDay() {
        return NOTIFS_ALLOWED_PER_DAY;
    }

    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    public final Set<Notification> allowedNotifTypes;

    // Initialisation block for allowedNotifTypes : Allowed All

    {
        allowedNotifTypes = new HashSet<Notification>(); //HashSet, since the order doesn't matter
        for(Notification n : Notification.values())
        {
            allowedNotifTypes.add(n);
        }
    }

    @Override
    public boolean ifAllowedNotif(Notification n)
    {
        if(allowedNotifTypes.contains(n)) return true;
        return false;
    }

    private PlatinumSubscription(){ }; //To disallow instantiation of class

    public static PlatinumSubscription instance = null;
    public static PlatinumSubscription getInstance()
    {
        if(instance == null)
        {
            instance = new PlatinumSubscription();
        }
        return instance;
    }
}


