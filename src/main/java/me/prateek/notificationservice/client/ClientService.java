package me.prateek.notificationservice.client;

import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    public Client addClient(String name, String address, String subscrType)
    {
        //Create new client
        Client c = new Client(name, address);
        Client c_saved = clientRepository.save(c);

        //Create new subscription for the client
        Integer clientId = c_saved.getId();
        subscriptionService.addSubscription(clientId,subscrType);
        return c_saved;
    }

    public void deleteClient(Integer id)
    {

    }
}
