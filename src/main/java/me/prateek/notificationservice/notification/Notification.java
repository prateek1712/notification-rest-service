package me.prateek.notificationservice.notification;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import javax.persistence.*;
import java.sql.Date;
import java.time.LocalDate;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class Notification {

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

    //TODO Character Limit Check of Message acc. to NotificationType
    public Notification(Integer clientId, Integer userId, NotificationType type, String message) {

        //1. Set Client ID
        this.clientId = clientId;

        //2. Set UserID
        this.userId = userId;

        //3. Set Notification Type
        this.type = type;

        //4. Set Date to Current Date
        LocalDate d = LocalDate.now();
        this.date = Date.valueOf(d);

        //5. Set message
        this.message = message;
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
