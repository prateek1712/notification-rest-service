package me.prateek.notificationservice.notification;


import javax.persistence.Entity;
import javax.persistence.Id;
import java.sql.Date;

@Entity
public class Notification {
    @Id
    private Integer id;


    private Date date;


    private Integer clientId;

}
