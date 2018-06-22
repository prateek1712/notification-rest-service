package me.prateek.notificationservice.client;

import me.prateek.notificationservice.subscription.Subscription;

public class ClientResponse {
    Client client;
    Subscription subscription;
    ClientResponse(Client c, Subscription s)
    {
        client = c;
        subscription = s;
    }

}
