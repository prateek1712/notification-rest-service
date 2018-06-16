package me.prateek.notificationservice;

import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class ClientController {

    //Get Client Details Using id eg. /Client?id=2

    @RequestMapping(value = "/client", method = RequestMethod.GET)
    public String getClient(@RequestParam long id) {
        //TODO Complete this GET method
        return "Hello World";
    }

    //Delete a Client

    @RequestMapping(value = "/client/{id}", method = RequestMethod.PUT)
    public String deleteClient() {
        //TODO Complete this DELETE method
        return "Uou";
    }

    //New Client

    @RequestMapping(value = "/client", method = RequestMethod.POST)
    public Client newClient(@RequestBody Map<String, String> body) {
        //TODO Complete this POST method
        return new Client();
    }

}
