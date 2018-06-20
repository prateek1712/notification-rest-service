package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class GoldSubscription extends SubscriptionType{

    //subscription ID
    public  final int ID = 2;

    //Price of subscription
    public  final int PRICE = 99;

    //Number of Notifications Allowed in a Day
    public  final int NOTIFS_ALLOWED_PER_DAY = 10000000;



    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    public  final Set<Notification> allowedNotifTypes;

    public int getId() {
        return ID;
    }

    public int getPrice() {
        return PRICE;
    }

    public int getNotifsAllowedPerDay() {
        return NOTIFS_ALLOWED_PER_DAY;
    }

    // Initialisation block for allowedNotifTypes : Allowed SMS & EMAIL

    {
        allowedNotifTypes = new HashSet<Notification>();
        allowedNotifTypes.add(Notification.SMS);
        allowedNotifTypes.add(Notification.EMAIL);
    }

    @Override
    public  boolean ifAllowedNotif(Notification n)
    {
        if(allowedNotifTypes.contains(n)) return true;
        return false;
    }

    private GoldSubscription(){ }; //To disallow instantiation of class

    public static GoldSubscription instance = null;
    public static GoldSubscription getInstance()
    {
        if(instance == null)
        {
            instance = new GoldSubscription();
        }
        return instance;
    }
}


