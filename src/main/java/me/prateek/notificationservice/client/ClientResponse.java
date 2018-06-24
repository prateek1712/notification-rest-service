package me.prateek.notificationservice.client;

import me.prateek.notificationservice.subscription.Subscription;

public class ClientResponse {
    private Client client;
    private Subscription subscription;

    ClientResponse(Client c, Subscription s)
    {
        client = c;
        subscription = s;
    }

    //Jackson JSON Library Needs Getters to get objects which are NOT public
    //Either make fields public or create getters for them
    public Client getClient() {
        return client;
    }

    public Subscription getSubscription() {
        return subscription;
    }
}
