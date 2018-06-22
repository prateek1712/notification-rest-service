package me.prateek.notificationservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer userId)
    {
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
        User u = userRepository.getOne(userId);
        u.setName(name);
        u.setPhone(phone);
        u.setEmail(email);
        userRepository.save(u);
        return u;
    }

    public String deleteUser(Integer id)
    {
        userRepository.deleteById(id);
        return ("User with ID " + id + " deleted");
    }

    public Long countUsers()
    {
        return userRepository.count();
    }

    public String blockUnblockUser(Integer userId, Boolean status)
    {
        //TODO Send Appropriate Response
        User u = userRepository.getOne(userId);
        u.setBlocked(status);
        userRepository.save(u);
        return "User with userID" + userId + "blocked successfully";
    }

}
