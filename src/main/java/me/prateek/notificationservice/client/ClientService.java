//SERVICE CLASS FOR 'CLIENT' MODEL
package me.prateek.notificationservice.client;

import me.prateek.notificationservice.exception.ResourceNotFoundException;
import me.prateek.notificationservice.subscription.Subscription;
import me.prateek.notificationservice.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class ClientService {
    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private SubscriptionService subscriptionService;

    public Client getClient(Integer id){

            Optional<Client> client = clientRepository.findById(id);
            if(!client.isPresent())
            {
                throw new ResourceNotFoundException(id,"Client");
            }
            return client.get();
    }

    public Client addClient(String name, String address, String subscrType)
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
        if(!b) return null;

        //Create new client
        Client c = new Client(name, address);
        Client c_saved = clientRepository.save(c);

        //Create new subscription for the client
        Integer clientId = c_saved.getId();
        subscriptionService.addSubscription(clientId,subscrType);
        return c_saved;
    }

    public boolean deleteClient(Integer clientId)
    {
        subscriptionService.deleteSubscription(clientId);
        clientRepository.deleteById(clientId);
        if(clientRepository.existsById(clientId))
        {
            return false;
        }
        return true;
    }

    public Subscription getClientSubscription(Integer clientId)
    {
        return subscriptionService.getSubscriptionByClientId(clientId);
    }


}
