package me.prateek.notificationservice.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

@RestController
public class UserController {

    @Autowired
    private UserService userService;

    //Get User Details Using id eg. /users?id=2
    @RequestMapping(value = "/users", method = RequestMethod.GET)
    public User getUser(@RequestParam Integer id) {
        return userService.getUser(id);
    }

    //Create New User
    @RequestMapping(value = "/users", method = RequestMethod.POST)
    public User newUser(@RequestBody Map<String, String> body) {
        return userService.addUser(body.get("name"),Integer.valueOf(body.get("phone")),body.get("email"));
    }

    //Update a User
    @RequestMapping(value = "/users/{id}", method = RequestMethod.PUT)
    public User updateUser(@PathVariable Integer id, @RequestBody Map<String, String> body){
        return userService.updateUser(id,body.get("name"),Integer.valueOf(body.get("phone")),body.get("email"));
    }

    //Delete a User
    @RequestMapping(value = "/users/{id}", method = RequestMethod.DELETE)
    public String deleteUser(@PathVariable Integer id) {
        return userService.deleteUser(id);
    }

    //Return Number of Users
    @RequestMapping(value = "/users/total", method = RequestMethod.GET)
    public Long numberUsers()
    {
        return userService.countUsers();
    }

    //Block a User
    @RequestMapping(value = "/users/{id}/block", method = RequestMethod.PUT)
    public String blockUser(@PathVariable Integer id)
    {
        //TODO InvalidParameter Handling
        userService.blockUnblockUser(id, true);
        return "User with userID" + id + " blocked";
    }

    //Unblock a User
    @RequestMapping(value = "/users/{id}/unblock", method = RequestMethod.PUT)
    public String unblockUser(@PathVariable Integer id)
    {
        //TODO InvalidParameter Handling
        userService.blockUnblockUser(id, false);
        return "User with userID" + id + " unblocked";
    }
}
