package me.prateek.notificationservice.subscription;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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
}
