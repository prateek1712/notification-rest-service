package me.prateek.notificationservice.client;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClientController {

    @Autowired
    private ClientRepository clientRepository;

    //Get Client Details Using id eg. /clients?id=2
    @RequestMapping(value = "/clients", method = RequestMethod.GET)
    public Client getClient(@RequestParam long id) {
        return clientRepository.findById(id);
    }

    //Create New Client
    @RequestMapping(value = "/clients", method = RequestMethod.POST)
    public Client newClient(@RequestBody Map<String, String> body) {
        Client c = new Client(body.get("name"), body.get("address"));
        clientRepository.save(c);
        return c;
    }

    //Update a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.PUT)
    public Client updateClient(@PathVariable long id, @RequestBody Map<String, String> body){
            Client c = new Client(id, body.get("name"), body.get("address"));
            clientRepository.save(c);
            return c;
    }

    //Delete a Client
    @RequestMapping(value = "/clients/{id}", method = RequestMethod.DELETE)
    public String deleteClient(@PathVariable long id) {
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
