package me.prateek.notificationservice.subscription;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

public interface SubscriptionRepository extends JpaRepository<Subscription, Integer> {

    //Returns Subscription with ClientID = passed parameter
    Subscription findByClientId(Integer clientId);

    @Modifying   //To signify that it's SQL DDL which modifies the database & doesn't return anything (executeUpdate())
    @Transactional
    @Query(value = "UPDATE Subscription SET NotifsSentToday = NotifsSentToday + 1 WHERE ClientID = ?1", nativeQuery=true)
    void incrementNotifCount(Integer clientId);

    @Query(value = "SELECT DailyNotifLimit,NotifsSentToday FROM Subscription WHERE ClientID = ?1", nativeQuery=true)
    List<Object[]> getDailyLimitAndNotifsSent(Integer clientId);
}