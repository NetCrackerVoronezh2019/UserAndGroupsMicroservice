package ru.services;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.domen.User;
import ru.repos.UserRepository;

import java.util.List;

@Service
public class UserService {
    @Autowired
    private UserRepository userRepository;

    public void saveUser(User user) {
        userRepository.save(user);
    }

    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    public List<User> getFriends(Long userId) {
        return userRepository.fiendUserFriends(userId);
    }

    public List<User> getOutgoingFriends(Long userId) {
        return userRepository.fiendOutgoing(userId);
    }

    public List<User> getIngoingFriends(Long userId) {
        return userRepository.fiendIngoing(userId);
    }

}
