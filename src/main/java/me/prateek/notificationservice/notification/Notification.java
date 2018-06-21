package me.prateek.notificationservice.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import me.prateek.notificationservice.subscription.SubscriptionType;
import org.springframework.beans.factory.annotation.Autowired;

import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Notification {

    @Transient
    @Autowired
    SubscriptionService subscriptionService;

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NotificationId" , nullable = false)
    private Integer id;

    @Column(name = "Date" , nullable = false)
    private Date date;

    @Column(name = "ClientId" , nullable = false)
    private Integer clientId;

    @Column(name = "UserId" , nullable = false)
    private Integer userId;

    @Column(name = "Type" , nullable = false)
    @Enumerated(EnumType.STRING)
    NotificationType type;

    @Column(name = "Message" , nullable = false)
    String message;

    public Notification(){}

    public Notification(Integer clientId, Integer userId, String notifType, String message) {

        //TODO If ClientID not found, throw exception
        notifType = notifType.toUpperCase();
        //Check if notification type is invalid
        if(!notifType.equals("EMAIL") && !notifType.equals("SMS") && !notifType.equals("PUSH"))
        {
            //TODO Throw IllegalArgument Exception
            return;
        }
        //1. Set Notification Type
        this.type = NotificationType.valueOf(notifType);

        //Get Subscription for Client
        Subscription sub = subscriptionService.getSubscriptionByClientId(clientId);
        if(sub.isSubscriptionActive()) //Check if subscription is active
        {
            if(!sub.ifDailyLimitReached()) //Check if daily limit not reached
            {
                SubscriptionType st = sub.getSubscriptionTypeInstance();
                if(st.ifAllowedNotif(this.type)) //Check if notification type allowed
                {
                    //2. Set Client ID
                    this.clientId = clientId;

                    //3. Set UserID
                    this.userId = userId;

                    //4. Set Date to Current Date
                    LocalDate d = LocalDate.now();
                    this.date = Date.valueOf(d);

                    //5. Set message
                    this.message = message;
                }
                else return; //TODO Throw NotificationTypeInvalid exception
            }
            else return; //TODO Throw DailyLimitReached exception
        }
        else return; //TODO Throw SubscriptionNotActive exception
    }

}
