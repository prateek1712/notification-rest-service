package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class SilverSubscription extends SubscriptionType{
    //subscription ID
    public  final int ID = 1;

    //Price of Subscriptiontype
    public  final int PRICE = 49;

    //Number of Notifications Allowed in a Day
    public  final int NOTIFS_ALLOWED_PER_DAY = 10000000;

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
    public  final Set<Notification> allowedNotifTypes;

    // Initialisation block for allowedNotifTypes : Allowed EMAIL only

    {
        allowedNotifTypes = new HashSet<Notification>();
        allowedNotifTypes.add(Notification.EMAIL);
    }

    @Override
    public boolean ifAllowedNotif(Notification n)
    {
        if(allowedNotifTypes.contains(n)) return true;
        return false;
    }

    private SilverSubscription(){ }; //To disallow instantiation of class

    public static SilverSubscription instance = null;
    public static SilverSubscription getInstance()
    {
        if(instance == null)
        {
            instance = new SilverSubscription();
        }
        return instance;
    }
}
