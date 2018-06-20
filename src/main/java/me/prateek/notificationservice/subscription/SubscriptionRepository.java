package me.prateek.notificationservice.subscription;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    //Returns Subscription with ClientID = passed parameter
    Subscription findByClientId(Integer clientId);

}