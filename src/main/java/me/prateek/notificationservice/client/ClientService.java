//SERVICE CLASS FOR 'CLIENT' MODEL
package me.prateek.notificationservice.client;

import me.prateek.notificationservice.exception.InvalidSubscriptionTypeException;
import me.prateek.notificationservice.exception.ResourceNotFoundException;
import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    public void checkIfClientPresent(Integer clientId)
    {
        Optional<Client> client = clientRepository.findById(clientId);
        if(!client.isPresent())
        {
            throw new ResourceNotFoundException(clientId,"Client");
        }
    }

    public Client getClient(Integer id){

        checkIfClientPresent(id); //Check if client with id is present
        return clientRepository.getOne(id);
    }

    public ClientResponse addClient(String name, String address, String subscrType)
    {
        //Input Validation for subscrType string
        boolean b = false;
        String[] allowed = {"GOLD", "SILVER", "PLATINUM"};
        for(String s : allowed)
        {
            if(subscrType.toUpperCase().equals(s))
            {
                b = true;
            }
        }
        if(!b)
        {
            throw new InvalidSubscriptionTypeException(subscrType);
        }

        //Create new client
        Client c_saved = clientRepository.save(new Client(name, address));

        //Create new subscription for the client
        Integer clientId = c_saved.getId();
        Subscription s_saved = subscriptionService.addSubscription(clientId,subscrType);
        return new ClientResponse(c_saved, s_saved);
    }

    public ClientResponse updateClient(Integer id, String name, String address, String subscrType)
    {
        //Input Validation for subscrType string
        boolean b = false;
        String[] allowed = {"GOLD", "SILVER", "PLATINUM"};
        for(String s : allowed)
        {
            if(subscrType.toUpperCase().equals(s))
            {
                b = true;
            }
        }
        if(!b)
        {
            throw new InvalidSubscriptionTypeException(subscrType);
        }

        //Create new client
        Client c_saved = clientRepository.save(new Client(id, name, address));

        //Create new subscription for the client
        Subscription s = subscriptionService.getSubscriptionByClientId(id);
        Integer subscriptionId = s.getId();
        Subscription s_saved = subscriptionService.updateSubscription(subscriptionId, id, subscrType);
        return new ClientResponse(c_saved, s_saved);
    }

    public void deleteClient(Integer clientId)
    {
        checkIfClientPresent(clientId); //Check if client with id is present
        subscriptionService.deleteSubscription(clientId); //1. Delete client's subscription first
        clientRepository.deleteById(clientId); //2. Delete client itself
    }

    public Subscription getClientSubscription(Integer clientId)
    {
        checkIfClientPresent(clientId); //Check if client with id is present
        return subscriptionService.getSubscriptionByClientId(clientId);
    }

    public List<Client> getAllClients()
    {
        return clientRepository.findAll();
    }
}
