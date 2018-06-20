package me.prateek.notificationservice.client;

import javafx.scene.SubScene;
import me.prateek.notificationservice.subscription.SubscriptionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;


@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private ClientService clientService;

    //Get Client Details Using id eg. /clients?id=2
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public Client getClient(@RequestParam Integer id) {
        return clientRepository.getOne(id);
    }

    //Create New Client
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Client newClient(@RequestBody Map<String, String> body) {
        return clientService.addClient(body.get("name"),body.get("address"),body.get("subscription_type"));
    }

    //Update a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public Client updateClient(@PathVariable Integer id, @RequestBody Map<String, String> body){
            Client c = new Client(id, body.get("name"), body.get("address"));
            clientRepository.save(c);
            return c;
    }

    //Delete a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public String deleteClient(@PathVariable Integer id) {
        //TODO Complete this DELETE method
        clientRepository.deleteById(id);
        return "Client with id "+id+" deleted" ;
    }

    //Return Number of Clients
    @RequestMapping(value = "/clients/total", method = RequestMethod.GET)
    public double numberClients()
    {
        return clientRepository.count();
    }
}
