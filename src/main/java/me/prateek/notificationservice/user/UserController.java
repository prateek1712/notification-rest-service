package me.prateek.notificationservice.user;

import me.prateek.notificationservice.exception.IllegalTypeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

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

    //Get User Details Using id eg. /users?id=2
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUser(@RequestParam String id) {
        checkIfIdInteger(id);
        return userService.getUser(Integer.parseInt(id));
    }

    //Create New User
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User newUser(@RequestBody Map<String, String> body) {
        return userService.addUser(body.get("name"),Integer.valueOf(body.get("phone")),body.get("email"));
    }

    //Update a User
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable String id, @RequestBody Map<String, String> body){
        checkIfIdInteger(id);
        return userService.updateUser(Integer.parseInt(id),body.get("name"),Integer.valueOf(body.get("phone")),body.get("email"));
    }

    //Delete a User
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable String id) {
        checkIfIdInteger(id);
        return userService.deleteUser(Integer.parseInt(id));
    }

    //Return Number of Users
    @RequestMapping(value = "/users/total", method = RequestMethod.GET)
    public Long numberUsers()
    {
        return userService.countUsers();
    }

    //Block a User
    @RequestMapping(value = "/users/{id}/block", method = RequestMethod.PUT)
    public String blockUser(@PathVariable String id)
    {
        checkIfIdInteger(id);
        return userService.blockUnblockUser(Integer.parseInt(id), true);
    }

    //Unblock a User
    @RequestMapping(value = "/users/{id}/unblock", method = RequestMethod.PUT)
    public String unblockUser(@PathVariable String id)
    {
        checkIfIdInteger(id);
        return userService.blockUnblockUser(Integer.parseInt(id), false);
    }

    //Get list of ALL Users
    @RequestMapping(value = "/users/list", method = RequestMethod.GET)
    public List<User> getAllUsers()
    {
        return userService.getAllUsers();
    }
}
