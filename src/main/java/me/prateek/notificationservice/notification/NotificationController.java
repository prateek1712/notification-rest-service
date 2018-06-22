package me.prateek.notificationservice.notification;

import me.prateek.notificationservice.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @Autowired
    SubscriptionService subscriptionService;

    //Create New Notification
    @RequestMapping(value = "/notification", method = RequestMethod.POST)
    public Notification newNotification(@RequestBody Map<String,String> body)
    {
       return notificationService.addNotification(Integer.valueOf(body.get("clientId")),Integer.valueOf(body.get("userId")),body.get("type"),body.get("message"));
    }

    //Get Notification eg./notification?clientId=1
    @RequestMapping(value = "/notification", method = RequestMethod.GET)
    public Integer getRemainingNotification(@RequestParam Integer clientId)
    {
        return subscriptionService.getRemainingNotifCount(clientId);
    }
}
