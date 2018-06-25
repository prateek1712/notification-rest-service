////SERVICE CLASS FOR 'SUBSCRIPTION' MODEL
package me.prateek.notificationservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public Subscription addSubscription(Integer clientId, String subscrType)
    {
        return subscriptionRepository.save(new Subscription(clientId,subscrType));
    }

    public Subscription updateSubscription(Integer id, Integer clientId, String subscrType)
    {
        Subscription s = new Subscription(id, clientId, subscrType);
        subscriptionRepository.save(s);
        return s;
    }

    public void deleteSubscription(Integer clientId)
    {
        Subscription s = subscriptionRepository.findByClientId(clientId);
        subscriptionRepository.deleteById(s.getId());
    }

    public Subscription getSubscriptionByClientId(Integer clientId)
    {
        return subscriptionRepository.findByClientId(clientId);
    }

    //Gets the remaining notification count for a day
    public Integer getRemainingNotifCount(Integer clientId)
    {
        List<Object[]> a = subscriptionRepository.getDailyLimitAndNotifsSent(clientId);
        Integer notifDailyLimit = Integer.parseInt(String.valueOf(a.get(0)[0]));
        Integer notifsToday = Integer.parseInt(String.valueOf(a.get(0)[1]));
        return notifDailyLimit-notifsToday;
    }

    //Used by Notification Service to Increment Notification Count pertaining to Subscription
    public void incrementNotifCount(Integer clientId)
    {
        subscriptionRepository.incrementNotifCount(clientId);
    }
}
