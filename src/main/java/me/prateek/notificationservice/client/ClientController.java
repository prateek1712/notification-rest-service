package me.prateek.notificationservice.client;

import me.prateek.notificationservice.exception.IllegalTypeException;
import me.prateek.notificationservice.exception.NullKeyException;
import me.prateek.notificationservice.subscription.Subscription;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    //Function to check if id passed as Parameter is Integer or not
    public boolean checkIfIdInteger(String id)
    {
        try {
            Integer.parseInt(id);
        }
        catch(Exception e) {
            throw new IllegalTypeException("id","Integer");
        }
        return true;
    }

    //Get Client Details Using id eg. /clients?id=2
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public Client getClient(@RequestParam String id)
    {
        checkIfIdInteger(id);
        return clientService.getClient(Integer.parseInt(id));
    }

    //Get Client Subscription Details Using id eg. /clients/{id}/subscription
    @RequestMapping(value = "/clients/{id}/subscription", method = RequestMethod.GET)
    public Subscription getSubscription(@PathVariable String id) {
        checkIfIdInteger(id);
        return clientService.getClientSubscription(Integer.parseInt(id));
    }


    //Create New Client
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public ClientResponse newClient(@RequestBody Map<String, String> body) {
       return clientService.addClient(body.get("name"),body.get("address"),body.get("subscriptionType"));
    }

    //Update a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public ClientResponse updateClient(@PathVariable String id, @RequestBody Map<String, String> body){
        checkIfIdInteger(id);
        clientService.checkIfClientPresent(Integer.parseInt(id));
        return clientService.updateClient(Integer.parseInt(id), body.get("name"),body.get("address"),body.get("subscriptionType"));
    }

    //Delete a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public String deleteClient(@PathVariable String id) {
        //TODO Improve Response on Client Deletion
        checkIfIdInteger(id);
        clientService.deleteClient(Integer.parseInt(id));
        return "Client with id "+id+" deleted" ;
    }

    //Return Number of Clients
    @RequestMapping(value = "/clients/total", method = RequestMethod.GET)
    public Long numberClients()
    {
        return clientRepository.count();
    }

    //Returns Complete List of Clients
    @RequestMapping(value = "/clients/list", method = RequestMethod.GET)
    public List<Client> getAllClients()
    {
        return clientService.getAllClients();
    }
}
