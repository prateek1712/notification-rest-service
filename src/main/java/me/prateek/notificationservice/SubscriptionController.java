package me.prateek.notificationservice;

import me.prateek.notificationservice.subscription.Subscription;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class SubscriptionController {

    //Get Subscription Details Using id eg. /subscription?id=2

    @RequestMapping(value = "/subscription", method = RequestMethod.GET)
    public String getSubscription(@RequestParam long id) {
        //TODO Complete this GET method
        return "Hello World";
    }

    //Delete(Cancel) a Subscription

    @RequestMapping(value = "/subscription/{id}", method = RequestMethod.PUT)
    public String deleteSubscription() {
        //TODO Complete this DELETE method
        return "Uou";
    }

    //New Subscription

    @RequestMapping(value = "/subscription", method = RequestMethod.POST)
    public Subscription newSubscription(@RequestBody Map<String, String> body) {
        //TODO Complete this POST method
        return new Subscription();
    }

}