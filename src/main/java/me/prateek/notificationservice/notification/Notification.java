package me.prateek.notificationservice.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.springframework.beans.factory.annotation.Autowired;

import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionType;

import me.prateek.notificationservice.subscription.SubscriptionService;
import me.prateek.notificationservice.user.UserService;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
@Component //To mark this as a spring bean
public class Notification {

    //Hack to make them Autowired within an Entity : https://stackoverflow.com/a/16489076
    @Transient
    private static SubscriptionService subscriptionService;

    @Transient
    private static UserService userService;

    @PostConstruct
    public void init() {
        System.out.println("Initializing SubscriptionService as [" +
                Notification.subscriptionService + "]");
        System.out.println("Initializing SubscriptionService as [" +
                Notification.userService + "]");

    }

    @Autowired
    public void setSubscriptionService(SubscriptionService subscriptionService) {
        Notification.subscriptionService = subscriptionService;
    }

    @Autowired
    public void setUserService(UserService userService) {
        Notification.userService = userService;
    }

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "NotificationID" , nullable = false)
    private Integer id;

    @Column(name = "Date" , nullable = false)
    private Date date;

    @Column(name = "ClientID" , nullable = false)
    private Integer clientId;

    @Column(name = "UserID" , nullable = false)
    private Integer userId;

    @Column(name = "Type" , nullable = false)
    @Enumerated(EnumType.STRING)
    NotificationType type;

    @Column(name = "Message" , nullable = false)
    String message;

    public Notification(){}

    //TODO Character Limit Check acc. to NotificationType
    public Notification(Integer clientId, Integer userId, String notifType, String message) {

        //TODO If ClientID or UserID not found, throw exception
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
                    if(!userService.getUser(userId).isBlocked()) //Check if User is Not Blocked
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

                        //6. Add Notification Count for Client
                        subscriptionService.incrementNotifCount(clientId);
                    }
                    else return; //TODO Throw UserBlocked exception
                }
                else return; //TODO Throw NotificationTypeInvalid exception
            }
            else return; //TODO Throw DailyLimitReached exception
        }
        else return; //TODO Throw SubscriptionNotActive exception
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public Integer getClientId() {
        return clientId;
    }

    public void setClientId(Integer clientId) {
        this.clientId = clientId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public NotificationType getType() {
        return type;
    }

    public void setType(NotificationType type) {
        this.type = type;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
}
