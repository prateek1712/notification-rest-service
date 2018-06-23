//SERVICE CLASS FOR 'NOTIFICATION' MODEL
package me.prateek.notificationservice.notification;


import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import me.prateek.notificationservice.subscription.SubscriptionType;
import me.prateek.notificationservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;

    public Notification getNotification(Integer id){ return notificationRepository.getOne(id); }

    public Notification addNotification(Integer clientId, Integer userId, String notifType, String message)
    {
        //TODO If ClientID or UserID not found, throw exception

        //Check if notification type is invalid
        notifType = notifType.toUpperCase();
        if(!notifType.equals("EMAIL") && !notifType.equals("SMS") && !notifType.equals("PUSH"))
        {
            //TODO Throw IllegalArgument Exception
            return null;
        }
        NotificationType type = NotificationType.valueOf(notifType);

        //Get Subscription for Client
        Subscription sub = subscriptionService.getSubscriptionByClientId(clientId);
        if(sub.isSubscriptionActive()) //Check if subscription is active
        {
            if(!sub.ifDailyLimitReached()) //Check if daily limit not reached
            {
                SubscriptionType st = sub.getSubscriptionTypeInstance();
                if(st.ifAllowedNotif(type)) //Check if notification type allowed
                {
                    if(!userService.getUser(userId).isBlocked()) //Check if User is Not Blocked
                    {
                        //ALL CHECKS PASSED
                        Notification newNotif = new Notification(clientId,userId,type,message);
                        //Add Notification Count for Client
                        subscriptionService.incrementNotifCount(clientId);
                        //Return Notification
                        return notificationRepository.save(newNotif);
                    }
                    else return null; //TODO Throw UserBlocked exception
                }
                else return null; //TODO Throw NotificationTypeInvalid exception
            }
            else return null; //TODO Throw DailyLimitReached exception
        }
        else return null; //TODO Throw SubscriptionNotActive exception
    }

    //TODO Get Notification Count within a Date Range for a Client

}
