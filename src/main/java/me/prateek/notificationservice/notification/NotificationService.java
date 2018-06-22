//SERVICE CLASS FOR 'NOTIFICATION' MODEL
package me.prateek.notificationservice.notification;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class NotificationService {
    @Autowired
    NotificationRepository notificationRepository;

    public Notification getNotification(Integer id){ return notificationRepository.getOne(id); }

    public Notification addNotification(Integer clientId, Integer userId, String notifType, String message)
    {
        Notification newNotif = new Notification(clientId,userId,notifType,message);
        return notificationRepository.save(newNotif);
    }

    //TODO Get Notification Count within a Date Range for a Client

}
