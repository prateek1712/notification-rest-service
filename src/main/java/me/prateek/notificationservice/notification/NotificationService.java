//SERVICE CLASS FOR 'NOTIFICATION' MODEL
package me.prateek.notificationservice.notification;


import me.prateek.notificationservice.client.Client;
import me.prateek.notificationservice.client.ClientService;
import me.prateek.notificationservice.exception.GenericException;
import me.prateek.notificationservice.exception.ResourceNotFoundException;
import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import me.prateek.notificationservice.subscription.SubscriptionType;
import me.prateek.notificationservice.user.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    @Autowired
    SubscriptionService subscriptionService;

    @Autowired
    UserService userService;

    @Autowired
    ClientService clientService;

    public void checkIfNotifPresent(Integer id)
    {
        Optional<Notification> notif = notificationRepository.findById(id);
        if(!notif.isPresent())
        {
            throw new ResourceNotFoundException(id,"Notification");
        }
    }

    public Notification getNotification(Integer id){
        checkIfNotifPresent(id);
        return notificationRepository.getOne(id);
    }

    public Notification addNotification(Integer clientId, Integer userId, String notifType, String message)
    {
        clientService.checkIfClientPresent(clientId);
        userService.checkIfUserPresent(userId);

        //Check if notification type is invalid
        notifType = notifType.toUpperCase();
        if(!notifType.equals("EMAIL") && !notifType.equals("SMS") && !notifType.equals("PUSH"))
        {
            //TODO Throw IllegalArgument Exception
            return null;
        }
        NotificationType type = NotificationType.valueOf(notifType);

        //Get Subscription Details for Client
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
                    else throw new GenericException("User with userID " + userId + " is Blocked");
                }
                else throw new GenericException("Notification Type Not Allowed: " + type + " for Client ID " + clientId);
            }
            else throw new GenericException("Daily Notification Limit Reached for ClientID " + clientId);
        }
        else throw new GenericException("Subscription Inactive for ClientID " + clientId);
    }

    //TODO Get Notification Count within a Date Range for a Client

}
