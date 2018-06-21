package me.prateek.notificationservice.user;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    @Autowired
    private UserRepository userRepository;

    public User getUser(Integer id)
    {
        return userRepository.getOne(id);
    }

    public User addUser(String name, Integer phone, String email)
    {
       User u = new User(name, phone, email);
       userRepository.save(u);
       return u;
    }

    public User updateUser(Integer id, String name, Integer phone, String email)
    {
        User u = userRepository.getOne(id);
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

}
