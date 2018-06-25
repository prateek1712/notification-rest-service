package me.prateek.notificationservice.user;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import org.hibernate.annotations.Type;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Entity
public class User{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "UserID", nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "Name", nullable = false)
    private String name;

    @NotNull
    @Column(name = "Phone", nullable = false)
    private Integer phone;

    @NotNull
    @Column(name = "Email", nullable = false)
    private String email;

    @Column(name = "IsBlocked")
    @Type(type="yes_no") //To save Y or N in the database as character
    private Boolean isBlocked = false;

    //TODO Add deviceType field

    public User(){} //Default Constructor

    public User(String name, Integer phone, String email) {
        this.name = name;
        this.phone = phone;
        this.email = email;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getPhone() {
        return phone;
    }

    public void setPhone(Integer phone) {
        this.phone = phone;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Boolean isBlocked() {
        return isBlocked;
    }

    public void setBlocked(boolean blocked) {
        isBlocked = blocked;
    }
}