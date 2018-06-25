package me.prateek.notificationservice.notification;

import me.prateek.notificationservice.exception.IllegalTypeException;
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

    //Function to check if id passed as Parameter is Integer or not
    public void checkIfIdInteger(String id)
    {
        try {
            Integer.parseInt(id);
        }
        catch(Exception e) {
            throw new IllegalTypeException("id","Integer");
        }
    }

    //Create New Notification
    @RequestMapping(value = "/notifications", method = RequestMethod.POST)
    public Notification newNotification(@RequestBody Map<String,String> body)
    {
        return notificationService.addNotification(Integer.valueOf(body.get("clientId")),Integer.valueOf(body.get("userId")),body.get("type"),body.get("message"));
    }

    //Get Notification Status eg./notification?id=1
    @RequestMapping(value = "/notifications", method = RequestMethod.GET)
    public Notification getNotification(@RequestParam String id)
    {
        checkIfIdInteger(id);
        return notificationService.getNotification(Integer.parseInt(id));
    }

    //Get Notification eg./notification/remaining?clientId=1
    @RequestMapping(value = "/notifications/remaining", method = RequestMethod.GET)
    public Integer getRemainingNotification(@RequestParam String clientId)
    {
        checkIfIdInteger(clientId);
        return subscriptionService.getRemainingNotifCount(Integer.parseInt(clientId));
    }
}
