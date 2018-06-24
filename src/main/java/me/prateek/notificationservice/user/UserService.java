package me.prateek.notificationservice.user;


import me.prateek.notificationservice.exception.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public void checkIfUserPresent(Integer userId)
    {
        Optional<User> user = userRepository.findById(userId);
        if(!user.isPresent())
        {
            throw new ResourceNotFoundException(userId,"User");
        }
    }

    public User getUser(Integer userId)
    {
        checkIfUserPresent(userId);
        return userRepository.getOne(userId);
    }

    public User addUser(String name, Integer phone, String email)
    {
       User u = new User(name, phone, email);
       userRepository.save(u);
       return u;
    }

    public User updateUser(Integer userId, String name, Integer phone, String email)
    {
        checkIfUserPresent(userId);
        User u = userRepository.getOne(userId);
        u.setName(name);
        u.setPhone(phone);
        u.setEmail(email);
        userRepository.save(u);
        return u;
    }

    public String deleteUser(Integer userId)
    {
        checkIfUserPresent(userId);
        userRepository.deleteById(userId);
        return ("User with ID " + userId + " deleted");
    }

    public Long countUsers()
    {
        return userRepository.count();
    }

    public String blockUnblockUser(Integer userId, Boolean status)
    {
        //TODO Send Appropriate Response
        checkIfUserPresent(userId);
        User u = userRepository.getOne(userId);
        u.setBlocked(status);
        userRepository.save(u);
        if(status) return "User with userID " + userId + " blocked successfully";
        else return "User with userID " + userId + " unblocked successfully";
    }

    public List<User> getAllUsers()
    {
        return userRepository.findAll();
    }
}
