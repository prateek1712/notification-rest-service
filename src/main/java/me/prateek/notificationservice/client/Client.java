package me.prateek.notificationservice.client;

import javax.persistence.*;

@Entity
@Table(name = "Client")
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    @Column(name = "ClientID")
    private long id;

    @Column(name = "ClientName")
    private String name;

    @Column(name = "ClientAddress")
    private String address;

    public Client(){ }

    public Client(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Client(long id, String name, String address) {
        this.id = id;
        this.name = name;
        this.address = address;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
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
