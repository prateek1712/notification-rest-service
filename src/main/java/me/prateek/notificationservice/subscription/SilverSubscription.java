package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.Notification;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class SilverSubscription extends SubscriptionType{
    //Subscription ID
    public static final long ID = 1;

    //Price of Subscription
    public static final long PRICE = 49;

    //Number of Notifications Allowed in a Day
    public static final long NOTIFS_ALLOWED_PER_DAY = 10000000;

    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    public static final Set<Notification> allowedNotifTypes;

    //Static Initialisation block for allowedNotifTypes : Allowed EMAIL only
    static
    {
        allowedNotifTypes = new HashSet<Notification>();
        allowedNotifTypes.add(Notification.EMAIL);
    }

    private SilverSubscription(){ }; //To disallow instantiation of class
}
