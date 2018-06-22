package me.prateek.notificationservice.subscription;



import javax.persistence.*;
import java.time.LocalDate;
import java.sql.Date;

@Entity
public class Subscription {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "SubscriptionID" , nullable = false)
    private Integer id;

    @Column(name = "Type" , nullable = false)
    private String subscriptionType;

    @Column(name = "ClientID" , nullable = false)
    private Integer clientId;

    @Column(name = "Period" , nullable = false)
    private String period = "Monthly";

    @Column(name = "ExpiryDate" , nullable = false)
    private Date expiryDate;

    @Column(name = "Price" , nullable = false)
    private Integer price;

    @Column(name = "DailyNotifLimit", nullable = false)
    private Integer dailyNotifLimit;

    @Column(name = "NotifsSentToday", nullable = false)
    private Integer notifsSentToday;

    @Transient
    private SubscriptionType subscriptionTypeInstance = null;

    //Default Constructor needed by Spring JPA
    public Subscription(){ }

    public Subscription(Integer clientId, String subType)
    {
        //1. Set Client ID for Subscription
        this.clientId = clientId;


        //2. Set type of Subscription
        setSubscriptionTypeInstance(subType);

        //3. Set Notifications Sent
        this.notifsSentToday = 0;

        //4. Set Expiry Date
        LocalDate thirtyDaysPlusToday = LocalDate.now().plusDays(30);
        this.expiryDate = Date.valueOf(thirtyDaysPlusToday);

        //5. Set Price
        this.price = subscriptionTypeInstance.getPrice();

        //6. Set Daily NotificationType Limit
        this.dailyNotifLimit = subscriptionTypeInstance.getNotifsAllowedPerDay();
    }

    public Integer getId() {
        return id;
    }

    public Integer getPrice() {
        return price;
    }

    public String getSubscriptionType() {
        return subscriptionType;
    }

    public Integer getClientId() {
        return clientId;
    }

    public Date getExpiryDate() {
        return expiryDate;
    }

    public String getPeriod() {
        return period;
    }

    public Integer getNotifsSentToday() { return notifsSentToday; }

    public Integer getDailyNotifLimit() {
        return dailyNotifLimit;
    }

    public boolean isSubscriptionActive() //Check if subscription is active
    {
        java.util.Date today = new java.util.Date();
        if(today.compareTo(this.expiryDate) <= 0) //Check if today's date is less than or equal to the expiry date
        {
            return true;
        }
        return false;
    }

    public boolean ifDailyLimitReached() //Check if daily notification limit reached
    {
        setSubscriptionTypeInstance(subscriptionType);
        Integer limit = subscriptionTypeInstance.getNotifsAllowedPerDay();
        if(notifsSentToday < limit)
        {
            return false;
        }
        return true;
    }

    public void setSubscriptionTypeInstance(String subType)
    {
        if(subscriptionTypeInstance == null)
        {
            subscriptionType = subType.toUpperCase();
            switch(subscriptionType)
            {
                case "SILVER":
                    subscriptionTypeInstance  = SilverSubscription.getInstance();
                    break;

                case "GOLD":
                    subscriptionTypeInstance = GoldSubscription.getInstance();
                    break;

                case "PLATINUM":
                    subscriptionTypeInstance = PlatinumSubscription.getInstance();
                    break;

                default:
                    return;
            }
        }
        else return;
    }

    public SubscriptionType getSubscriptionTypeInstance() {
        if(subscriptionType == null)
        {
            setSubscriptionTypeInstance(getSubscriptionType());
        }
        return subscriptionTypeInstance;
    }
}
