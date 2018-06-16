package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class PlatinumSubscription extends SubscriptionType{

    //Subscription ID
    public static final long ID = 3;

    //Price of Subscription
    public static final long PRICE = 499;

    //Number of Notifications Allowed in a Day
    public static final long NOTIFS_ALLOWED_PER_DAY = Long.MAX_VALUE; //Practically Unlimited

    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    public static final Set<Notification> allowedNotifTypes;

    //Static Initialisation block for allowedNotifTypes : Allowed All
    static
    {
        allowedNotifTypes = new HashSet<Notification>(); //HashSet, since the order doesn't matter
        for(Notification n : Notification.values())
        {
            allowedNotifTypes.add(n);
        }
    }

    public boolean ifAllowedNotif(Notification n)
    {
        if(allowedNotifTypes.contains(n)) return true;
        return false;
    }

    private PlatinumSubscription(){ }; //To disallow instantiation of class
}


