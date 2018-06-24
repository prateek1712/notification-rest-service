package me.prateek.notificationservice.subscription;

import me.prateek.notificationservice.notification.NotificationType;

import java.util.HashSet;
import java.util.Set;

//A Constant Class
final class SilverSubscription extends SubscriptionType{
    //subscription ID
    private final int ID = 1;

    //Price of Subscriptiontype
    private final int PRICE = 49;

    //Number of Notifications Allowed in a Day
    private final int NOTIFS_ALLOWED_PER_DAY = 1000000;

    //Types of Notifications Allowed: EMAIL, SMS, PUSH
    private final Set<NotificationType> allowedNotifTypes;

    // Initialisation block for allowedNotifTypes : Allowed EMAIL only

    {
        allowedNotifTypes = new HashSet<NotificationType>();
        allowedNotifTypes.add(NotificationType.EMAIL);
    }

    public int getId() {
        return ID;
    }

    public int getPrice() {
        return PRICE;
    }

    public int getNotifsAllowedPerDay() {
        return NOTIFS_ALLOWED_PER_DAY;
    }

    public Set<NotificationType> getAllowedNotifTypes() {
        return allowedNotifTypes;
    }

    @Override
    public boolean ifAllowedNotif(NotificationType n)
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
