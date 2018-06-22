////SERVICE CLASS FOR 'SUBSCRIPTION' MODEL
package me.prateek.notificationservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Iterator;
import java.util.List;

@Service
public class SubscriptionService {
    @Autowired
    private SubscriptionRepository subscriptionRepository;

    public void addSubscription(Integer clientId, String subscrType)
    {
        Subscription s = new Subscription(clientId,subscrType);
        subscriptionRepository.save(s);
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

    public Integer getRemainingNotifCount(Integer clientId)
    {
        List<Object[]> a = subscriptionRepository.getDailyLimitAndNotifsSent(clientId);
        Object[] o = a.get(0);
        Integer notifDailyLimit = Integer.parseInt(String.valueOf(a.get(0)[0]));
        Integer notifsToday = Integer.parseInt(String.valueOf(a.get(0)[1]));
        return notifDailyLimit-notifsToday;
    }

    public void incrementNotifCount(Integer clientId)
    {
        subscriptionRepository.incrementNotifCount(clientId);
    }
}
