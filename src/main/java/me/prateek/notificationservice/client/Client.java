package me.prateek.notificationservice.client;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import javax.persistence.*;
import javax.validation.constraints.NotNull;

@Entity
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ClientID" , nullable = false)
    private Integer id;

    @NotNull
    @Column(name = "ClientName" , nullable = false)
    private String name;

    @NotNull
    @Column(name = "ClientAddress" , nullable = false)
    private String address;

    public Client(){ }

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Client(Integer id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
