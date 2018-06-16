package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class GoldSubscription extends SubscriptionType{

    //Subscription ID
    public static final long ID = 2;

    //Price of Subscription
    public static final long PRICE = 99;

    //Number of Notifications Allowed in a Day
    public static final long NOTIFS_ALLOWED_PER_DAY = 10000000;

    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    public static final Set<Notification> allowedNotifTypes;

    //Static Initialisation block for allowedNotifTypes : Allowed SMS & EMAIL
    static
    {
        allowedNotifTypes = new HashSet<Notification>();
        allowedNotifTypes.add(Notification.SMS);
        allowedNotifTypes.add(Notification.EMAIL);
    }

    public boolean ifAllowedNotif(Notification n)
    {
        if(allowedNotifTypes.contains(n)) return true;
        return false;
    }

    private GoldSubscription(){ }; //To disallow instantiation of class
}


